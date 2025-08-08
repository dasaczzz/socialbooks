package com.apiweb.socialbooks.Repository;

import com.apiweb.socialbooks.Model.UsersModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UsersModel, ObjectId> {}