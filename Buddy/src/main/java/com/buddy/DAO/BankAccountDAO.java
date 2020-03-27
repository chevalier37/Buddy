package com.buddy.DAO;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buddy.model.BankAccount;
import com.buddy.model.User;

@Repository
public interface BankAccountDAO extends JpaRepository<BankAccount, Integer>{
	


}
