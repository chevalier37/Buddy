package com.buddy.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.model.Connection;
import com.buddy.model.Transaction;
import com.buddy.model.User;

@Transactional
@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Integer>{
	
	@Query("SELECT t FROM Transaction t INNER JOIN User u ON t.to_id = u.id WHERE t.from_id = ?1")
	public List<Transaction> getTransactionList(int fromId);

}
