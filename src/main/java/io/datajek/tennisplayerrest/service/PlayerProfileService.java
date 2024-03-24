package io.datajek.tennisplayerrest.service;

import io.datajek.tennisplayerrest.data.dto.request.PlayerProfileRequest;
import io.datajek.tennisplayerrest.data.dto.response.PlayerProfileResponse;
import io.datajek.tennisplayerrest.data.dto.response.PlayerResponse;
import io.datajek.tennisplayerrest.data.model.PlayerProfile;

import java.util.List;

public interface PlayerProfileService {
    List<PlayerProfileResponse> getAllPlayerProfile();
    PlayerProfileResponse getPlayerProfile(PlayerProfileRequest request);
    PlayerProfileResponse addPlayerProfile(PlayerProfileRequest request);
    PlayerProfileResponse updatePlayerProfile(PlayerProfileRequest request);
    void deletePlayerProfile(PlayerProfileRequest request);
}
