package io.datajek.tennisplayerrest.data.repository;

import io.datajek.tennisplayerrest.data.model.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerProfileRepository extends JpaRepository<PlayerProfile, Integer> {
}
