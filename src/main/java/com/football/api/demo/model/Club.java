package com.football.api.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "club")
public class Club implements Serializable {
    @Id
    private String code;
    private String clubname;
    private String basedCountry;
    private String regulationLeague;
//    TODO 1: Create relation field
    private String clubCode;
}
