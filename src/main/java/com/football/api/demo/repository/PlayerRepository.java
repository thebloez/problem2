package com.football.api.demo.repository;

import com.football.api.demo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query("SELECT p FROM Player p LEFT JOIN FETCH p.positionList")
    List<Player> findWithoutNPlusOne();

    @Transactional
    @Modifying
    @Query("UPDATE Player p SET CLUB_CODE = :clubCode where ID = :playerId")
    int enlistPlayerIntoTeam(int playerId, String clubCode);
}
