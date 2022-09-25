package com.geekbrains.cartservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long order_id;

    @Column(name = "title")
    private String title;

    @Column(name = "count")
    private int count;

    @Column(name = "price")
    private Double price;

    @Column(name = "sum")
    private Double sum;

    public OrderItem(Long order_id, String title, int count, Double price, Double sum) {
        this.order_id = order_id;
        this.title = title;
        this.count = count;
        this.price = price;
        this.sum = sum;
    }
}
