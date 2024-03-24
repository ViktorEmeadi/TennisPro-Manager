package io.datajek.tennisplayerrest.service;

import io.datajek.tennisplayerrest.data.dto.request.TournamentRequest;
import io.datajek.tennisplayerrest.data.dto.response.TournamentResponse;
import io.datajek.tennisplayerrest.data.model.Tournament;
import io.datajek.tennisplayerrest.data.repository.TournamentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TournamentServiceImplTest {
    TournamentRepository repo;
    TournamentService service;

    @BeforeEach
    void init(){
        repo = mock(TournamentRepository.class);
        service = new TournamentServiceImpl(repo);
    }

    @Test
    void addTournamentTest(){
        TournamentRequest request = TournamentRequest.builder()
                .name("Mr Me")
                .location("Lagos")
                .build();

        when(repo.save(any(Tournament.class))).thenAnswer(invocationOnMock -> {
            Tournament saved = invocationOnMock.getArgument(0);
            saved.setId(1);
            return saved;
        });

        TournamentResponse response = service.addTournament(request);

        assertEquals(1, response.getId());
        assertEquals("Mr Me", response.getName());
        assertEquals("Lagos", response.getLocation());
    }

    @Test
    void getTournamentTest(){
        Tournament tournament = Tournament.builder()
                .name("Mr Me")
                .location("Nigeria")
                .id(1)
                .build();

        when(repo.findById(1)).thenReturn(Optional.ofNullable(tournament));

        TournamentRequest request = TournamentRequest.builder().id(1).build();
        TournamentResponse response = service.getTournament(request);

        assertEquals(1, response.getId());
        assertEquals("Nigeria", response.getLocation());
        assertEquals("Mr Me", response.getName());
    }

    @Test
    void getAllTournamentsTest(){
        Tournament tournament1 = Tournament.builder()
                .name("Mr Me")
                .location("Nigeria")
                .id(1)
                .build();

        Tournament tournament2 = Tournament.builder()
                .name("Mr Me")
                .location("Nigeria")
                .id(2)
                .build();
        List<Tournament> tournaments = new ArrayList<>();
        tournaments.add(tournament1);
        tournaments.add(tournament2);

        when(repo.findAll()).thenReturn(tournaments);

        List<TournamentResponse> responses = service.getAllTournaments();

        assertEquals(tournaments.get(0).getId(), responses.get(0).getId());
        assertEquals(tournaments.get(1).getId(), responses.get(1).getId());
    }

    @Test
    void updateTournamentTest(){
        Tournament tournament = Tournament.builder()
                .name("Mr Me")
                .location("Nigeria")
                .id(1)
                .build();

        when(repo.save(tournament)).thenReturn(tournament);
        when(repo.findById(1)).thenReturn(Optional.of(tournament));

        TournamentRequest request = TournamentRequest.builder()
                .id(1)
                .location("London")
                .name("Me")
                .build();

        TournamentResponse response = service.updateTournament(request);

        assertEquals(1, response.getId());
        assertEquals("Me", response.getName());
        assertEquals("London", response.getLocation());
    }

}