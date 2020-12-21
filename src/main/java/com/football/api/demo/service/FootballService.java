package com.football.api.demo.service;

import com.football.api.demo.AppConstants;
import com.football.api.demo.exception.BadRequestException;
import com.football.api.demo.model.Club;
import com.football.api.demo.model.Player;
import com.football.api.demo.payload.PagedResponse;
import com.football.api.demo.repository.ClubRepository;
import com.football.api.demo.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class FootballService {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public void createClub(Club club){
        clubRepository.save(club);
    }

    public PagedResponse<Club> getAllTeamPlayer(int page, int size){
        validatePageSize(page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<Club> clubs = clubRepository.findAll(pageable);

        if (clubs.getNumberOfElements() == 0){
            return new PagedResponse<>(Collections.emptyList(), clubs.getNumber(),
                    clubs.getSize(), clubs.getTotalElements(), clubs.getTotalPages(), clubs.isLast());
        }

        return new PagedResponse<>(clubs.getContent(), clubs.getNumber(),
                clubs.getSize(), clubs.getTotalElements(), clubs.getTotalPages(), clubs.isLast());
    }

    public PagedResponse<Player> getAllPlayers(int page, int size){
        validatePageSize(page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<Player> players = playerRepository.findAll(pageable);

        if (players.getNumberOfElements() == 0){
            return new PagedResponse<>(Collections.emptyList(), players.getNumber(), players.getSize(),
                    players.getTotalElements(), players.getTotalPages(), players.isLast());
        }

        return new PagedResponse<>(players.getContent(), players.getNumber(), players.getSize(),
                players.getTotalElements(), players.getTotalPages(), players.isLast());
    }

    private void validatePageSize(int page, int size) {
        if(page < 0){
            throw new BadRequestException("Page number cannot be less than zero");
        }
        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }
}
