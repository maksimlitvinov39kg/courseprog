package com.example.courseprog.Repositories;

import com.example.courseprog.Tables.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findByCustomerId(Long ID);
}

