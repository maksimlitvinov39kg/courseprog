package com.example.courseprog.Service;

import com.example.courseprog.Repositories.DispersionRepository;
import com.example.courseprog.Repositories.TransactionsRepository;
import com.example.courseprog.Tables.Dispersion;
import com.example.courseprog.Tables.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class DispersionService {
    private DispersionRepository dispersionRepository;
    private TransactionsRepository transactionsRepository;
    @Autowired
    public DispersionService(DispersionRepository dispersionRepository,TransactionsRepository transactionsRepository){
        this.dispersionRepository = dispersionRepository;
        this.transactionsRepository = transactionsRepository;
    }
    public List<Dispersion> getDispersion (){
    List<Dispersion> dispersionList = dispersionRepository.findAll();
    return dispersionList;
    }
    public double countDispersion (List<Transactions> transactionsList){
        int count = transactionsList.size();
        int sum = 0;
        for(Transactions transactions: transactionsList){
            sum +=transactions.getAmount();
        }
            double avg = sum/count;
        double deltaSum = 0 ;
        for (Transactions transactions:transactionsList){
            double t = transactions.getAmount() - avg;
            deltaSum +=t*t;
        }
        return(deltaSum/count);
    }
    public void addDespersion(Transactions transactions){
        int mccType =transactions.getMccCode()+ transactions.getTranType();
        Dispersion dispersion = dispersionRepository.findByMccType(mccType);
        double amount = 0;
        if(dispersion!= null){
            List<Transactions> transactionsList =transactionsRepository.findAllByMccCodeAndTranType(transactions.getMccCode(),
                    transactions.getTranType());
                if (transactionsList.size()>=10)
                    amount = countDispersion(transactionsList);
        }
        dispersionRepository.save(new Dispersion(mccType,amount));
    }
    public void calculateAll(){
        List<Transactions> transactionsList = transactionsRepository.findAll();
        for(Transactions transactions : transactionsList){
            addDespersion(transactions);
        }
    }

}
