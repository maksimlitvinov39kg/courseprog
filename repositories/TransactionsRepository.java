package com.example.courseprog.Repositories;

import com.example.courseprog.Tables.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    ArrayList<Transactions> findAllByMccCodeAndTranType(int mcc,int type);
}
