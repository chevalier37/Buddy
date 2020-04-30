package com.buddy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.model.Transaction;


@Transactional
@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Integer>{

}
