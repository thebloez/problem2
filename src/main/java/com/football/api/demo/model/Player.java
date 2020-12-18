package com.football.api.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "player")
public class Player extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String playername;
    private String dob;
    private String pob;
    private String height;
    private String weight;
    private String citizenship;
    private String foot;
    private String position;
    // TODO 2 : create relation field with lazy
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "club_code", nullable = false)
    @JsonIgnore
    private Club club;
}
