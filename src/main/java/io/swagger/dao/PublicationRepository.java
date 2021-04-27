package io.swagger.dao;

import io.swagger.model.Genre;
import io.swagger.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    public Publication findPublicationById(Long id);
    public List<Publication> findByAuthorIn(List<String> author);
    public List<Publication> findByReleaseYearIn(List<Integer> releaseYear);
    public List<Publication> findDistinctByGenre_GenreNameIn(List<String> genre);
}
