package io.datajek.tennisplayerrest.controller;

import io.datajek.tennisplayerrest.data.dto.request.CategoryRequest;
import io.datajek.tennisplayerrest.data.dto.request.RegisterRequest;
import io.datajek.tennisplayerrest.data.dto.request.TournamentRequest;
import io.datajek.tennisplayerrest.data.dto.response.TournamentResponse;
import io.datajek.tennisplayerrest.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {
    @Autowired
    private TournamentService service;

    @PostMapping("/tournaments")
    public TournamentResponse addTournament(@RequestBody TournamentRequest request){
        return service.addTournament(request);
    }

    @GetMapping("/tournaments/{id}")
    public TournamentResponse getTournament(TournamentRequest request){
        return service.getTournament(request);
    }

    @GetMapping("/tournaments")
    public List<TournamentResponse> getAllTournaments(){
        return service.getAllTournaments();
    }

    @PatchMapping("/tournaments/{id}")
    public TournamentResponse updateTournament(@RequestBody TournamentRequest request){
        return service.updateTournament(request);
    }

    @PostMapping("/{id}/registrations")
    public String addRegistration(RegisterRequest request){
        return service.addRegistration(request);
    }

    @PostMapping("/{id}/categories")
    public String addCategory(CategoryRequest request){
        return service.addCategory(request);
    }

    @DeleteMapping("/{id}/registrations")
    public String deleteRegistration(RegisterRequest request){
        return service.removeRegistration(request);
    }

    @DeleteMapping("/{id}/categories")
    public String removeCategory(CategoryRequest request){
        return service.removeCategory(request);
    }
}
