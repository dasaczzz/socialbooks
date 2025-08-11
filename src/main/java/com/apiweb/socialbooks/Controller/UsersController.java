package com.apiweb.socialbooks.Controller;

import com.apiweb.socialbooks.Lib.BaseResponse;
import com.apiweb.socialbooks.Lib.Utils;
import com.apiweb.socialbooks.Model.UserProfilesView;
import com.apiweb.socialbooks.Model.UsersModel;
import com.apiweb.socialbooks.Service.IUsersService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/apiweb/users")
public class UsersController implements BaseController<UsersModel> {

    //  --------- Dependency injection ------------------
    final IUsersService usersService;

    public UsersController(IUsersService usersService) {
        this.usersService = usersService;
    }
    // --------------------------------------------------

    @PostMapping("/")
    public ResponseEntity<BaseResponse> createRecord(@RequestBody UsersModel user) {
        List<ObjectId> validFriends = validateFriends(user.getFriends());
        user.setFriends(validFriends);
        return new ResponseEntity<>(usersService.createRecord(user), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UsersModel>> getRecords() {
        return new ResponseEntity<>(usersService.getRecords(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersModel> getRecordById(@PathVariable String id) {
        UsersModel userFound = Utils.validateEntryId(id, usersService);
        return new ResponseEntity<>(userFound, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteRecord(@PathVariable String id) {
        UsersModel userFound = Utils.validateEntryId(id, usersService);
        return new ResponseEntity<>(usersService.deleteRecord(userFound.getId()), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<UserProfilesView>> getUserProfiles() {
        return new ResponseEntity<>(usersService.getUserProfiles(), HttpStatus.OK);
    }

    private List<ObjectId> validateFriends(List<ObjectId> idList) {
        List<ObjectId> validFriends = new ArrayList<>();
        if (idList == null || idList.isEmpty()) {
            return List.of();
        }
        for (ObjectId id : idList) {
            UsersModel amigo = usersService.getRecordById(id);
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
