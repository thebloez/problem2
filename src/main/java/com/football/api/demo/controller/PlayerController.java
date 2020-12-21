package com.football.api.demo.controller;

import com.football.api.demo.AppConstants;
import com.football.api.demo.exception.ResourceNotFoundException;
import com.football.api.demo.model.Player;
import com.football.api.demo.payload.PagedResponse;
import com.football.api.demo.payload.request.EnlistRequest;
import com.football.api.demo.repository.ClubRepository;
import com.football.api.demo.repository.PlayerRepository;
import com.football.api.demo.service.FootballService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/player")
public class PlayerController {

    private PlayerRepository playerRepository;

    private ClubRepository clubRepository;

    private FootballService footballService;

    public PlayerController(PlayerRepository playerRepository, ClubRepository clubRepository, FootballService footballService) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
        this.footballService = footballService;
    }

    @GetMapping("/all")
    public PagedResponse<Player> getAllPlayers(@RequestParam(value = "page",
            defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                               @RequestParam(value = "size",
                                                       defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size){
        return footballService.getAllPlayers(page, size);
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
