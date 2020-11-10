package com.jpabook.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Table(name = "ORDER_ITEM")
@Entity
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @Column(name = "ITEM_ID")
    private String itemId;

    @Column(name = "ORDER_ID")
    private String orderId;

    private int orderPrice;
    private int count;
}
