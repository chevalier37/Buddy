package com.buddy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "connections")
public class Connection implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "from_id", nullable = false)
    private int from_id;
	
	@Column(name = "to_id", nullable = false)
    private int to_id;
	
	
	public Connection(int from_id, int to_id) {
		this.from_id = from_id;
		this.to_id = to_id;
	}
	
	public Connection() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	

}
