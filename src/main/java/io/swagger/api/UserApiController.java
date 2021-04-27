package io.swagger.api;

import io.swagger.dao.UserRepository;
import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.threeten.bp.OffsetDateTime;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-25T09:56:25.993Z[GMT]")
@RestController
public class UserApiController implements UserApi {

    @Autowired
    private UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    static User loggedUser = null;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<User> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "Creates user object", required=true, schema=@Schema()) @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        body.setDateAdded(OffsetDateTime.now());
        if(body.getUserRole() == null){
            body.setUserRole(User.UserRoleEnum.MEMBER);
        }
        userRepository.save(body);
        return new ResponseEntity<User>(body,HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteUser(@Parameter(in = ParameterIn.PATH, description = "The name that needs to be deleted", required=true, schema=@Schema()) @PathVariable("username") String username,@Parameter(in = ParameterIn.HEADER, description = "The user role" ,required=true,schema=@Schema(allowableValues={ "admin", "member" }
)) @RequestHeader(value="role", required=true) String role) {
        String accept = request.getHeader("Accept");
        Optional<User> userOptional = userRepository.findById(username);
        if(userOptional.isPresent()){
            if(role.equals("admin")){
                userRepository.delete(userOptional.get());
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<User> getUserByName(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("username") String username) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{\n  \"password\" : \"password\",\n  \"userRole\" : \"admin\",\n  \"email\" : \"email\",\n  \"dateAdded\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"username\" : \"username\"\n}", User.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        Optional<User> userOptional = userRepository.findById(username);
        if(userOptional.isPresent()){
            return new ResponseEntity<User>(userOptional.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> loginUser(@NotNull @Parameter(in = ParameterIn.QUERY, description = "The username for login" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "username", required = true) String username,@NotNull @Parameter(in = ParameterIn.QUERY, description = "The password for login in clear text" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "password", required = true) String password) {

        Optional<User> userOptional = userRepository.findById(username);
        if(userOptional.isPresent()){
            if(userOptional.get().getPassword().equals(password)){
                loggedUser = userOptional.get();
                return new ResponseEntity<String>("User logged in",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<String>("Wrong password",HttpStatus.UNAUTHORIZED);
            }
        }
        else{
            return new ResponseEntity<String>("Username not found",HttpStatus.NOT_FOUND);
        }


    }

    public ResponseEntity<Void> logoutUser() {
        loggedUser = null;
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    public ResponseEntity<User> updateUser(@Parameter(in = ParameterIn.PATH, description = "Name that needs to be updated", required=true, schema=@Schema()) @PathVariable("username") String username,@Parameter(in = ParameterIn.DEFAULT, description = "Updated user object", required=true, schema=@Schema()) @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");

        Optional<User> userOptional = userRepository.findById(username);
        if(userOptional.isPresent()){
            body.setUsername(username);
            if(body.getUserRole() == null){
                body.setUserRole(User.UserRoleEnum.MEMBER);
            }
            body.setDateAdded(userOptional.get().getDateAdded());
            userRepository.save(body);
            return new ResponseEntity<User>(body, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<User> getLoggedUser() {
        if(loggedUser != null){
            return new ResponseEntity<User>(loggedUser,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

}
