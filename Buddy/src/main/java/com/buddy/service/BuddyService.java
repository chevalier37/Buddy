package com.buddy.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.DAO.BankAccountDAO;
import com.buddy.DAO.TransactionDAO;
import com.buddy.DAO.UserDAO;
import com.buddy.model.BankAccount;
import com.buddy.model.Connection;
import com.buddy.model.Transaction;
import com.buddy.model.User;

@Service
@Transactional
public class BuddyService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BankAccountDAO bankAccountDAO;
	
	@Autowired
	private TransactionDAO transactionDAO;
	
	@Autowired
	private com.buddy.DAO.ConnectionDAO connectionDAO;
	
	public User addUser(String firstname, String lastname, String mail, String password, Double amount) {
		String hashPassword = passwordEncoder.encode((CharSequence) password);
		String hashMail = passwordEncoder.encode( (CharSequence) mail);
		User creatUser = new User(firstname, lastname, hashMail, hashPassword, amount, null);
		userDAO.save(creatUser);
		return creatUser;		
	}
	
	public User findUser(String firstname, String lastname) {
		return userDAO.getUserByFirstnameAndLastname(firstname, lastname);
	}
	
	
	public boolean isUserLogin(String mail, String password) {
		User user = userDAO.getUserByEmail(mail);
		String getPassword = user.getPassword();
		boolean isPasswordCorrect = passwordEncoder.matches(password, getPassword);
		if(isPasswordCorrect) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isUserExiste(String mail) {
		User user = userDAO.getUserByEmail(mail);
		if(user.getEmail().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	public User payIn(int userId, Double amount) {
		User user = userDAO.getOne(userId);
		Double newWallet = user.getWallet() + amount;
		user.setWallet(newWallet);
		userDAO.save(user);
		BankAccount  payIn = new BankAccount(userId, amount, true);
		bankAccountDAO.save(payIn);
		return user;
	}
	
	public User payOut(int userId, Double amount) {
		User user = userDAO.getOne(userId);
		Double amountOut = - amount;
		Double newWallet = user.getWallet() + amountOut;
		user.setWallet(newWallet);
		userDAO.save(user);
		BankAccount  payIn = new BankAccount(userId, amount, false);
		bankAccountDAO.save(payIn);
		return user;
	}
	
	public Connection addConnection(int fromId, int toId) {
		Connection connection = new Connection(fromId, toId);
		connectionDAO.save(connection);
		return connection;
	}
	
	public Connection findOneConnection(int fromId, int toId) {
		return connectionDAO.getConnectionByFromIdAndToId(fromId,toId);
	}	
	
	public List<User> getConnectionList(int fromId) {
		return connectionDAO.getConnectionList(fromId);
	}
	
	public Boolean addTransaction(int fromId, int toId, Double amount, String description) {
		Double feeBuddy = amount * 0.005;
		Double amountWallet = amount - feeBuddy;
		Transaction transaction = new Transaction(fromId, toId, amountWallet, description);
		transactionDAO.save(transaction);
		
		User getFromUser = userDAO.getOne(fromId);
		Double getWalletFrom = getFromUser.getWallet();
		
		if(getWalletFrom < amount) {
			return false;
		}else {
			Double updateWalletFrom = getWalletFrom - amount;
			getFromUser.setWallet(updateWalletFrom);
			userDAO.save(getFromUser);
			
			User getToUser = userDAO.getOne(toId);
			Double updateWalletTo = getToUser.getWallet() + amountWallet;
			getToUser.setWallet(updateWalletTo);
			userDAO.save(getToUser);
			return true;
		}
		
	}
	
	public List<Transaction> getTransactionList(int fromId){
		return transactionDAO.getTransactionList(fromId);
	}
	
	public void deleteAllUser() {
		userDAO.deleteAll();	
	}
	
	public void deleteAllbankAccount() {
		bankAccountDAO.deleteAll();	
	}
	
	public void deleteAllConnection() {
		connectionDAO.deleteAll();	
	}
	
	public void deleteAllTransaction() {
		transactionDAO.deleteAll();	
	}
		


}
