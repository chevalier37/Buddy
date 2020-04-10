package com.buddy.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.buddy.model.Connection;
import com.buddy.model.User;

@Repository
public interface ConnectionDAO extends JpaRepository<Connection, Integer>{
	
	@Query("FROM Connection WHERE from_id = ?1 AND to_id =	?2 ")
	public Connection getConnectionByFromIdAndToId(int fromId, int toId);
	
	//@Query("SELECT u FROM User u INNER JOIN Connection c ON c.to_id = u.id WHERE c.from_id = ?1")
	//public List<User> getConnectionList(int fromId);
	
	@Query("SELECT u FROM User u INNER JOIN Connection c ON c.to_id = u.id WHERE c.from_id = ?1")
	public List<User> getConnectionList(int fromId);

}
