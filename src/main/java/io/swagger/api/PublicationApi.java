/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Genre;
import io.swagger.model.Publication;
import io.swagger.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-25T09:56:25.993Z[GMT]")
@Validated
@CrossOrigin
public interface PublicationApi {

    @Operation(summary = "Add a new publication to the service", description = "", tags={ "publication" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Publication.class))) })
    @RequestMapping(value = "/publication",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Publication> addPublication(@Parameter(in = ParameterIn.DEFAULT, description = "Publication object that needs to be added to the service", required=true, schema=@Schema()) @Valid @RequestBody Publication body);


    @Operation(summary = "Delete a publication", description = "", tags={ "publication" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        
        @ApiResponse(responseCode = "403", description = "User does not have the rights to delete this publication"),
        
        @ApiResponse(responseCode = "404", description = "Publication not found") })
    @RequestMapping(value = "/publication/{publicationId}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deletePublication(@Parameter(in = ParameterIn.PATH, description = "Publication id to delete", required=true, schema=@Schema()) @PathVariable("publicationId") Long publicationId, @Parameter(in = ParameterIn.COOKIE, description = "" ,schema=@Schema()) @CookieValue(value="user", required=false) User user);


    @Operation(summary = "Find publications by author's name", description = "Muliple authors names can be provided with comma separated strings.", tags={ "publication" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Publication.class)))) })
    @RequestMapping(value = "/publication/findByAuthor",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Publication>> findPublicationsByAuthor(@NotNull @Parameter(in = ParameterIn.QUERY, description = "Authors to filter by" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "authors", required = true) List<String> authors);


    @Operation(summary = "Find publications by genre", description = "Multiple genre values can be provided with comma separated strings", tags={ "publication" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Publication.class)))),
        
        @ApiResponse(responseCode = "400", description = "Invalid genre value") })
    @RequestMapping(value = "/publication/findByGenre",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Publication>> findPublicationsByGenre(@NotNull @Parameter(in = ParameterIn.QUERY, description = "Genre values that need to be considered for filter" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "genre", required = true) List<String> genre);


    @Operation(summary = "Find publications by release year", description = "Muliple years integers can be provided with comma separated integers.", tags={ "publication" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Publication.class)))) })
    @RequestMapping(value = "/publication/findByReleaseYear",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Publication>> findPublicationsByYear(@NotNull @Parameter(in = ParameterIn.QUERY, description = "Years to filter by" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "years", required = true) List<Integer> years);


    @Operation(summary = "Find publication by ID", description = "Returns a single publication", tags={ "publication" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Publication.class))),
        
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        
        @ApiResponse(responseCode = "404", description = "Publication not found") })
    @RequestMapping(value = "/publication/{publicationId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Publication> getPublicationById(@Parameter(in = ParameterIn.PATH, description = "ID of publication to return", required=true, schema=@Schema()) @PathVariable("publicationId") Long publicationId);

    @Operation(summary = "Find all publications", description = "", tags={ "publication" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Publication.class)))) })
    @RequestMapping(value = "/publication/findAll",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Publication>> findAllPublications();

    @Operation(summary = "Update an existing publication", description = "", tags={ "publication" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "204", description = "Successfully updated publication object"),
        
        @ApiResponse(responseCode = "403", description = "User does not have the rights to alter this publication"),
        
        @ApiResponse(responseCode = "404", description = "Publication not found") })
    @RequestMapping(value = "/publication",
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Publication> updatePublication(@Parameter(in = ParameterIn.HEADER, description = "User info to check modification rights" ,required=true,schema=@Schema()) @RequestHeader(value="user", required=true) String user, @Parameter(in = ParameterIn.DEFAULT, description = "Publication object that needs to be updated", required=true, schema=@Schema()) @Valid @RequestBody Publication body);


    @Operation(summary = "Update rating of an existing publication", description = "", tags={ "publication" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cool"),
        @ApiResponse(responseCode = "204", description = "Successfully updated publication object"),
        
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        
        @ApiResponse(responseCode = "403", description = "User does not have the rights to alter this publication"),
        
        @ApiResponse(responseCode = "404", description = "Publication not found") })
    @RequestMapping(value = "/publication/{publicationId}",
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Publication> updatePublicationRating(@Parameter(in = ParameterIn.PATH, description = "ID of publication to rate", required=true, schema=@Schema()) @PathVariable("publicationId") Long publicationId, @Parameter(in = ParameterIn.DEFAULT, description = "Publication rating", required=true, schema=@Schema()) @Valid @RequestBody Integer body);

}

