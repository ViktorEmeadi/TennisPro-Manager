package io.datajek.tennisplayerrest.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequest {
    private int id;
    private String name;
    private String nationality;
    private Date birthDate;
    private int title;

    public void setId(@PathVariable int id){
        this.id = id;
    }
}
