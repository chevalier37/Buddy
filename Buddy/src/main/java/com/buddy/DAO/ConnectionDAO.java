package com.buddy.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buddy.model.BankAccount;
import com.buddy.model.Connection;

@Repository
public interface ConnectionDAO extends JpaRepository<Connection, Integer>{

}
