package com.javatechie.os.api.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "order_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private int id;
    private String name;
    private int quantity;
    private double price;
}
