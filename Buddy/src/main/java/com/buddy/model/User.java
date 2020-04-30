package com.buddy.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable{

	private static final long serialVersionUID = 5217679369679514730L;


	public User() {
	}

	public User(String firstname, String lastname, String email, String password, Double wallet, List<User> myFriends,
			List<User> friendsOf, List<Transaction> transactionOut, List<Transaction> transactionIn,
			List<BankAccount> transfertBankAccount) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.wallet = wallet;
		this.myFriends = myFriends;
		this.friendsOf = friendsOf;
		this.transactionOut = transactionOut;
		this.transactionIn = transactionIn;
		this.transfertBankAccount = transfertBankAccount;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "firstname", nullable = false)
    private String firstname;
	
	@Column(name = "lastname", nullable = false)
    private String lastname;
	
	@Column(name = "email", nullable = false)
    private String email;
	
	@Column(name = "password", nullable = false)
    private String password;
	
	@Column(name = "wallet")
    private Double wallet;
	
	@ManyToMany
	@JoinTable(
			  name = "connections", 
			  joinColumns = @JoinColumn(name = "from_id"), 
			  inverseJoinColumns = @JoinColumn(name = "to_id"))
    private List<User> myFriends;
	
	@ManyToMany
	@JoinTable(
			  name = "connections", 
			  joinColumns = @JoinColumn(name = "to_id"), 
			  inverseJoinColumns = @JoinColumn(name = "from_id"))
    private List<User> friendsOf;
	
	@OneToMany
	@JoinTable(
			  name = "transaction", 
			  joinColumns = @JoinColumn(name = "from_id"))
    private List<Transaction> transactionOut;	
	
	@OneToMany
	@JoinTable(
			  name = "transaction", 
			  joinColumns = @JoinColumn(name = "to_id"))
    private List<Transaction> transactionIn;
	
	@OneToMany
	@JoinTable(
			  name = "tranfert_bank_account", 
			  joinColumns = @JoinColumn(name = "user_id"))
    private List<BankAccount> transfertBankAccount;


	public List<Transaction> getTransactionOut() {
		return transactionOut;
	}



	public void setTransactionOut(List<Transaction> transactionOut) {
		this.transactionOut = transactionOut;
	}



	public List<Transaction> getTransactionIn() {
		return transactionIn;
	}



	public void setTransactionIn(List<Transaction> transactionIn) {
		this.transactionIn = transactionIn;
	}



	public List<BankAccount> getTransfertBankAccount() {
		return transfertBankAccount;
	}



	public void setTransfertBankAccount(List<BankAccount> transfertBankAccount) {
		this.transfertBankAccount = transfertBankAccount;
	}



	public List<User> getMyFriends() {
		return myFriends;
	}

	public void setMyFriends(List<User> myFriends) {
		this.myFriends = myFriends;
	}

	public List<User> getFriendsOf() {
		return friendsOf;
	}

	public void setFriendsOf(List<User> friendsOf) {
		this.friendsOf = friendsOf;
	}

	public int getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getWallet() {
		return wallet;
	}

	public void setWallet(Double wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", wallet=" + wallet + "]";
	}

	



}
