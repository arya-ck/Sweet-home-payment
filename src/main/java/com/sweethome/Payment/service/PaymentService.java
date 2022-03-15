package com.sweethome.Payment.service;

import com.sweethome.Payment.dto.TransactionInfoDTO;
import com.sweethome.Payment.entity.TransactionDetailsEntity;

public interface PaymentService {

    public TransactionDetailsEntity processPayment(TransactionDetailsEntity transactionDetailsEntity);
    public TransactionDetailsEntity getPaymentDetails(int transactionId);
}
