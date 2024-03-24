package io.datajek.tennisplayerrest.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String nationality;
    /* By default, dates are saved as Timestamp by Hibernate. When we annotate the
    birthDate field with @JsonFormat, Jackson will use the provided format for
    serializing and deserializing the field. */
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
    @Column(name = "Titles")
    private int title;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "player_profile", referencedColumnName = "id")
    @JsonIgnoreProperties("players")
    private PlayerProfile playerProfile;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Registration> registrations = new ArrayList<>();

    public void registerPlayer(Registration registration){
        registrations.add(registration);
        registration.setPlayer(this);
    }
}
