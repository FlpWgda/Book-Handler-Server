package io.swagger.api;

import io.swagger.dao.GenreRepository;
import io.swagger.dao.PublicationRepository;
import io.swagger.model.Genre;
import io.swagger.model.Publication;
import io.swagger.model.PublicationList;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-25T09:56:25.993Z[GMT]")
@RestController
public class GenreApiController implements GenreApi {

    @Autowired
    private GenreRepository genreRepository;

    private static final Logger log = LoggerFactory.getLogger(GenreApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public GenreApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Genre> addGenre(@Parameter(in = ParameterIn.HEADER, description = "User info to check genre addition rights" ,required=true,schema=@Schema()) @RequestHeader(value="user", required=true) String user,@Parameter(in = ParameterIn.DEFAULT, description = "Genre object that needs to be added to the genre list", required=true, schema=@Schema()) @Valid @RequestBody Genre body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Genre>(objectMapper.readValue("{\n  \"createdBy\" : {\n    \"password\" : \"password\",\n    \"userRole\" : \"admin\",\n    \"email\" : \"email\",\n    \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"username\" : \"username\"\n  },\n  \"name\" : \"name\",\n  \"id\" : 0,\n  \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"publications\" : [ {\n    \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n    \"author\" : \"author\",\n    \"addedBy\" : {\n      \"password\" : \"password\",\n      \"userRole\" : \"admin\",\n      \"email\" : \"email\",\n      \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"username\" : \"username\"\n    },\n    \"genre\" : [ {\n      \"name\" : \"name\"\n    }, {\n      \"name\" : \"name\"\n    } ],\n    \"rating\" : 1.4658129,\n    \"id\" : 0,\n    \"title\" : \"title\",\n    \"releaseYear\" : 6,\n    \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\"\n  }, {\n    \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n    \"author\" : \"author\",\n    \"addedBy\" : {\n      \"password\" : \"password\",\n      \"userRole\" : \"admin\",\n      \"email\" : \"email\",\n      \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"username\" : \"username\"\n    },\n    \"genre\" : [ {\n      \"name\" : \"name\"\n    }, {\n      \"name\" : \"name\"\n    } ],\n    \"rating\" : 1.4658129,\n    \"id\" : 0,\n    \"title\" : \"title\",\n    \"releaseYear\" : 6,\n    \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\"\n  } ]\n}", Genre.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Genre>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        genreRepository.save(body);

        return new ResponseEntity<Genre>(body,HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteGenre(@Parameter(in = ParameterIn.PATH, description = "Name of the genre to delete", required=true, schema=@Schema()) @PathVariable("genreName") String genreName,@Parameter(in = ParameterIn.COOKIE, description = "User info to check genre deletion rights" ,schema=@Schema()) @CookieValue(value="user", required=false) User user) {
        String accept = request.getHeader("Accept");
        List<Publication> tempList = new ArrayList<Publication>();
        Genre tempG = null;

        for(Genre g: Genre.genres){
            if(g.getGenreName().equals(genreName)){
                tempG = g;
                Genre.genres.remove(g);
                break;
            }
        }
        if(tempG == null){
            return new ResponseEntity<Void>(HttpStatus.OK);
        }

        for(Publication p:Publication.publications){
            List<Genre> newGenreList = new ArrayList<Genre>();
            for(Genre g: p.getGenre()){
                if(!tempG.getGenreName().equals(g.getGenreName())){
                    newGenreList.add(g);
                }
            }
            p.setGenre(newGenreList);
        }
        genreRepository.deleteById(genreName);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<Genre>> findAllGenres() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<List<Genre>>(Genre.genres, HttpStatus.OK);
        }

        return new ResponseEntity<List<Genre>>(genreRepository.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<Genre> updateGenre(@Parameter(in = ParameterIn.PATH, description = "Name of the genre to update", required=true, schema=@Schema()) @PathVariable("genreName") String genreName,@Parameter(in = ParameterIn.HEADER, description = "User info to check modification rights" ,required=true,schema=@Schema()) @RequestHeader(value="user", required=true) String user,@Parameter(in = ParameterIn.DEFAULT, description = "Genre object that needs to be updated", required=true, schema=@Schema()) @Valid @RequestBody Genre body) {
        String accept = request.getHeader("Accept");
        Genre genre = genreRepository.findByGenreName(genreName);
        genre.setGenreName(body.getGenreName());
        genreRepository.save(genre);
        return new ResponseEntity<Genre>(genre,HttpStatus.OK);
    }

}
