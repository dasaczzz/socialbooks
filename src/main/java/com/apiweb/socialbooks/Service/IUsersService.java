package com.apiweb.socialbooks.Service;

import com.apiweb.socialbooks.Model.UserProfilesView;
import com.apiweb.socialbooks.Model.UsersModel;
import org.bson.types.ObjectId;

import java.util.List;

public interface IUsersService {
    String createUser(UsersModel user);
    List<UsersModel> getUsers();
    UsersModel getUserById(String id);
    List<UserProfilesView> getUserProfiles();
    String deleteUser(ObjectId id);
    String deleteUserFromFriends(ObjectId id);
}
