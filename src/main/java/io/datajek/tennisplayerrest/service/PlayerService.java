package io.datajek.tennisplayerrest.service;

import io.datajek.tennisplayerrest.data.dto.request.PlayerRequest;
import io.datajek.tennisplayerrest.data.dto.request.RegisterRequest;
import io.datajek.tennisplayerrest.data.dto.response.PlayerResponse;

import java.util.List;

public interface PlayerService {
    List<PlayerResponse> getAllPlayers();
    PlayerResponse getPlayerById(PlayerRequest playerRequest);
    PlayerResponse addPlayer(PlayerRequest playerRequest);
    PlayerResponse updatePlayer(PlayerRequest playerRequest);
    void deletePlayer(PlayerRequest playerRequest);
    void registerPlayer(RegisterRequest request);
}
