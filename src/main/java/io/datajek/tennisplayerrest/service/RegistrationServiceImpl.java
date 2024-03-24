package io.datajek.tennisplayerrest.service;

import io.datajek.tennisplayerrest.data.dto.request.RegisterRequest;
import io.datajek.tennisplayerrest.data.dto.response.RegisterResponse;
import io.datajek.tennisplayerrest.data.model.MatchType;
import io.datajek.tennisplayerrest.data.model.Registration;
import io.datajek.tennisplayerrest.data.repository.RegistrationRepository;
import io.datajek.tennisplayerrest.exception_handling.PlayerNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class RegistrationServiceImpl implements RegistrationService{
    private final RegistrationRepository repo;

    public RegistrationServiceImpl(RegistrationRepository repo) {
        this.repo = repo;
    }

    @Override
    public RegisterResponse addRegistrationSingle(RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();

        Registration registration = Registration.builder()
                .date(Date.from(Instant.now()))
                .matchType(MatchType.SINGLES)
                .build();

        repo.save(registration);
        BeanUtils.copyProperties(registration, response);

        return response;
    }

    @Override
    public RegisterResponse addRegistrationDouble(RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();

        Registration registration = Registration.builder()
                .id(0)
                .date(Date.from(Instant.now()))
                .matchType(MatchType.DOUBLES)
                .build();

        repo.save(registration);
        BeanUtils.copyProperties(registration, response);

        return response;
    }

    @Override
    public List<RegisterResponse> getAllRegistrations() {
        List<RegisterResponse> responses = new ArrayList<>();

        repo.findAll().stream().forEach(response -> {
            RegisterResponse response1 = RegisterResponse.builder()
                    .id(response.getId())
                    .matchType(response.getMatchType())
                    .date(response.getDate())
                    .build();
            responses.add(response1);
        });

        return responses;
    }

    @Override
    public RegisterResponse getById(RegisterRequest request) {
        Optional<Registration> tempRegistration = repo.findById(request.getId());
        RegisterResponse response = new RegisterResponse();

        if (tempRegistration.isEmpty())
            throw new PlayerNotFoundException("Registration with ID "+ request.getId() + " not found");

        BeanUtils.copyProperties(tempRegistration.get(), response);
        return response;
    }

    @Override
    public RegisterResponse updateRegistration(RegisterRequest request) {
        Optional<Registration> reg = repo.findById(request.getId());
        RegisterResponse response = new RegisterResponse();

        if (reg.isEmpty())
            throw new PlayerNotFoundException("Registration with ID "+ request.getId() + " not found");

        reg.get().setMatchType(request.getMatchType());
        repo.save(reg.get());

        BeanUtils.copyProperties(reg.get(), response);

        return response;
    }

    @Override
    public Registration getRegistration(RegisterRequest request) {
        Optional<Registration> tempReg = repo.findById(request.getId());

        if (tempReg.isEmpty())
            throw new PlayerNotFoundException("Registration with id "+ request.getId()
                    + " not found");

        return tempReg.get();
    }

    @Override
    public void deleteRegistration(RegisterRequest request) {
        Optional<Registration> reg = repo.findById(request.getId());

        if (reg.isEmpty())
            throw new PlayerNotFoundException("Registration with ID "+ request.getId() + " not found");

        repo.deleteById(request.getId());
    }
}
