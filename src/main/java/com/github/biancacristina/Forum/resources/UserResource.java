package com.github.biancacristina.Forum.resources;

import com.github.biancacristina.Forum.domain.User;
import com.github.biancacristina.Forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        User obj = userService.findById(id);

        return ResponseEntity.ok().body(obj);
    }
}
