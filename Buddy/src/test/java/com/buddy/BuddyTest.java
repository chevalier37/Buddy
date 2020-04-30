package com.buddy;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.dao.BankAccountDAO;
import com.buddy.dao.TransactionDAO;
import com.buddy.dao.UserDAO;
import com.buddy.model.BankAccount;
import com.buddy.model.Transaction;
import com.buddy.model.User;


@SpringBootTest
@TestPropertySource(locations="classpath:applicationTest.properties")
public class BuddyTest {
	
	@Autowired
	private  BankAccountDAO bankAccountDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDAO userDAO;
	
	
	@Autowired
	private TransactionDAO transactionDAO;

	
	@Test
	@Sql({"/buddyTest.sql"}) 
	@DisplayName("register user")
	public void registerUserTest() {
		List<User> userList = new ArrayList<>();
		String hashPassword = passwordEncoder.encode((CharSequence) "123");
		String hashMail = passwordEncoder.encode( (CharSequence) "sam@mail.com");
		User creatUser = new User("Sam", "TODD12", hashMail, hashPassword, 0.00, userList, userList, null, null, null);
		userDAO.save(creatUser);

		User findUser = userDAO.getUserByFirstnameAndLastname("Sam", "TODD12");
		
		assertEquals("Sam", findUser.getFirstname());
		assertEquals("TODD12", findUser.getLastname());
	}
	
	@Test
	@Transactional
	@Sql({"/buddyTest.sql"}) 
	@DisplayName("payIn")
	public void PayInUserTest() {
		List<User> userList = new ArrayList<>();
		String hashPassword = passwordEncoder.encode((CharSequence) "123");
		String hashMail = passwordEncoder.encode( (CharSequence) "sam@mail.com");
		User creatUser = new User("Sam", "TODD1", hashMail, hashPassword, 0.00, userList, userList, null, null, null);
		userDAO.save(creatUser);

		User findUser = userDAO.getUserByFirstnameAndLastname("Sam", "TODD1");
		int userId = findUser.getId();
		
		User user = userDAO.getOne(userId);
		Double newWallet = user.getWallet() + 50.00;
		user.setWallet(newWallet);
		userDAO.save(user);
		BankAccount  payIn = new BankAccount(userId, 50.00, true);
		bankAccountDAO.save(payIn);

		User findUserPayIn = userDAO.getUserByFirstnameAndLastname("Sam", "TODD1");
		
		assertEquals(50, findUserPayIn.getWallet());
	}
	
	
	@Test
	@Transactional
	@Sql({"/buddyTest.sql"}) 
	@DisplayName("payOut")
	public void PayOutUserTest() {
		List<User> userList = new ArrayList<>();
		String hashPassword = passwordEncoder.encode((CharSequence) "123");
		String hashMail = passwordEncoder.encode( (CharSequence) "sam@mail.com");
		User creatUser = new User("Sam", "TODD123", hashMail, hashPassword, 0.00, userList, userList, null, null, null);
		userDAO.save(creatUser);

		User findUser = userDAO.getUserByFirstnameAndLastname("Sam", "TODD123");
		int userId = findUser.getId();
		User user = userDAO.getOne(userId);
		Double newWallet = user.getWallet() + 50.00;
		user.setWallet(newWallet);
		userDAO.save(user);
		BankAccount  payIn = new BankAccount(userId, 50.00, true);
		bankAccountDAO.save(payIn);

		Double amountOut = - 10.00;
		Double newWallet1 = user.getWallet() + amountOut;
		user.setWallet(newWallet1);
		userDAO.save(user);
		BankAccount  payOut = new BankAccount(userId, 10.00, false);
		bankAccountDAO.save(payOut);
		
		User findUserPayOut = userDAO.getUserByFirstnameAndLastname("Sam", "TODD123");
		assertEquals(40, findUserPayOut.getWallet());
	}
	
	@Test
	@Sql({"/buddyTest.sql"}) 
	@DisplayName("add connexion")
	public void addConnexionTest() {
		List<User> userList = new ArrayList<>();
		String hashPassword = passwordEncoder.encode((CharSequence) "123");
		String hashMail = passwordEncoder.encode( (CharSequence) "sam@mail.com");
		User creatUser = new User("Sam", "TODD123", hashMail, hashPassword, 0.00, userList, userList, null, null, null);
		User user1 = userDAO.save(creatUser);
		
		String hashPassword1 = passwordEncoder.encode((CharSequence) "123");
		String hashMail1 = passwordEncoder.encode( (CharSequence) "sam@mail.com");
		User creatUser1 = new User("Sam", "TODD124", hashMail1, hashPassword1, 0.00, userList, userList, null, null, null);
		User user2 = userDAO.save(creatUser1);
		
		int userId2 = user2.getId();
		
		List<User> friendListUser1 = user1.getMyFriends();
		friendListUser1.add(user2);
		userDAO.save(user1);	
		
		assertEquals(user1.getMyFriends().get(0).getId(), userId2);
	}
	
