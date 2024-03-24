package io.datajek.tennisplayerrest.data.dto.response;

import io.datajek.tennisplayerrest.data.model.MatchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private int id;
    private MatchType matchType;
    private Date date;
}
