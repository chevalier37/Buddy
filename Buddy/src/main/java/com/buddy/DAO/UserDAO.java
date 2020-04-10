package com.buddy.DAO;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.buddy.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{
	
	@Query("FROM User WHERE firstname = ?1 AND lastname =	?2 ")
	public User getUserByFirstnameAndLastname(String firstname, String lastname);
	
	@Query("FROM User WHERE email = ?1")
	public User getUserByEmail(String mail);
	
	@Query("SELECT u FROM User u INNER JOIN Connection c ON c.to_id = u.id WHERE c.from_id = ?1")
	public List<User> getConnectionList(int fromId);
	

}
