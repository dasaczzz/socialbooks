package com.apiweb.socialbooks.Service;

import com.apiweb.socialbooks.Model.UserProfilesView;
import com.apiweb.socialbooks.Model.UsersModel;
import com.apiweb.socialbooks.Repository.UserProfilesRepository;
import com.apiweb.socialbooks.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    @Override
    public String getRecordType() {
        return "user";
    }

    @Override
    public String createRecord(UsersModel user) {
        usersRepository.save(user);
        return String.format("The user %s with the id %s has been created successfully", user.getFullName(), user.getId());
    }

    @Override
    public List<UsersModel> getRecords() {
        return usersRepository.findAll();
    }

    @Override
    public UsersModel getRecordById(ObjectId id) { return usersRepository.findById(id).orElse(null); }

    @Override
    public String deleteRecord(ObjectId id) {
        usersRepository.deleteById(id);
        return String.format("The user with id %s has been deleted successfully", id);
    }

    @Override
    public List<UserProfilesView> getUserProfiles() { return userProfilesRepository.findAll(); }

    @Override
    public String deleteUserFromFriendLists(ObjectId id) {
        Query query = new Query();
        Update update = new Update().pull("friends", id);
        mongoTemplate.updateMulti(query, update, "Users");
        return String.format("The user with id %s has been removed from friendlists", id);
    }
}
