package com.jpabook.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;  //재고

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public void add(Item item) {
    }
}
