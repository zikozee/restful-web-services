package com.zikozee.restfulwebservices.domain.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author : zikoz
 * @created : 23 Apr, 2021
 */
@RestController
@RequiredArgsConstructor
@Api(value = "User Resource Controller", protocols = "https", description = "User Operations")
public class UserResource {

    private final UserDaoServiceImpl service;

    //retrieveAllUsers
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found users",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Users not found",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))})
    })
    @GetMapping(path = "users")
    public List<User> retrieveAllUsers(){

        return service.findAll();
    }

    //retrieveUSer(int id)
    @GetMapping(path = "users/{id}")
    public EntityModel<User> retrieveUser(@ApiParam(name = "user_id") @PathVariable(value = "id") int id){
        var user =service.findOne(id);
        if(ObjectUtils.isEmpty(user))
            throw new UserNotFoundException("id-" + id);

        //"all-users", SERVER_PATH + "/users"  ===========>>>> USING HATOEAS to return links yo other resources
        //retrieveAllUsers
        EntityModel<User> resource = EntityModel.of(user);

        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));

        //HATEOAS
        return resource;

    }

    @DeleteMapping(path = "users/{id}")
    public void deleteUser(@PathVariable(value = "id") int id){
        var user =service.deleteById(id);
        if(ObjectUtils.isEmpty(user))
            throw new UserNotFoundException("id-" + id);
    }

    //CREATED
    //input  - details of user
    //output -CREATED & Return the created URI
    @PostMapping(path = "users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        var savedUser = service.save(user);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}") //this id will be replaced by the savedUser.getId()
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
