package com.apiweb.socialbooks.Repository;

import com.apiweb.socialbooks.Model.UserProfilesView;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfilesRepository extends MongoRepository<UserProfilesView,String> {}