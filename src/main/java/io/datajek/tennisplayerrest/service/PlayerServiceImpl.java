package io.datajek.tennisplayerrest.service;

import io.datajek.tennisplayerrest.data.dto.request.PlayerProfileRequest;
import io.datajek.tennisplayerrest.data.dto.request.PlayerRequest;
import io.datajek.tennisplayerrest.data.dto.request.RegisterRequest;
import io.datajek.tennisplayerrest.data.dto.response.PlayerProfileResponse;
import io.datajek.tennisplayerrest.data.dto.response.PlayerResponse;
import io.datajek.tennisplayerrest.data.model.Player;
import io.datajek.tennisplayerrest.data.model.Registration;
import io.datajek.tennisplayerrest.data.repository.PlayerRepository;
import io.datajek.tennisplayerrest.exception_handling.PlayerNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    private RegistrationService regService;
    private final PlayerRepository repo;

    public PlayerServiceImpl(PlayerRepository repo) {
        this.repo = repo;
    }

    public String deleteById(int id){
        try {
            repo.deleteById(id);
        }catch (Exception e){
            return "Player with id " + id + "not found";
        }
        return "Player with id " + id + " successfully deleted";

    }


    @Override
    public List<PlayerResponse> getAllPlayers() {
        List<PlayerResponse> responses = new ArrayList<>();
        List<Player> players = repo.findAll();

        players.stream().forEach((player -> {
            PlayerResponse response = new PlayerResponse();
            BeanUtils.copyProperties(player, response);
            responses.add(response);
        }));

        return responses;
    }

    @Override
    public PlayerResponse getPlayerById(PlayerRequest playerRequest) {
        Optional<Player> tempPlayer = repo.findById(playerRequest.getId());
        PlayerResponse response = new PlayerResponse();

        if (tempPlayer.isEmpty())
            throw new PlayerNotFoundException("Player with id "+ playerRequest.getId() +
                                            " not found");
        BeanUtils.copyProperties(tempPlayer.get(), response);
        return response;
    }

    @Override
    public PlayerResponse addPlayer(PlayerRequest playerRequest) {
        PlayerResponse response = new PlayerResponse();

        Player player = Player.builder()
                .id(0)
                .title(playerRequest.getTitle())
                .name(playerRequest.getName())
                .nationality(playerRequest.getNationality())
                .birthDate(playerRequest.getBirthDate())
                .build();
        repo.save(player);
        BeanUtils.copyProperties(player, response);

        return response;
    }

    @Override
    public PlayerResponse updatePlayer(PlayerRequest playerRequest) {
        Optional<Player> tempPlayer = repo.findById(playerRequest.getId());
        PlayerResponse response = new PlayerResponse();
        Player player = null;

        if (tempPlayer.isEmpty())
            throw new PlayerNotFoundException("Player with id "+ playerRequest.getId()
                                + " not found");
        player = tempPlayer.get();
        player.setName(playerRequest.getName());
        player.setTitle(playerRequest.getTitle());
        player.setNationality(playerRequest.getNationality());
        player.setBirthDate(playerRequest.getBirthDate());

        repo.save(player);
        BeanUtils.copyProperties(player, response);

        return response;
    }

    @Override
    public void deletePlayer(PlayerRequest playerRequest) {
        repo.deleteById(playerRequest.getId());
    }

    @Override
    public void registerPlayer(RegisterRequest request) {
        Optional<Player> player = repo.findById(request.getPlayerId());

        if (player.isEmpty())
            throw new PlayerNotFoundException("Player with id "+ request.getPlayerId()
                    + " not found");
        Registration registration = regService.getRegistration(request);
        player.get().getRegistrations().add(registration);
    }
}
