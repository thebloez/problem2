package com.football.api.demo;

import com.football.api.demo.model.Club;
import com.football.api.demo.repository.ClubRepository;
import com.football.api.demo.service.FootballService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ClubUnitTest {

    @InjectMocks
    private FootballService footballService;

    @Mock
    private ClubRepository clubRepository;

    @Test
    public void retrievlAllClubPlayers(){
        Mockito.when(clubRepository.findAll()).thenReturn(Arrays.asList(
                new Club("LFC"),
                new Club("PSG"),
                new Club("MAN")
        ));

        List<Club> allClub = clubRepository.findAll();

        Assert.assertEquals("LFC", allClub.get(0).getCode());
        Assert.assertEquals("PSG", allClub.get(1).getCode());
        Assert.assertEquals("MAN", allClub.get(2).getCode());
    }


}
