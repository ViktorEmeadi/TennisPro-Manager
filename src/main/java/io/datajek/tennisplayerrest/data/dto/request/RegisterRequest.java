package io.datajek.tennisplayerrest.data.dto.request;

import io.datajek.tennisplayerrest.data.model.MatchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private int id;
    private MatchType matchType;
    private Date date;
    private int playerId;
    private int tournamentId;

    public void setPlayerId(@PathVariable  int playerId){
        this.playerId = playerId;
    }

    public void setId(@PathVariable int id){
        this.id = id;
    }
    public void setTournamentId(@PathVariable int tournamentId) {
        this.tournamentId = tournamentId;
    }

}
