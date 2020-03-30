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
	
	public User addUser(String firstname, String lastname, String mail, String password, int amount) {
		String hashPassword = passwordEncoder.encode((CharSequence) password);
		//String hashMail = passwordEncoder.encode( (CharSequence) mail);
		User creatUser = new User(firstname, lastname, mail, hashPassword, amount, null);
		userDAO.save(creatUser);
		return creatUser;		
	}
	
	public User findUser(String firstname, String lastname) {
		return userDAO.getUserByFirstnameAndLastname(firstname, lastname);
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
	
	public User payIn(int userId, int amount) {
		User user = userDAO.getOne(userId);
		int newWallet = user.getWallet() + amount;
		user.setWallet(newWallet);
		userDAO.save(user);
		BankAccount  payIn = new BankAccount(userId, amount, "payIn");
		bankAccountDAO.save(payIn);
		return user;
	}
	
	public User payOut(int userId, int amount) {
		User user = userDAO.getOne(userId);
		int amountOut = - amount;
		int newWallet = user.getWallet() + amountOut;
		user.setWallet(newWallet);
		userDAO.save(user);
		BankAccount  payIn = new BankAccount(userId, amount, "payOut");
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
	
	public Boolean addTransaction(int fromId, int toId, int amount, String description) {
		Transaction transaction = new Transaction(fromId, toId, amount, description);
		transactionDAO.save(transaction);
		
		User getFromUser = userDAO.getOne(fromId);
		int getWalletFrom = getFromUser.getWallet();
		
		if(getWalletFrom < amount) {
			return false;
		}else {
			int updateWalletFrom = getWalletFrom - amount;
			getFromUser.setWallet(updateWalletFrom);
			userDAO.save(getFromUser);
			
			User getToUser = userDAO.getOne(toId);
			int updateWalletTo = getToUser.getWallet() + amount;
			getToUser.setWallet(updateWalletTo);
			userDAO.save(getToUser);
			return true;
		}
		
	}
	
	public List<Transaction> getTransactionList(int fromId){
		return transactionDAO.getTransactionList(fromId);
	}
		


}
