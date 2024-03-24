package io.datajek.tennisplayerrest.data.dto.response;

import io.datajek.tennisplayerrest.data.model.PlayerProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponse {
    private int id;
    private String name;
    private String nationality;
    private Date birthDate;
    private int title;
    private PlayerProfile playerProfile;
}
