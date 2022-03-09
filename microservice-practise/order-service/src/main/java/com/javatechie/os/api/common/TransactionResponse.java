package com.javatechie.os.api.common;

import com.javatechie.os.api.entitiy.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private Order order;
    private double amount;
    private String transactionId;

    private String message;
}

//    payment class entities
//    private int paymentId;
//    private String paymentStatus;
//    private String transactionId;
//
//    private int orderId;
//    private double amount;
