package io.swagger.api;

import io.swagger.dao.PublicationRepository;
import io.swagger.model.Genre;
import io.swagger.model.Publication;
import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-25T09:56:25.993Z[GMT]")
@RestController
public class PublicationApiController implements PublicationApi {

    @Autowired
    private PublicationRepository publicationRepository;

    private static final Logger log = LoggerFactory.getLogger(PublicationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PublicationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Publication> addPublication(@Parameter(in = ParameterIn.DEFAULT, description = "Publication object that needs to be added to the service", required=true, schema=@Schema()) @Valid @RequestBody Publication body) {
        String accept = request.getHeader("Accept");
        body.setRating(0.0f);
        if (accept != null && accept.contains("application/json")) {
            publicationRepository.save(body);
            return new ResponseEntity<Publication>(body, HttpStatus.OK);
        }
        publicationRepository.save(body);

        return new ResponseEntity<Publication>(body,HttpStatus.OK);
    }

    public ResponseEntity<Void> deletePublication(@Parameter(in = ParameterIn.PATH, description = "Publication id to delete", required=true, schema=@Schema()) @PathVariable("publicationId") Long publicationId,@Parameter(in = ParameterIn.COOKIE, description = "" ,schema=@Schema()) @CookieValue(value="user", required=false) User user) {
        String accept = request.getHeader("Accept");
        publicationRepository.deleteById(publicationId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<Publication>> findAllPublications() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<List<Publication>>(publicationRepository.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<List<Publication>>(publicationRepository.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<List<Publication>> findPublicationsByAuthor(@NotNull @Parameter(in = ParameterIn.QUERY, description = "Authors to filter by" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "authors", required = true) List<String> authors) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Publication>>(objectMapper.readValue("[ {\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"author\" : \"author\",\n  \"addedBy\" : {\n    \"password\" : \"password\",\n    \"userRole\" : \"admin\",\n    \"email\" : \"email\",\n    \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"username\" : \"username\"\n  },\n  \"genre\" : [ {\n    \"name\" : \"name\"\n  }, {\n    \"name\" : \"name\"\n  } ],\n  \"rating\" : 1.4658129,\n  \"id\" : 0,\n  \"title\" : \"title\",\n  \"releaseYear\" : 6,\n  \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\"\n}, {\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"author\" : \"author\",\n  \"addedBy\" : {\n    \"password\" : \"password\",\n    \"userRole\" : \"admin\",\n    \"email\" : \"email\",\n    \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"username\" : \"username\"\n  },\n  \"genre\" : [ {\n    \"name\" : \"name\"\n  }, {\n    \"name\" : \"name\"\n  } ],\n  \"rating\" : 1.4658129,\n  \"id\" : 0,\n  \"title\" : \"title\",\n  \"releaseYear\" : 6,\n  \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Publication>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        List<Publication> tempList = new ArrayList<Publication>();
        for(Publication p:Publication.publications){
            for(String author: authors){
                if(p.getAuthor().equals(author)){
                    tempList.add(p);
                    break;
                }
            }
        }


        return new ResponseEntity<List<Publication>>(publicationRepository.findByAuthorIn(authors),HttpStatus.OK);
    }

    public ResponseEntity<List<Publication>> findPublicationsByGenre(@NotNull @Parameter(in = ParameterIn.QUERY, description = "Genre values that need to be considered for filter" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "genre", required = true) List<String> genres) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Publication>>(objectMapper.readValue("[ {\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"author\" : \"author\",\n  \"addedBy\" : {\n    \"password\" : \"password\",\n    \"userRole\" : \"admin\",\n    \"email\" : \"email\",\n    \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"username\" : \"username\"\n  },\n  \"genre\" : [ {\n    \"name\" : \"name\"\n  }, {\n    \"name\" : \"name\"\n  } ],\n  \"rating\" : 1.4658129,\n  \"id\" : 0,\n  \"title\" : \"title\",\n  \"releaseYear\" : 6,\n  \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\"\n}, {\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"author\" : \"author\",\n  \"addedBy\" : {\n    \"password\" : \"password\",\n    \"userRole\" : \"admin\",\n    \"email\" : \"email\",\n    \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"username\" : \"username\"\n  },\n  \"genre\" : [ {\n    \"name\" : \"name\"\n  }, {\n    \"name\" : \"name\"\n  } ],\n  \"rating\" : 1.4658129,\n  \"id\" : 0,\n  \"title\" : \"title\",\n  \"releaseYear\" : 6,\n  \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Publication>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        List<Publication> tempList = new ArrayList<Publication>();
        for(Publication p:Publication.publications){
            for(Genre g:p.getGenre()) {
                Publication tempP = null;
                for(String g2: genres){
                    if (g.getGenreName().equals(g2)) {
                        tempList.add(p);
                        tempP = p;
                        break;
                    }
                }
                if(tempP != null){
                    break;
                }
            }
        }

        return new ResponseEntity<List<Publication>>(publicationRepository.findDistinctByGenre_GenreNameIn(genres),HttpStatus.OK);
    }

    public ResponseEntity<List<Publication>> findPublicationsByYear(@NotNull @Parameter(in = ParameterIn.QUERY, description = "Years to filter by" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "years", required = true) List<Integer> years) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Publication>>(objectMapper.readValue("[ {\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"author\" : \"author\",\n  \"addedBy\" : {\n    \"password\" : \"password\",\n    \"userRole\" : \"admin\",\n    \"email\" : \"email\",\n    \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"username\" : \"username\"\n  },\n  \"genre\" : [ {\n    \"name\" : \"name\"\n  }, {\n    \"name\" : \"name\"\n  } ],\n  \"rating\" : 1.4658129,\n  \"id\" : 0,\n  \"title\" : \"title\",\n  \"releaseYear\" : 6,\n  \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\"\n}, {\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"author\" : \"author\",\n  \"addedBy\" : {\n    \"password\" : \"password\",\n    \"userRole\" : \"admin\",\n    \"email\" : \"email\",\n    \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"username\" : \"username\"\n  },\n  \"genre\" : [ {\n    \"name\" : \"name\"\n  }, {\n    \"name\" : \"name\"\n  } ],\n  \"rating\" : 1.4658129,\n  \"id\" : 0,\n  \"title\" : \"title\",\n  \"releaseYear\" : 6,\n  \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Publication>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Publication>>(publicationRepository.findByReleaseYearIn(years),HttpStatus.OK);
    }

    public ResponseEntity<Publication> getPublicationById(@Parameter(in = ParameterIn.PATH, description = "ID of publication to return", required=true, schema=@Schema()) @PathVariable("publicationId") Long publicationId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Publication tempPublication = publicationRepository.findPublicationById(publicationId);
                if(tempPublication != null){
                    return new ResponseEntity<Publication>(tempPublication,HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<Publication>(HttpStatus.NOT_FOUND);
                }
            }

        return new ResponseEntity<Publication>(publicationRepository.findPublicationById(publicationId),HttpStatus.OK);
    }

    public ResponseEntity<Publication> updatePublication(@Parameter(in = ParameterIn.HEADER, description = "User info to check modification rights" ,required=true,schema=@Schema()) @RequestHeader(value="user", required=true) String user,@Parameter(in = ParameterIn.DEFAULT, description = "Publication object that needs to be updated", required=true, schema=@Schema()) @Valid @RequestBody Publication body) {
        String accept = request.getHeader("Accept");
        publicationRepository.save(body);
        return new ResponseEntity<Publication>(body,HttpStatus.OK);
    }

    public ResponseEntity<Publication> updatePublicationRating(@Parameter(in = ParameterIn.PATH, description = "ID of publication to rate", required=true, schema=@Schema()) @PathVariable("publicationId") Long publicationId,@Parameter(in = ParameterIn.DEFAULT, description = "Publication rating", required=true, schema=@Schema()) @Valid @RequestBody Integer body) {
        String accept = request.getHeader("Accept");
        Publication publication = publicationRepository.findPublicationById(publicationId);
        int numberOfRatings = publication.getNumberOfRatings();
        float rating = publication.getRating();
        float newRating = (numberOfRatings*rating + body)/(numberOfRatings+1);
        publication.setRating(newRating);
        publication.setNumberOfRatings(numberOfRatings+1);
        publicationRepository.save(publication);
        return new ResponseEntity<Publication>(publication,HttpStatus.OK);
    }

}
