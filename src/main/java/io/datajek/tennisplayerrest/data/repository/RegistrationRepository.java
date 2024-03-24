package io.datajek.tennisplayerrest.data.repository;

import io.datajek.tennisplayerrest.data.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
}
