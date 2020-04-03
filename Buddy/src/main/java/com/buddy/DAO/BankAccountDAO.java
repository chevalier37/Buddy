package com.buddy.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.model.BankAccount;
import com.buddy.model.User;

@Transactional
@Repository
public interface BankAccountDAO extends JpaRepository<BankAccount, Integer>{
	
	


}
