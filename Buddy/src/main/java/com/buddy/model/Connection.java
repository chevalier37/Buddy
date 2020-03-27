package com.buddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "connections")
public class Connection {

	public Connection(int id, int from_id, int to_id) {
		this.id = id;
		this.from_id = from_id;
		this.to_id = to_id;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "from_id", nullable = false)
    private int from_id;
	
	@Column(name = "to_id", nullable = false)
    private int to_id;

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

	@Override
	public String toString() {
		return "Connection [id=" + id + ", from_id=" + from_id + ", to_id=" + to_id + "]";
	}
	
	

}
