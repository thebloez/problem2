package com.football.api.demo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "player")
@Setter
@Getter
public class Player extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String playerName;
    private String pob;
    private String foot;
    @Basic
    private Date dob;
    private int height;
    private int weight;
    private String clubCode;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PLAYER")
    Set<PlayerPosition> positionList = new HashSet<>();
}