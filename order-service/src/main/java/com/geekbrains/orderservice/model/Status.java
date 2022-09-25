package com.geekbrains.orderservice.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "statuses")
public class Status {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;
}
