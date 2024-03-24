package io.datajek.tennisplayerrest.data.repository;

import io.datajek.tennisplayerrest.data.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
//    @Modifying
//    @Query("update Player p set p.title = :title where p.id = :id")
//    void updateTitle(@Param("id") int id, @Param("title") int title);
}
