package com.buddy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.buddy.model.BankAccount;
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
	
	
	
	

}
