package com.football.api.demo.controller;

import com.football.api.demo.exception.ResourceNotFoundException;
import com.football.api.demo.model.Player;
import com.football.api.demo.payload.request.EnlistRequest;
import com.football.api.demo.repository.ClubRepository;
import com.football.api.demo.repository.PlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/player")
public class PlayerController {

    private PlayerRepository playerRepository;

    private ClubRepository clubRepository;

    public PlayerController(PlayerRepository playerRepository, ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
    }

    @GetMapping("/all")
    public Page<Player> getAllPlayers(Pageable page){
        return playerRepository.findAll(page);
    }

    @PostMapping(value = "/enlist")
    public Player addPlayerIntoTeam(@Validated @RequestBody EnlistRequest request){
        return playerRepository.findById(request.getPlayerId())
                .map(player -> {
                    clubRepository.findById(request.getClubCode())
                            .orElseThrow(() -> new ResourceNotFoundException("Club Not Found"));

                    player.setClubCode(request.getClubCode());
                    return playerRepository.save(player);
                }).orElseThrow(() -> new ResourceNotFoundException("Player not found"));
    }

    @GetMapping("/{playerId}")
    public Player getPlayer(@PathVariable int playerId){
        return playerRepository.findById(playerId).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
    }

    @PostMapping("/create")
    public Player createPlayer(@Validated @RequestBody Player request){
        return playerRepository.save(request);
    }


}
