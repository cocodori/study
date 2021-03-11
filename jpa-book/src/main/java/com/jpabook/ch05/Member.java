package com.jpabook.ch05;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity @NoArgsConstructor
public class Member {
    @Id @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public void setTeam(Team team) {
        if (this.team != null)
            this.team.getMembers().remove(this);

        this.team = team;
        this.team.getMembers().add(this);
    }
}
