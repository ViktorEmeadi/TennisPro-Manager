package io.datajek.tennisplayerrest.data.dto.response;

import io.datajek.tennisplayerrest.data.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TournamentResponse {
    private int id;
    private String name;
    private Date date;
    private String location;
    private List<Category> categories = new ArrayList<>();
}
