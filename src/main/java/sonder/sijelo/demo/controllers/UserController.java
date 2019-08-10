package sonder.sijelo.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sonder.sijelo.demo.models.User;
import sonder.sijelo.demo.repositories.UserRepository;

@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity register(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password) {
        try {
            repository.saveAndFlush(new User(username, password));
            logger.info("Created user " + username);
            return ResponseEntity.ok().body(200);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @RequestMapping(value = "/users2", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody User user) {
        try {
            repository.saveAndFlush(user);
            logger.info("Created user " + user.getUsername());
            return ResponseEntity.ok().body(200);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}
