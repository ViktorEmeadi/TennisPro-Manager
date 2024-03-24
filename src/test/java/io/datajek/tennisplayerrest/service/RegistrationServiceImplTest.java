package io.datajek.tennisplayerrest.service;

import io.datajek.tennisplayerrest.data.dto.request.PlayerRequest;
import io.datajek.tennisplayerrest.data.dto.request.RegisterRequest;
import io.datajek.tennisplayerrest.data.dto.response.RegisterResponse;
import io.datajek.tennisplayerrest.data.model.MatchType;
import io.datajek.tennisplayerrest.data.model.Player;
import io.datajek.tennisplayerrest.data.model.Registration;
import io.datajek.tennisplayerrest.data.repository.RegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrationServiceImplTest {

    RegistrationRepository repo;
    RegistrationService regService;

    @BeforeEach
    void init(){
        repo = mock(RegistrationRepository.class);
        regService = new RegistrationServiceImpl(repo);
    }

    @Test
    void addRegistrationTest(){
        RegisterRequest req = RegisterRequest.builder()
                .matchType(MatchType.SINGLES)
                .build();

        when(repo.save(any(Registration.class))).thenAnswer(invocationOnMock -> {
            Registration savedReg = invocationOnMock.getArgument(0);
            savedReg.setId(1);
            return savedReg;
        });

        RegisterResponse response = regService.addRegistrationSingle(req);

        assertEquals(1, response.getId());
        assertEquals(MatchType.SINGLES, response.getMatchType());
    }

    @Test
    void getAllRegistrationsTest(){
        Registration reg1 = Registration.builder()
                .id(1)
                .matchType(MatchType.SINGLES)
                .build();
        Registration reg2 = Registration.builder()
                .id(2)
                .matchType(MatchType.DOUBLES)
                .build();

        List<Registration> registrations = new ArrayList<>();
        registrations.add(reg1);
        registrations.add(reg2);

        when(repo.findAll()).thenReturn(registrations);

        List<RegisterResponse> response = regService.getAllRegistrations();

        assertEquals(registrations.get(0).getId(), response.get(0).getId());
        assertEquals(registrations.get(1).getId(), response.get(1).getId());
    }

    @Test
    void getByIdTest(){
        Registration reg = Registration.builder()
                .id(1)
                .matchType(MatchType.DOUBLES)
                .build();

        when(repo.findById(1)).thenReturn(Optional.ofNullable(reg));

        RegisterRequest req = RegisterRequest.builder().id(1).build();
        RegisterResponse response = regService.getById(req);

        assertEquals(reg.getMatchType(), response.getMatchType());
        assertEquals(reg.getId(), response.getId());
    }

    @Test
    void updateRegistrationTest(){
        Registration reg = Registration.builder()
                .matchType(MatchType.SINGLES)
                .build();

        when(repo.save(reg)).thenReturn(reg);
        when(repo.findById(1)).thenReturn(Optional.of(reg));

        RegisterRequest request = RegisterRequest.builder().id(1).matchType(MatchType.DOUBLES).build();
        RegisterResponse response = regService.updateRegistration(request);

        assertEquals(MatchType.DOUBLES, response.getMatchType());
    }
}