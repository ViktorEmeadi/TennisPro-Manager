package io.datajek.tennisplayerrest.data.model;

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
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date date;
    private String location;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tournament_id")
    private List<Registration> registrations = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "tournaments_categories",
    joinColumns = @JoinColumn(name = "tournament_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonIgnoreProperties("tournaments")
    private List<Category> categories = new ArrayList<>();

    public void addRegistration(Registration reg){
        registrations.add(reg);
    }

    public void addCategory(Category category){
        categories.add(category);
        category.getTournaments().add(this);
    }

    public void removeCategory(Category category){
        if (category != null){
            categories.remove(category);
            category.getTournaments().remove(this);
        }
    }
}
