package io.datajek.tennisplayerrest.data.dto.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    public void setId(@PathVariable int id) {
        this.id = id;
    }

    public void setTournamentId(@PathVariable("tournament-id") int tournamentId) {
        this.tournamentId = tournamentId;
    }

    private int id;
    private String name;
    private int tournamentId;
}
