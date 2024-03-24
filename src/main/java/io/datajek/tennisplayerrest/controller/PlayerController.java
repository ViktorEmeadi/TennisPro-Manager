package io.datajek.tennisplayerrest.controller;

import io.datajek.tennisplayerrest.data.dto.request.PlayerRequest;
import io.datajek.tennisplayerrest.data.dto.request.RegisterRequest;
import io.datajek.tennisplayerrest.data.dto.response.PlayerResponse;
import io.datajek.tennisplayerrest.service.PlayerService;
import io.datajek.tennisplayerrest.service.PlayerServiceImpl;
import io.datajek.tennisplayerrest.data.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//VERSION CONTROL

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService service;

    @PostMapping("/players")
    public PlayerResponse addPlayer(@RequestBody PlayerRequest request){
        return service.addPlayer(request);
    }

    @GetMapping("/players")
    public List<PlayerResponse> getAllPlayers(){
        return service.getAllPlayers();
    }

    @GetMapping("/players/{id}")
    public PlayerResponse getPlayer(PlayerRequest request){
        return service.getPlayerById(request);
    }

    @PatchMapping("/players/{id}")
    public PlayerResponse updatePlayer(@RequestBody PlayerRequest request){
        return service.updatePlayer(request);
    }

    @DeleteMapping("/players/{id}")
    public void deletePlayer(PlayerRequest request){
        service.deletePlayer(request);
    }

    @PutMapping("/{id}/registrations")
    public void registerPlayer(RegisterRequest request){
        service.registerPlayer(request);
    }
}
