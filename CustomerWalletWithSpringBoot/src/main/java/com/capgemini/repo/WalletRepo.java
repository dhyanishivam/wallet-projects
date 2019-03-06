package com.capgemini.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.beans.Customer;

@Repository
public interface WalletRepo extends JpaRepository<Customer, String>{

}
