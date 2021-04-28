package com.zikozee.restfulwebservices.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
public class UserResource {

    private final UserDaoServiceImpl service;

    //retrieveAllUsers
    @GetMapping(path = "users")
    public List<User> retrieveAllUsers(){

        return service.findAll();
    }

    //retrieveUSer(int id)
    @GetMapping(path = "users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable(value = "id") int id){
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
