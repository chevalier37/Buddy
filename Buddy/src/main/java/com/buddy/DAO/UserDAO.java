package com.buddy.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.buddy.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{
	
	@Query("FROM User WHERE lastname =	?1 ")
	public User getUserByLastname(String lastname1);

}
