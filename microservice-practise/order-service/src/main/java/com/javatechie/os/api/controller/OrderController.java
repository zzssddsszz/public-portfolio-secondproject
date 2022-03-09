package com.javatechie.os.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javatechie.os.api.common.Payment;
import com.javatechie.os.api.common.TransactionRequest;
import com.javatechie.os.api.common.TransactionResponse;
import com.javatechie.os.api.entitiy.Order;
import com.javatechie.os.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest) throws JsonProcessingException {
        // transactionRequest has a combination of Payment and Order entities
        return orderService.saveOrder(transactionRequest);
        // do a rest call to payment and pass the order id
    }

//    {
//        "order":{
//        "id":103,
//                "name":"mobile",
//                "quantity":1,
//                "price": 8000
//    },
//        "payment":{}
//    }


}
