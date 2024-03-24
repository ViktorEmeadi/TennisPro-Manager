package io.datajek.tennisplayerrest.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TournamentRequest {
    public void setId(@PathVariable int id) {
        this.id = id;
    }

    private int id;
    private String name;
    private Date date;
    private String location;
}
