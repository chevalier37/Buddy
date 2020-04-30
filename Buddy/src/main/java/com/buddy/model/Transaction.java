package com.buddy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 5980899126871598583L;

	public Transaction(int fromId, int toId, Double amount, String description) {
		this.fromId = fromId;
		this.toId = toId;
		this.amount = amount;
		this.description = description;
	}
	
	public Transaction() {
	
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "from_id", nullable = false)
    private int fromId;
	
	@Column(name = "to_id", nullable = false)
    private int toId;
	
	@Column(name = "amount", nullable = false)
    private Double amount;
	
	@Column(name = "description", nullable = false)
    private String description;

	public int getId() {
		return id;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
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
		return "Transaction [id=" + id + ", from_id=" + fromId + ", to_id=" + toId + ", amount=" + amount
				+ ", description=" + description + "]";
	}


}
