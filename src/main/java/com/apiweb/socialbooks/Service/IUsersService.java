package com.apiweb.socialbooks.Service;

import com.apiweb.socialbooks.Model.UserProfilesView;
import com.apiweb.socialbooks.Model.UsersModel;
import org.bson.types.ObjectId;

import java.util.List;

public interface IUsersService extends BaseService<UsersModel> {
    List<UserProfilesView> getUserProfiles();
    String deleteUserFromFriendLists(ObjectId id);
}
