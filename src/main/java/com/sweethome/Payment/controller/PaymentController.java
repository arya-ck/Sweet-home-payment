package com.sweethome.Payment.controller;

import com.sweethome.Payment.dto.TransactionInfoDTO;
import com.sweethome.Payment.entity.TransactionDetailsEntity;
import com.sweethome.Payment.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentServiceImpl paymentService;

    @PostMapping("/transaction")
    public ResponseEntity processPayment(@RequestBody TransactionInfoDTO transactionInfoDTO){

        // create a new transaction
        TransactionDetailsEntity detailsEntity = new TransactionDetailsEntity();
        detailsEntity.setPaymentMode(transactionInfoDTO.getPaymentMode());
        detailsEntity.setBookingId(transactionInfoDTO.getBookingId());
        detailsEntity.setUpiId(transactionInfoDTO.getUpiId());
        detailsEntity.setCardNumber(transactionInfoDTO.getCardNumber());

        // process the transaction
        TransactionDetailsEntity savedTransaction = this.paymentService.processPayment(detailsEntity);

        // return transaction id
        return new ResponseEntity(savedTransaction.getTransactionId(), HttpStatus.CREATED);
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity processPayment(@PathVariable int transactionId) {

        // retrieve transaction details from database
        TransactionDetailsEntity detailsEntity = paymentService.getPaymentDetails(transactionId);

        // initialize payment dto
        TransactionInfoDTO transactionInfoDTO = new TransactionInfoDTO();
        transactionInfoDTO.setTransactionId(transactionId);
        transactionInfoDTO.setPaymentMode(detailsEntity.getPaymentMode());
        transactionInfoDTO.setUpiId(detailsEntity.getUpiId());
        transactionInfoDTO.setCardNumber(detailsEntity.getCardNumber());

        // return details
        return new ResponseEntity(transactionInfoDTO, HttpStatus.OK);
    }
}
