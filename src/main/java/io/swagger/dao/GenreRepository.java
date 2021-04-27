package io.swagger.dao;

import io.swagger.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, String> {

  public Genre findByGenreName(String id);
}
