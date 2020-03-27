package com.buddy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.buddy.model.BankAccount;
import com.buddy.model.Connection;
import com.buddy.model.User;

@SpringBootTest
public class BuddyTest {
	
	@Test
	@DisplayName("register user")
	public void registerUserTest() {
		User userTest = new User("Sam", "TODD", "s.todd@gmail.com", "123", 0);
		addUser(userTest);
		
		User findUser = findUser(userTest);
		
		assertEquals(userTest, findUser);
	}
	
	@Test
	@DisplayName("login user")
	public void loginUserTest() {
		User userTest = new User("Sam", "TODD", "s.todd@gmail.com", "123", 0);
		addUser(userTest);
		
		User findUser = findUser(userTest);
		String mail = findUser.getEmail();
		String password = findUser.getPassword();
		
		assertEquals(mail, "s.todd@gmail.com");
		assertEquals(password, "123");
	}
	
	@Test
	@DisplayName("payIn")
	public void PayInUserTest() {
		User userTest = new User("Sam", "TODD", "s.todd@gmail.com", "123", 0);
		addUser(userTest);
		
		User findUser = findUser(userTest);
		int id = findUser.getId();
		
		BankAccount transfert = new BankAccount(id, 10, "payIn");
		int wallet = findUser.getWallet();
		findUser.setWallet(wallet + 10);	
		
		assertEquals(findUser.getWallet(), 10);
	}
	
	@Test
	@DisplayName("payOut")
	public void PayOutUserTest() {
		User userTest = new User("Sam", "TODD", "s.todd@gmail.com", "123", 0);
		addUser(userTest);
		
		User findUser = findUser(userTest);
		int id = findUser.getId();
		
		BankAccount transfert = new BankAccount(id, -10, "payOut");
		int wallet = findUser.getWallet();
		findUser.setWallet(wallet - 10);	
		
		assertEquals(findUser.getWallet(), 10);
	}
	
	@Test
	@DisplayName("add connexion")
	public void addConnexionTest() {
		User userTest1 = new User("Sam", "TODD", "s.todd@gmail.com", "123", 0);
		addUser(userTest1);
		User userTest2 = new User("Samy", "TODDy", "sy.todd@gmail.com", "1234", 0);
		addUser(userTest2);
		
		User findUser1 = findUser(userTest);
		int id1 = findUser1.getId();
		
		User findUser2 = findUser(userTest);
		int id2 = findUser2.getId();
		
		Connection connection = new Connection(id1, id2);
		
		Connection searchConnection = getConnection(id1);
		int getId2 = searchConnection.getTo_id();
		
		assertEquals(id2, getId2);
	}
	
	
	
	
	
	

}
