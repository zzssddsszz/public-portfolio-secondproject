package com.javatechie.os.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.os.api.common.Payment;
import com.javatechie.os.api.common.TransactionRequest;
import com.javatechie.os.api.common.TransactionResponse;
import com.javatechie.os.api.entitiy.Order;
import com.javatechie.os.api.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    @Lazy
    private RestTemplate template;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    private Logger logger = LoggerFactory.getLogger(OrderService.class);


    public TransactionResponse saveOrder (TransactionRequest transactionRequest) throws JsonProcessingException {
        String response = "";

        Order order = transactionRequest.getOrder();
        Payment payment = transactionRequest.getPayment();

        payment.setOrderId(order.getId());  // sets payment id from order id
        payment.setAmount(order.getPrice());    // sets payment price from order price

        // writeValueAsString() is to get the JSON string representation of an object, returns the generated JSON as a string
        logger.info("OrderService request : {}", new ObjectMapper().writeValueAsString(transactionRequest));


        // rest call
        Payment paymentResponse = template.postForObject(ENDPOINT_URL, payment, Payment.class);

        logger.info("Payment-service response from OrderService rest call : {}", new ObjectMapper().writeValueAsString(paymentResponse));
        // messages pop out if the payment_status is success nor payment_status is unsuccessful
        response = paymentResponse.getPaymentStatus().equals("success")?"payment processing successful and order placed":
                                                             "there is a failure in payment api, order added to cart";
        orderRepository.save(order);
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
    }
}
