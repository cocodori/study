package com.jpabook.ch05.realExample;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity @Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setMember(Member member) {
        //원래 관계 끊고
        if (Objects.nonNull(member))
            this.member.getOrders().remove(this);

        this.member = member;
        this.member.getOrders().add(this);
    }
}
