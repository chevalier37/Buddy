package com.buddy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tranfert_bank_account")
public class BankAccount implements Serializable{

	public BankAccount(int user_id, Double amount, Boolean type) {
		this.user_id = user_id;
		this.amount = amount;
		this.type = type;
	}
	
	public BankAccount() {
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_id", nullable = false)
    private int user_id;
	
	@Column(name = "amount", nullable = false)
    private Double amount;
	
	@Column(name = "type", nullable = false)
    private Boolean type;

	public int getId() {
		return id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", user_id=" + user_id + ", amount=" + amount + ", type=" + type + "]";
	}
	

}
