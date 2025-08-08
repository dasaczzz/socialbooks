package com.apiweb.socialbooks.Controller;

import com.apiweb.socialbooks.Model.UsersModel;
import com.apiweb.socialbooks.Service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiweb/users")
public class UsersController {
    @Autowired
    IUsersService usersService;

    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody UsersModel user) {
        return new ResponseEntity<>(usersService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UsersModel>> getUsers() {
        return new ResponseEntity<>(usersService.getUsers(), HttpStatus.OK);
    }
}
