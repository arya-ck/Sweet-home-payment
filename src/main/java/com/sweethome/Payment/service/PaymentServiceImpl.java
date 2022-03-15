package com.sweethome.Payment.service;

import com.sweethome.Payment.entity.TransactionDetailsEntity;
import com.sweethome.Payment.respository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    TransactionRepository transactionRepository;

    public TransactionDetailsEntity processPayment(TransactionDetailsEntity transactionDetailsEntity){

        //  save the new transaction to repository and return the transaction entity
        TransactionDetailsEntity savedPayment = transactionRepository.save(transactionDetailsEntity);
        return savedPayment;
    }

    public TransactionDetailsEntity getPaymentDetails(int transactionId){

        // get details from repository and return
        TransactionDetailsEntity paymentDetails = transactionRepository.findById(transactionId).get();
        return paymentDetails;
    }
}
