package io.datajek.tennisplayerrest.data.repository;

import io.datajek.tennisplayerrest.data.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
}
