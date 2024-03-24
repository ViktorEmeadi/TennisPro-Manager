package io.datajek.tennisplayerrest.controller;

import io.datajek.tennisplayerrest.data.dto.request.PlayerProfileRequest;
import io.datajek.tennisplayerrest.data.dto.response.PlayerProfileResponse;
import io.datajek.tennisplayerrest.service.PlayerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player-profiles")
public class PlayerProfileController {
    @Autowired
    private PlayerProfileService service;

    @PostMapping("/player-profiles")
    public PlayerProfileResponse addPlayerProfile(@RequestBody PlayerProfileRequest request){
        return service.addPlayerProfile(request);
    }

    @GetMapping("player-profiles/{id}")
    public PlayerProfileResponse getPlayerProfile(PlayerProfileRequest request){
        return service.getPlayerProfile(request);
    }

    @GetMapping("/player-profiles")
    public List<PlayerProfileResponse> getAllPlayerProfile(){
        return service.getAllPlayerProfile();
    }

    @PatchMapping("/player-profiles/{id}")
    public PlayerProfileResponse updatePlayerProfile(@RequestBody PlayerProfileRequest request){
        return service.updatePlayerProfile(request);
    }

    @DeleteMapping("/player-profiles/{id}")
    public void deletePlayerProfile(PlayerProfileRequest request){
        service.deletePlayerProfile(request);
    }
}
