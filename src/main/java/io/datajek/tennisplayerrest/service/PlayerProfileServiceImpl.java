package io.datajek.tennisplayerrest.service;

import io.datajek.tennisplayerrest.data.dto.request.PlayerProfileRequest;
import io.datajek.tennisplayerrest.data.dto.response.PlayerProfileResponse;
import io.datajek.tennisplayerrest.data.dto.response.PlayerResponse;
import io.datajek.tennisplayerrest.data.model.PlayerProfile;
import io.datajek.tennisplayerrest.data.repository.PlayerProfileRepository;
import io.datajek.tennisplayerrest.exception_handling.PlayerNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PlayerProfileServiceImpl implements PlayerProfileService{
    private final PlayerProfileRepository repo;

    public PlayerProfileServiceImpl(PlayerProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<PlayerProfileResponse> getAllPlayerProfile() {
        List<PlayerProfileResponse> responses = new ArrayList<>();

        List<PlayerProfile> profiles = repo.findAll();

        profiles.forEach(profile -> {
            PlayerProfileResponse response = PlayerProfileResponse.builder()
                    .id(profile.getId())
                    .email(profile.getEmail())
                    .build();

            responses.add(response);
        });
        return responses;
    }

    @Override
    public PlayerProfileResponse getPlayerProfile(PlayerProfileRequest request) {
        Optional<PlayerProfile> tempProfile = repo.findById(request.getId());
        PlayerProfileResponse response = new PlayerProfileResponse();

        if (tempProfile.isEmpty())
            throw new PlayerNotFoundException("Player Profile with ID "+ request.getId() + " not found");

        BeanUtils.copyProperties(tempProfile.get(), response);
        return response;
    }

    @Override
    public PlayerProfileResponse addPlayerProfile(PlayerProfileRequest request) {
        PlayerProfileResponse response = new PlayerProfileResponse();

        PlayerProfile profile = PlayerProfile.builder()
                .id(0)
                .email(request.getEmail())
                .build();
        repo.save(profile);

        BeanUtils.copyProperties(profile, response);

        return response;
    }

    @Override
    public PlayerProfileResponse updatePlayerProfile(PlayerProfileRequest request) {
        Optional<PlayerProfile> tempProfile = repo.findById(request.getId());
        PlayerProfileResponse response = new PlayerProfileResponse();

        if (tempProfile.isEmpty())
            throw new PlayerNotFoundException("Player Profile with ID "+ request.getId() + " not found");

        tempProfile.get().setEmail(request.getEmail());

        repo.save(tempProfile.get());
        BeanUtils.copyProperties(tempProfile.get(), response);

        return response;
    }

    @Override
    public void deletePlayerProfile(PlayerProfileRequest request) {
        repo.deleteById(request.getId());
    }
}
