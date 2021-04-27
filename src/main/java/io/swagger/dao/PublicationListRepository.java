package io.swagger.dao;

import io.swagger.model.Publication;
import io.swagger.model.PublicationList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationListRepository extends JpaRepository<PublicationList, Long> {

    public List<PublicationList> findByCreatedBy_Username(String username);
}
