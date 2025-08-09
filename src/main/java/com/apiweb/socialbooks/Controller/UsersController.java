package com.apiweb.socialbooks.Controller;

import com.apiweb.socialbooks.Exception.ResourceNotFound;
import com.apiweb.socialbooks.Model.UserProfilesView;
import com.apiweb.socialbooks.Model.UsersModel;
import com.apiweb.socialbooks.Service.IUsersService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/apiweb/users")
public class UsersController {
    @Autowired
    IUsersService usersService;

    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody UsersModel user) {
        List<ObjectId> validFriends = validateFriends(user.getFriends());
        user.setFriends(validFriends);
        return new ResponseEntity<>(usersService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UsersModel>> getUsers() {
        return new ResponseEntity<>(usersService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        try {
            UsersModel userFound = validateId(id);
            return new ResponseEntity<>(userFound, HttpStatus.OK);
        } catch (ResourceNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        try {
            UsersModel userFound = validateId(id);
            String confirmation = usersService.deleteUserFromFriendLists(userFound.getId());
            String deletedUser = usersService.deleteUser(userFound.getId());
            String finalString = confirmation + "\n" + deletedUser;
            return new ResponseEntity<>(finalString, HttpStatus.OK);
        } catch (ResourceNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<UserProfilesView>> getUserProfiles() {
        return new ResponseEntity<>(usersService.getUserProfiles(), HttpStatus.OK);
    }

    private UsersModel validateId(String id) throws ResourceNotFound {
        if(!ObjectId.isValid(id)) {
            throw new ResourceNotFound(String.format("The user with id %s has not been found", id));
        }
        UsersModel user = usersService.getUserById(new ObjectId(id));
        if (user == null) {
            throw new ResourceNotFound(String.format("The user with id %s has not been found", id));
        }
        return user;
    }

    private List<ObjectId> validateFriends(List<ObjectId> idList) {
        List<ObjectId> validFriends = new ArrayList<>();
        if (idList == null || idList.isEmpty()) {
            return List.of();
        }
        for (ObjectId id : idList) {
            UsersModel amigo = usersService.getUserById(id);
            if (amigo != null) {
                validFriends.add(id);
            }
        }
        Set<ObjectId> validFriendsNoRepeat = new HashSet<>(validFriends);
        validFriends.clear();
        validFriends.addAll(validFriendsNoRepeat);
        return validFriends;
    }
}
