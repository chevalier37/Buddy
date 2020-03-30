package com.buddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccount {

	public BankAccount(int user_id, int amount, String type) {
		this.user_id = user_id;
		this.amount = amount;
		this.type = type;
	}
	
	public BankAccount() {
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "user_id", nullable = false)
    private int user_id;
	
	@Column(name = "amount", nullable = false)
    private int amount;
	
	@Column(name = "type", nullable = false)
    private String type;

	public int getId() {
		return id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", user_id=" + user_id + ", amount=" + amount + ", type=" + type + "]";
	}
	
	
	
	

}