	@Test
	@Sql({"/buddyTest.sql"}) 
	@DisplayName("get list connexion")
	public void getConnectionListTest() {
		List<User> userList = new ArrayList<>();
		String hashPassword = passwordEncoder.encode((CharSequence) "123");
		String hashMail = passwordEncoder.encode( (CharSequence) "sam@mail.com");
		User creatUser = new User("Sam", "TODD123", hashMail, hashPassword, 0.00, userList, userList, null, null, null);
		User user1 = userDAO.save(creatUser);
		
		String hashPassword1 = passwordEncoder.encode((CharSequence) "123");
		String hashMail1 = passwordEncoder.encode( (CharSequence) "sam@mail.com");
		User creatUser1 = new User("Sam", "TODD124", hashMail1, hashPassword1, 0.00, userList, userList, null, null, null);
		User user2 = userDAO.save(creatUser1);
		
		String hashPassword2 = passwordEncoder.encode((CharSequence) "123");
		String hashMail2 = passwordEncoder.encode( (CharSequence) "sam@mail.com");
		User creatUser2 = new User("Sam", "TODD125", hashMail2, hashPassword2, 0.00, userList, userList, null, null, null);
		User user3 = userDAO.save(creatUser2);
		
	
		List<User> friendListUser1 = user1.getMyFriends();
		friendListUser1.add(user2);
		friendListUser1.add(user3);
		userDAO.save(user1);	
		
		
		List<User> listConnexionExpected = new ArrayList<>();
		listConnexionExpected.add(user2);
		listConnexionExpected.add(user3);
	
		assertEquals(listConnexionExpected, user1.getMyFriends());
	}
	
	@Test
	@Transactional
	@Sql({"/buddyTest.sql"}) 
	@DisplayName("add transaction")
	public void addTransactionTest() {
		List<User> userList = new ArrayList<>();
		String hashPassword = passwordEncoder.encode((CharSequence) "123");
		String hashMail = passwordEncoder.encode( (CharSequence) "sam@mail.com");
		User creatUser = new User("Sam", "TODD124", hashMail, hashPassword, 50.00, userList, userList, null, null, null);
		User user1 = userDAO.save(creatUser);
		
		String hashPassword1 = passwordEncoder.encode((CharSequence) "123");
		String hashMail1 = passwordEncoder.encode( (CharSequence) "sam@mail.com");
		User creatUser1 = new User("Sam", "TODD1245", hashMail1, hashPassword1, 50.00, userList, userList, null, null, null);
		User user2 = userDAO.save(creatUser1);
		
		int userId1 = user1.getId();
		int userId2 = user2.getId();
		
		Double amount = 10.00;
		Double feeBuddy = amount * 0.005;
		Double amountWallet = amount - feeBuddy;
		Transaction transaction = new Transaction(userId1, userId2, amountWallet, "test transaction");
		transactionDAO.save(transaction);
		
		User getFromUser = userDAO.getOne(userId1);
		Double getWalletFrom = getFromUser.getWallet();
		
		if(getWalletFrom < amount) {
		}else {
			Double updateWalletFrom = getWalletFrom - amount;
			getFromUser.setWallet(updateWalletFrom);
			userDAO.save(getFromUser);
			
			User getToUser = userDAO.getOne(userId2);
			Double updateWalletTo = getToUser.getWallet() + amountWallet;
			getToUser.setWallet(updateWalletTo);
			userDAO.save(getToUser);
		}
		
		User findUser = userDAO.getUserByFirstnameAndLastname("Sam", "TODD1245");
		
		assertEquals(59.95, findUser.getWallet());
	}
	
	@Test
	@Transactional
	@Sql({"/buddyTest.sql"}) 
	@DisplayName("get transaction list")
	public void getTransactionListTest() {
		List<User> userList = new ArrayList<>();
		List<Transaction> userListTansaction = new ArrayList<>();
		String hashPassword = passwordEncoder.encode((CharSequence) "123");
		String hashMail = passwordEncoder.encode( (CharSequence) "sam@mail.com");
		User creatUser = new User("Sam", "TODD124", hashMail, hashPassword, 50.00, userList, userList, userListTansaction, userListTansaction, null);
		User user1 = userDAO.save(creatUser);
		
		String hashPassword1 = passwordEncoder.encode((CharSequence) "123");
		String hashMail1 = passwordEncoder.encode( (CharSequence) "sam@mail.com");
		User creatUser1 = new User("Sam", "TODD1245", hashMail1, hashPassword1, 50.00, userList, userList, userListTansaction, userListTansaction, null);
		User user2 = userDAO.save(creatUser1);
		
		int userId1 = user1.getId();
		int userId2 = user2.getId();
		Double amount = 10.00;
		Double feeBuddy = amount * 0.005;
		Double amountWallet = amount - feeBuddy;

		Transaction transaction = new Transaction(userId1, userId2, amountWallet, "test transaction");
		transactionDAO.save(transaction);
		List<Transaction> transactionOutList = user1.getTransactionOut();
		transactionOutList.add(transaction);
		userDAO.save(user1);
		
		User getFromUser = userDAO.getOne(userId1);
		Double getWalletFrom = getFromUser.getWallet();
		
		if(getWalletFrom < amount) {
		}else {
			Double updateWalletFrom = getWalletFrom - amount;
			getFromUser.setWallet(updateWalletFrom);
			userDAO.save(getFromUser);
			
			User getToUser = userDAO.getOne(userId2);
			Double updateWalletTo = getToUser.getWallet() + amountWallet;
			getToUser.setWallet(updateWalletTo);
			userDAO.save(getToUser);
		}
		
		List<Transaction> transaction1 = user1.getTransactionOut();
		
		assertEquals(9.95, transaction1.get(0).getAmount());
	}

}
