package com.football.api.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "player_position")
public class PlayerPosition implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    TODO 3: Create relation field with lazy
    private Long playerId;
    private String clubCode;
    private String status;
}
