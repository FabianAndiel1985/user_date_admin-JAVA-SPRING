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
class AppointmentController {

  private final AppointmentRepository repository;

  AppointmentController(AppointmentRepository repository) {
    this.repository = repository;
  }


  @GetMapping("/appointments")
  CollectionModel<EntityModel<Appointment>> all() {

    List<EntityModel<Appointment>> user = repository.findAll().stream()
        .map(appointment -> EntityModel.of(appointment,
            linkTo(methodOn(AppointmentController.class).one(appointment.getId())).withSelfRel(),
            linkTo(methodOn(AppointmentController.class).all()).withRel("appointments")))
        .collect(Collectors.toList());
    return CollectionModel.of(user, linkTo(methodOn(UserController.class).all()).withSelfRel());
  }
  
  @PostMapping("/appointments")
  Appointment newAppointment(@RequestBody Appointment newAppointment) {
    return repository.save(newAppointment);
  }
  
  @GetMapping("/appointments/{id}")
  EntityModel<Appointment> one(@PathVariable Long id) {
	  
	  //Todo Exception behandeln

    Appointment appointment = repository.findById(id) //
        .orElseThrow();

    return EntityModel.of(appointment, //
        linkTo(methodOn(AppointmentController.class).one(id)).withSelfRel(),
        linkTo(methodOn(AppointmentController.class).all()).withRel("appointments"));
  }
  
//  @PutMapping("/users/{id}")
//User replaceUser(@RequestBody User newUser, @PathVariable Long id) { 
//		
//  return repository.findById(id)
//    .map(user -> {
//      user.setName(newUser.getName());
//      return repository.save(user);
//    })
//    .orElseGet(() -> {
//      newUser.setId(id);
//      return repository.save(newUser);
//    });
//}
//  
//  @DeleteMapping("/users/{id}")
//  void deleteUser(@PathVariable Long id) {
//    repository.deleteById(id);
//  }
  
  
}
