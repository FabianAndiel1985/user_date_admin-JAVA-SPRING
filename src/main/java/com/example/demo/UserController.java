package com.example.demo;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
class UserController {

  private final UserRepository repository;

  UserController(UserRepository repository) {
    this.repository = repository;
  }


  @GetMapping("/users")
  CollectionModel<EntityModel<User>> all() {

    List<EntityModel<User>> user = repository.findAll().stream()
        .map(employee -> EntityModel.of(employee,
            linkTo(methodOn(UserController.class).one(employee.getId())).withSelfRel(),
            linkTo(methodOn(UserController.class).all()).withRel("employees")))
        .collect(Collectors.toList());

    return CollectionModel.of(user, linkTo(methodOn(UserController.class).all()).withSelfRel());
  }
  
  @PostMapping("/users")
  User newUser(@RequestBody User newUser) {
    return repository.save(newUser);
  }
  
  @GetMapping("/users/{id}")
  EntityModel<User> one(@PathVariable Long id) {
	  
	  //Todo Exception behandeln

    User user = repository.findById(id) //
        .orElseThrow();

    return EntityModel.of(user, //
        linkTo(methodOn(UserController.class).one(id)).withSelfRel(),
        linkTo(methodOn(UserController.class).all()).withRel("users"));
  }
  
  @PutMapping("/users/{id}")
User replaceUser(@RequestBody User newUser, @PathVariable Long id) { 
		
  return repository.findById(id)
    .map(user -> {
      user.setName(newUser.getName());
      return repository.save(user);
    })
    .orElseGet(() -> {
      newUser.setId(id);
      return repository.save(newUser);
    });
}
  
  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
