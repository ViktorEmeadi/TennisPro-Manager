package io.datajek.tennisplayerrest.service;

import io.datajek.tennisplayerrest.data.dto.request.PlayerProfileRequest;
import io.datajek.tennisplayerrest.data.dto.response.PlayerProfileResponse;
import io.datajek.tennisplayerrest.data.dto.response.PlayerResponse;
import io.datajek.tennisplayerrest.data.model.PlayerProfile;
import io.datajek.tennisplayerrest.data.repository.PlayerProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerProfileServiceImplTest {

    PlayerProfileRepository repo;
    PlayerProfileService service;

    @BeforeEach
    void init(){
        repo = mock(PlayerProfileRepository.class);
        service = new PlayerProfileServiceImpl(repo);
    }

    @Test
    void getAllProfilesTest(){
        PlayerProfile profile1 = PlayerProfile.builder()
                .id(1)
                .email("veetor@gmail.com")
                .build();
        PlayerProfile profile2 = PlayerProfile.builder()
                .id(2)
                .email("gk@gmail.com")
                .build();

        List<PlayerProfile> profiles = new ArrayList<>();
        profiles.add(profile1);
        profiles.add(profile2);

        when(repo.findAll()).thenReturn(profiles);

        List<PlayerProfileResponse> responses = service.getAllPlayerProfile();

        assertEquals(profiles.get(0).getId(), responses.get(0).getId());
        assertEquals(profiles.get(1).getId(), responses.get(1).getId());
    }

    @Test
    void getPlayerProfileId(){
        PlayerProfile profile = PlayerProfile.builder()
                .id(1)
                .email("vee@gmail.com")
                .build();

        when(repo.findById(1)).thenReturn(Optional.ofNullable(profile));

        PlayerProfileRequest request = PlayerProfileRequest.builder().id(1).build();
        PlayerProfileResponse response = service.getPlayerProfile(request);

        assertEquals(1, response.getId());
        assertEquals("vee@gmail.com", response.getEmail());
    }

    @Test
    void addPlayerProfileTest(){
        PlayerProfileRequest request =  PlayerProfileRequest.builder()
                .email("gg@gmail.com")
                .build();
        when(repo.save(any(PlayerProfile.class))).thenAnswer(invocationOnMock -> {
            PlayerProfile savedProfile = invocationOnMock.getArgument(0);
            savedProfile.setId(1);
            return savedProfile;
        });

        PlayerProfileResponse response = service.addPlayerProfile(request);

        assertEquals(1, response.getId());
    }

    @Test
    void updatePlayerProfileTest(){
        PlayerProfile profile = PlayerProfile.builder()
                .id(3)
                .email("jon@gmail.com")
                .build();

        when(repo.save(profile)).thenReturn(profile);
        when(repo.findById(1)).thenReturn(Optional.ofNullable(profile));

        PlayerProfileRequest req = PlayerProfileRequest.builder().id(1).email("tor@gmail.com").build();
        PlayerProfileResponse response = service.updatePlayerProfile(req);

        assertEquals(3, response.getId());
        assertEquals("tor@gmail.com", response.getEmail());
    }
}