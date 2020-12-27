package com.jpabook.domain;

import com.jpabook.domain.item.Item;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    private int orderPrice;
    private int count;
}
