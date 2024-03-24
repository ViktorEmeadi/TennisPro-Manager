package io.datajek.tennisplayerrest.service;

import io.datajek.tennisplayerrest.data.dto.request.CategoryRequest;
import io.datajek.tennisplayerrest.data.dto.request.RegisterRequest;
import io.datajek.tennisplayerrest.data.dto.request.TournamentRequest;
import io.datajek.tennisplayerrest.data.dto.response.TournamentResponse;
import io.datajek.tennisplayerrest.data.model.Category;
import io.datajek.tennisplayerrest.data.model.Registration;
import io.datajek.tennisplayerrest.data.model.Tournament;
import io.datajek.tennisplayerrest.data.repository.TournamentRepository;
import io.datajek.tennisplayerrest.exception_handling.PlayerNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentServiceImpl implements TournamentService{
    private final TournamentRepository repo;
    @Autowired
    private RegistrationService regService;
    @Autowired
    private CategoryService catService;

    public TournamentServiceImpl(TournamentRepository repo) {
        this.repo = repo;
    }

    @Override
    public TournamentResponse addTournament(TournamentRequest request) {
        TournamentResponse response = new TournamentResponse();

        Tournament tournament = Tournament.builder()
                .id(0)
                .date(request.getDate())
                .name(request.getName())
                .location(request.getLocation())
                .build();

        repo.save(tournament);
        BeanUtils.copyProperties(tournament, response);

        return response;
    }

    @Override
    public TournamentResponse getTournament(TournamentRequest request) {
        Optional<Tournament> tempTournament = repo.findById(request.getId());
        TournamentResponse response = new TournamentResponse();

        if (tempTournament.isEmpty())
            throw new PlayerNotFoundException("Tournament wiht ID "+ request.getId() +" not found");

        BeanUtils.copyProperties(tempTournament.get(), response);

        return response;
    }

    @Override
    public List<TournamentResponse> getAllTournaments() {
        List<TournamentResponse> responses = new ArrayList<>();

        repo.findAll().forEach(tournament -> {
            TournamentResponse response = TournamentResponse.builder()
                    .id(tournament.getId())
                    .location(tournament.getLocation())
                    .name(tournament.getName())
                    .categories(tournament.getCategories())
                    .build();

            responses.add(response);
        });

        return responses;
    }

    @Override
    public TournamentResponse updateTournament(TournamentRequest request) {
        Optional<Tournament> tempT = repo.findById(request.getId());
        TournamentResponse response = new TournamentResponse();

        if (tempT.isEmpty())
            throw new PlayerNotFoundException("Tournament wiht ID "+ request.getId() +" not found");

        tempT.get().setName(request.getName());
        tempT.get().setDate(request.getDate());
        tempT.get().setLocation(request.getLocation());
        repo.save(tempT.get());

        BeanUtils.copyProperties(tempT.get(), response);

        return response;
    }

    @Override
    public void deleteTournament(TournamentRequest request) {
        repo.deleteById(request.getId());
    }

    @Override
    public String addRegistration(RegisterRequest request) {
        Registration registration = regService.getRegistration(request);
        Optional<Tournament> tournament = repo.findById(request.getTournamentId());

        if (tournament.isEmpty())
            throw new PlayerNotFoundException("Tournament wiht ID "+ request.getId() +" not found");

        tournament.get().getRegistrations().add(registration);

        return "Successfully registered";
    }

    @Override
    public String removeRegistration(RegisterRequest request) {
        Registration registration = regService.getRegistration(request);
        Optional<Tournament> tournament = repo.findById(request.getTournamentId());

        if (tournament.isEmpty())
            throw new PlayerNotFoundException("Tournament wiht ID "+ request.getId() +" not found");

        tournament.get().getRegistrations().remove(registration);

        return "Successfully removed registration";
    }

    @Override
    public String addCategory(CategoryRequest request) {
        Category category = catService.getCategory(request);
        Optional<Tournament> tournament = repo.findById(request.getTournamentId());

        if (tournament.isEmpty())
            throw new PlayerNotFoundException("Tournament wiht ID "+ request.getId() +" not found");

        tournament.get().getCategories().add(category);

        return "Category successfully added to tournament";
    }

    @Override
    public String removeCategory(CategoryRequest request) {
        Category category = catService.getCategory(request);
        Optional<Tournament> tournament = repo.findById(request.getTournamentId());

        if (tournament.isEmpty())
            throw new PlayerNotFoundException("Tournament wiht ID "+ request.getId() +" not found");

        tournament.get().getCategories().remove(category);

        return "Category successfully removed from tournament";
    }
}
