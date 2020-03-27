package com.buddy.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buddy.model.Connection;
import com.buddy.model.Transaction;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Integer>{

}
