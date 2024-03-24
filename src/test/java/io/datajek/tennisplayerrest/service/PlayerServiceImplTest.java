package io.datajek.tennisplayerrest.service;

import io.datajek.tennisplayerrest.data.dto.request.PlayerRequest;
import io.datajek.tennisplayerrest.data.dto.response.PlayerResponse;
import io.datajek.tennisplayerrest.data.model.Player;
import io.datajek.tennisplayerrest.data.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PlayerServiceImplTest {
    PlayerRepository repo;
    PlayerService playerService;

    @BeforeEach
    void init() {
        repo = mock(PlayerRepository.class);
        playerService = new PlayerServiceImpl(repo);
    }

    @Test
    void getAllPlayersTest() {
        List<Player> players = new ArrayList<>();

        Player player1 = Player.builder()
                .id(1)
                .title(1)
                .name("Vee")
                .nationality("Nigerian")
                .build();

        Player player2 = Player.builder()
                .id(2)
                .title(3)
                .name("Dee")
                .nationality("Rwandan")
                .build();

        players.add(player1);
        players.add(player2);

        when(repo.findAll()).thenReturn(players);

        List<PlayerResponse> result = new ArrayList<>();
        players.stream().forEach(player -> {
            PlayerResponse response = new PlayerResponse();
            BeanUtils.copyProperties(player, response);
            result.add(response);
        });

        assertEquals(result, playerService.getAllPlayers());
    }

    @Test
    void getPlayerByIdTest() {
        Player player = Player.builder()
                .nationality("Nigerian")
                .name("Don")
                .title(3)
                .id(1)
                .build();
        PlayerRequest request = PlayerRequest.builder().id(1).build();

        when(repo.findById(1)).thenReturn(Optional.ofNullable(player));

        PlayerResponse response = playerService.getPlayerById(request);
        assertSame(player.getName(), response.getName());
    }

    @Test
    void addPlayerTest(){
        PlayerRequest playerRequest = new PlayerRequest();
        playerRequest.setTitle(5);
        playerRequest.setName("John Doe");
        playerRequest.setNationality("US");

        // Mock behavior of repo.save() method
        when(repo.save(any(Player.class))).thenAnswer(invocation -> {
            Player savedPlayer = invocation.getArgument(0);
            savedPlayer.setId(1); // Simulating that player has been saved with an ID
            return savedPlayer;
        });

        // Call the method under test
        PlayerResponse response = playerService.addPlayer(playerRequest);

        // Verify that repo.save() method is called with the correct argument
        verify(repo).save(any(Player.class));

        // Verify that the response object is correctly populated
        assertEquals(1, response.getId()); // Assuming getId() returns the ID set in the mock repo
        assertEquals(playerRequest.getTitle(), response.getTitle());
        assertEquals(playerRequest.getName(), response.getName());
        assertEquals(playerRequest.getNationality(), response.getNationality());
    }

    @Test
    void updatePlayerTest(){
        Player player = Player.builder()
                .nationality("Nigerian")
                .name("Don")
                .title(3)
                .id(1)
                .build();

        when(repo.save(player)).thenReturn(player);
        when(repo.findById(1)).thenReturn(Optional.of(player));

        PlayerRequest request = PlayerRequest.builder()
                .id(1)
                .name("Von")
                .build();

        PlayerResponse response = playerService.updatePlayer(request);
        assertEquals("Von", response.getName());
    }

}