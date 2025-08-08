package com.apiweb.socialbooks.Service;

import com.apiweb.socialbooks.Model.UserProfilesView;
import com.apiweb.socialbooks.Model.UsersModel;
import com.apiweb.socialbooks.Repository.UserProfilesRepository;
import com.apiweb.socialbooks.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImp implements IUsersService {
    @Autowired
    UserRepository usersRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserProfilesRepository  userProfilesRepository;

    @Override
    public String createUser(UsersModel usuario) {
        usersRepository.save(usuario);
        return String.format("The user %s with the id %s has been created successfully", usuario.getFullName(), usuario.getId());
    }

    @Override
    public List<UsersModel> getUsers() {
        return usersRepository.findAll();
    }

    @Override
    public UsersModel getUserById(String id) {
        return null;
    }

    @Override
    public List<UserProfilesView> getUserProfiles() {
        return List.of();
    }

    @Override
    public String deleteUser(ObjectId id) {
        return "";
    }

    @Override
    public String deleteUserFromFriends(ObjectId id) {
        return "";
    }
}
