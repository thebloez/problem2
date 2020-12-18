package com.football.api.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name = "history_club_player")
public class HistoryClubPlayer implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Long playerId;
    private String clubCode;
    private boolean status;
}
