package com.apiweb.socialbooks.Service;

import com.apiweb.socialbooks.Lib.BaseResponse;
import com.apiweb.socialbooks.Model.UserProfilesView;
import com.apiweb.socialbooks.Model.UsersModel;
import com.apiweb.socialbooks.Repository.UserProfilesRepository;
import com.apiweb.socialbooks.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImp implements IUsersService {

    //  --------- Dependency injection ------------------
    final UserRepository usersRepository;
    final MongoTemplate mongoTemplate;
    final UserProfilesRepository  userProfilesRepository;

    public UsersServiceImp(UserRepository usersRepository, MongoTemplate mongoTemplate, UserProfilesRepository userProfilesRepository) {
        this.usersRepository = usersRepository;
        this.mongoTemplate = mongoTemplate;
        this.userProfilesRepository = userProfilesRepository;
    }
    // --------------------------------------------------

    @Override
    public String getRecordType() { return "user"; }

    @Override
    public BaseResponse createRecord(UsersModel user) {
        usersRepository.save(user);
        return new BaseResponse(
                HttpStatus.CREATED.value(),
                String.format("The user %s with the id %s has been created successfully", user.getFullName(), user.getId()),
                "POST",
                "/apiweb/users/"
        );
    }

    @Override
    public List<UsersModel> getRecords() {
        return usersRepository.findAll();
    }

    @Override
    public UsersModel getRecordById(ObjectId id) { return usersRepository.findById(id).orElse(null); }

    @Override
    public BaseResponse deleteRecord(ObjectId id) {
        usersRepository.deleteById(id);
        deleteUserFromFriendLists(id);
        return new BaseResponse(
                HttpStatus.NO_CONTENT.value(),
                String.format("The user with id %s has been deleted successfully", id),
                "DELETE",
                String.format("/apiweb/users/%s", id)
        );
    }

    @Override
    public List<UserProfilesView> getUserProfiles() { return userProfilesRepository.findAll(); }

    private void deleteUserFromFriendLists(ObjectId id) {
        Query query = new Query();
        Update update = new Update().pull("friends", id);
        mongoTemplate.updateMulti(query, update, "Users");
    }
}
