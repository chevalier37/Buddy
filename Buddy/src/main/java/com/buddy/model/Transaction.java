package com.buddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	public Transaction(int from_id, int to_id, Double amount, String description) {
		this.from_id = from_id;
		this.to_id = to_id;
		this.amount = amount;
		this.description = description;
	}
	
	public Transaction() {
	
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "from_id", nullable = false)
    private int from_id;
	
	@Column(name = "to_id", nullable = false)
    private int to_id;
	
	@Column(name = "amount", nullable = false)
    private Double amount;
	
	@Column(name = "description", nullable = false)
    private String description;

	public int getId() {
		return id;
	}

	public int getFrom_id() {
		return from_id;
	}

	public void setFrom_id(int from_id) {
		this.from_id = from_id;
	}

	public int getTo_id() {
		return to_id;
	}

	public void setTo_id(int to_id) {
		this.to_id = to_id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", from_id=" + from_id + ", to_id=" + to_id + ", amount=" + amount
				+ ", description=" + description + "]";
	}
	
	
	
	

}
