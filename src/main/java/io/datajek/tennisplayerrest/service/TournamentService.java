package io.datajek.tennisplayerrest.service;

import io.datajek.tennisplayerrest.data.dto.request.CategoryRequest;
import io.datajek.tennisplayerrest.data.dto.request.RegisterRequest;
import io.datajek.tennisplayerrest.data.dto.request.TournamentRequest;
import io.datajek.tennisplayerrest.data.dto.response.TournamentResponse;

import java.util.List;

public interface TournamentService {
    TournamentResponse addTournament(TournamentRequest request);
    TournamentResponse getTournament(TournamentRequest request);
    List<TournamentResponse> getAllTournaments();
    TournamentResponse updateTournament(TournamentRequest request);
    void deleteTournament(TournamentRequest request);
    String addRegistration(RegisterRequest request);
    String removeRegistration(RegisterRequest request);
    String addCategory(CategoryRequest request);
    String removeCategory(CategoryRequest request);
}
