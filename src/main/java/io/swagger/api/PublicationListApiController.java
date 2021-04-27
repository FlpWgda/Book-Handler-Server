package io.swagger.api;

import io.swagger.dao.PublicationListRepository;
import io.swagger.dao.PublicationRepository;
import io.swagger.model.Publication;
import io.swagger.model.PublicationList;
import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-25T09:56:25.993Z[GMT]")
@RestController
public class PublicationListApiController implements PublicationListApi {

    private static final Logger log = LoggerFactory.getLogger(PublicationListApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private PublicationListRepository publicationListRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public PublicationListApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<PublicationList> addPublicationList(@Parameter(in = ParameterIn.DEFAULT, description = "PublicationList object that needs to be added to the service", required=true, schema=@Schema()) @Valid @RequestBody PublicationList body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PublicationList>(objectMapper.readValue("{\n  \"createdBy\" : {\n    \"password\" : \"password\",\n    \"userRole\" : \"admin\",\n    \"email\" : \"email\",\n    \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n    \"username\" : \"username\"\n  },\n  \"name\" : \"name\",\n  \"id\" : 0,\n  \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"publications\" : [ {\n    \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n    \"author\" : \"author\",\n    \"addedBy\" : {\n      \"password\" : \"password\",\n      \"userRole\" : \"admin\",\n      \"email\" : \"email\",\n      \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"username\" : \"username\"\n    },\n    \"genre\" : [ {\n      \"name\" : \"name\"\n    }, {\n      \"name\" : \"name\"\n    } ],\n    \"rating\" : 1.4658129,\n    \"id\" : 0,\n    \"title\" : \"title\",\n    \"releaseYear\" : 6,\n    \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\"\n  }, {\n    \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n    \"author\" : \"author\",\n    \"addedBy\" : {\n      \"password\" : \"password\",\n      \"userRole\" : \"admin\",\n      \"email\" : \"email\",\n      \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"username\" : \"username\"\n    },\n    \"genre\" : [ {\n      \"name\" : \"name\"\n    }, {\n      \"name\" : \"name\"\n    } ],\n    \"rating\" : 1.4658129,\n    \"id\" : 0,\n    \"title\" : \"title\",\n    \"releaseYear\" : 6,\n    \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\"\n  } ]\n}", PublicationList.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PublicationList>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        publicationListRepository.save(body);

        return new ResponseEntity<PublicationList>(body, HttpStatus.OK);
    }

    public ResponseEntity<Void> deletePublicationList(@Parameter(in = ParameterIn.PATH, description = "Publication list id to delete", required=true, schema=@Schema()) @PathVariable("publicationListId") Long publicationListId,@Parameter(in = ParameterIn.HEADER, description = "" ,schema=@Schema()) @RequestHeader(value="username", required=false) String username) {
        Optional<PublicationList> publicationListRepositoryOptional = publicationListRepository.findById(publicationListId);
        if(publicationListRepositoryOptional.isPresent()){
            if(publicationListRepositoryOptional.get().getCreatedBy().getUsername().equals(UserApiController.loggedUser.getUsername())){
                publicationListRepository.delete(publicationListRepositoryOptional.get());
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<PublicationList>> findPublicationsListByUserId(@NotNull @Parameter(in = ParameterIn.QUERY, description = "User id to filter by" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "username", required = true) String userId) {
        List<PublicationList> publicationListList = publicationListRepository.findByCreatedBy_Username(userId);

        if(userId.equals(UserApiController.loggedUser.getUsername())){
            return new ResponseEntity<List<PublicationList>>(publicationListList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<PublicationList>>(HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<PublicationList> getPublicationListById(@Parameter(in = ParameterIn.PATH, description = "ID of publication list to return", required=true, schema=@Schema()) @PathVariable("publicationListId") Long publicationListId) {
        Optional<PublicationList> publicationListOptional = publicationListRepository.findById(publicationListId);
        if (publicationListOptional.isPresent()) {
            if(publicationListOptional.get().getCreatedBy().getUsername().equals(UserApiController.loggedUser.getUsername())){
                return new ResponseEntity<PublicationList>(publicationListOptional.get(),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<PublicationList>(HttpStatus.UNAUTHORIZED);
            }
        }
        else{
            return new ResponseEntity<PublicationList>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> updatePublicationList(@Parameter(in = ParameterIn.COOKIE, description = "User info to check modification rights" ,required=true,schema=@Schema()) @CookieValue(value="user", required=true) User user,@Parameter(in = ParameterIn.DEFAULT, description = "PublicationList object that needs to be updated", required=true, schema=@Schema()) @Valid @RequestBody PublicationList body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
