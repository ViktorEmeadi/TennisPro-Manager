package io.datajek.tennisplayerrest.data.repository;

import io.datajek.tennisplayerrest.data.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
