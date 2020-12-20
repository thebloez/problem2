package com.football.api.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "club")
@AllArgsConstructor
@NoArgsConstructor
public class Club implements Serializable {
    @Id
    private String code;
    private String clubName;
    private String basedCountry;
    private String regulationLeague;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clubCode")
//    @JoinColumn(name = "club_code")
    private Set<Player> players;

    public Club(String code) {
        this.code = code;
    }
}
