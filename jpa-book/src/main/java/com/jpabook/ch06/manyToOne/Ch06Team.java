package com.jpabook.ch06.manyToOne;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
public class Ch06Team {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Ch06Member> members = new ArrayList<>();

    public void addMember(Ch06Member member) {
        this.members.add(member);

        //무한 루프 안 돌도록 체크
        if (member.getTeam() != this)
            member.setTeam(this);
    }
}
