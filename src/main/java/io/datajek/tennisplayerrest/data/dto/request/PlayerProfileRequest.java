package io.datajek.tennisplayerrest.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerProfileRequest {

    public void setId(@PathVariable int id) {
        this.id = id;
    }

    private int id;
    private String email;
}
