package com.football.api.demo.controller;

import com.football.api.demo.AppConstants;
import com.football.api.demo.model.Club;
import com.football.api.demo.payload.PagedResponse;
import com.football.api.demo.repository.ClubRepository;
import com.football.api.demo.service.FootballService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/club")
public class ClubController {

    private ClubRepository clubRepository;
    private FootballService footballService;

    public ClubController(ClubRepository clubRepository, FootballService footballService) {
        this.clubRepository = clubRepository;
        this.footballService = footballService;
    }

    @GetMapping("/all")
    public Page<Club> listClub(Pageable page){
       return clubRepository.findAll(page);
    }

    @GetMapping("/withplayers")
    public PagedResponse<Club> listClubWithPlayers(@RequestParam(value = "page",
            defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                   @RequestParam(value = "size",
                                                           defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size){
        return footballService.getAllTeamPlayer(page, size);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> createClub(@Validated @RequestBody Club club){
        footballService.createClub(club);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(club.getCode()).toUri();
        return ResponseEntity.created(location).build();
    }
}
