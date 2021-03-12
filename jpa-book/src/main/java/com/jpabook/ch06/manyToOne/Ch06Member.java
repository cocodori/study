package com.jpabook.ch06.manyToOne;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Ch06Member {
    @Id @GeneratedValue
    private Long id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Ch06Team team;

    public void setTeam(Ch06Team team) {
        this.team = team;
    }
}
