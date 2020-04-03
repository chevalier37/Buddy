package com.buddy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import com.buddy.DAO.UserDAO;
import com.buddy.model.BankAccount;
import com.buddy.model.Connection;
import com.buddy.model.Transaction;
import com.buddy.model.User;
import com.buddy.service.BuddyService;

@SpringBootTest
public class BuddyTest {
	
	@Autowired
	private  BuddyService buddyService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDAO userDAO;
	
	@Test
	@Sql({"/buddyTest.sql"}) 
	@DisplayName("register user")
	public void registerUserTest() {
	
		String hashPassword = passwordEncoder.encode((CharSequence) "123");
		String hashMail = passwordEncoder.encode( (CharSequence) "sam@mail.com");
		User creatUser = new User("Sam", "TODD12", hashMail, hashPassword, 0.00, null);
		
		userDAO.save(creatUser);

		User findUser =userDAO.getUserByFirstnameAndLastname("Sam", "TODD12");
		
		assertEquals("Sam", findUser.getFirstname());
		assertEquals("TODD12", findUser.getLastname());
	}
	
	@Test
	@Sql({"/buddyTest.sql"}) 
	@DisplayName("payIn")
	public void PayInUserTest() {
		
		buddyService.addUser("Sam", "TODD1", "s.todd@gmail.com", "123", 0.00);
		
		User findUser = buddyService.findUser("Sam", "TODD1");
		int userId = findUser.getId();
		
		buddyService.payIn(userId, 50.00);
		
		
		
		User findUserPayIn = buddyService.findUser("Sam", "TODD1");
		
		assertEquals(50, findUserPayIn.getWallet());
	}
	
	
	@Test
	@Sql({"/buddyTest.sql"}) 
	@DisplayName("payOut")
	public void PayOutUserTest() {
		buddyService.addUser("Sam", "TODD123", "s.todd@gmail.com", "123", 0.00);
		User findUser = buddyService.findUser("Sam", "TODD123");
		int userId = findUser.getId();
		buddyService.payIn(userId, 50.00);
		buddyService.payOut(userId, 10.00);
		User findUserPayOut = buddyService.findUser("Sam", "TODD123");
		assertEquals(40, findUserPayOut.getWallet());
	}
	
	@Test
	@Sql({"/buddyTest.sql"}) 
	@DisplayName("add connexion")
	public void addConnexionTest() {
		User user1 = buddyService.addUser("Sam", "TODD124", "s.todd@gmail.com", "123", 0.00);
		User user2 = buddyService.addUser("Sam", "TODD144", "s.todd@gmail.com", "123", 0.00);
		int userId1 = user1.getId();
		int userId2 = user2.getId();
		buddyService.addConnection(userId1, userId2);
		Connection connection = buddyService.findOneConnection(userId1, userId2);
		assertEquals(user1.getId(), connection.getFrom_id());
	}
	
	@Test
	@Sql({"/buddyTest.sql"}) 
	@DisplayName("get list connexion")
	public void getConnectionListTest() {
		User user1 = buddyService.addUser("Sam", "TODD124", "s.todd@gmail.com", "123", 0.00);
		User user2 = buddyService.addUser("Sam", "TODD144", "s.todd@gmail.com", "123", 0.00);
		User user3 = buddyService.addUser("Sam", "TODD145", "s.todd@gmail.com", "123", 0.00);
		int userId1 = user1.getId();
		int userId2 = user2.getId();
		int userId3 = user3.getId();
		buddyService.addConnection(userId1, userId2);
		buddyService.addConnection(userId1, userId3);
		
		List<User> listConnexion = buddyService.getConnectionList(userId1);
		List<User> listConnexionExpected = new ArrayList<>();
		listConnexionExpected.add(user2);
		listConnexionExpected.add(user3);
	
		assertEquals(listConnexionExpected.get(0).getLastname(), listConnexion.get(0).getLastname());
	}
	
	@Test
	@Sql({"/buddyTest.sql"}) 
	@DisplayName("add transaction")
	public void addTransactionTest() {
		User user1 = buddyService.addUser("Sam", "TODD56", "s.todd@gmail.com", "123", 50.00);
		User user2 = buddyService.addUser("Sam", "TODD55", "s.todd@gmail.com", "123", 50.00);
		int userId1 = user1.getId();
		int userId2 = user2.getId();
		buddyService.addTransaction(userId1, userId2, 10.00, "test transaction");
		User findUser = buddyService.findUser("Sam", "TODD55");
		
		assertEquals(59.95, findUser.getWallet());
	}
	
	@Test
	@Sql({"/buddyTest.sql"}) 
	@DisplayName("get transaction list")
	public void getTransactionListTest() {
		User user1 = buddyService.addUser("Sam", "TODD56", "s.todd@gmail.com", "123", 50.00);
		User user2 = buddyService.addUser("Sam", "TODD55", "s.todd@gmail.com", "123", 50.00);
		int userId1 = user1.getId();
		int userId2 = user2.getId();
		buddyService.addTransaction(userId1, userId2, 10.00, "test transaction");
		List<Transaction> transaction = buddyService.getTransactionList(userId1);
		
		assertEquals(9.95, transaction.get(0).getAmount());
	}
	
	
	
	

}
