package com.apiweb.socialbooks.Repository;

import com.apiweb.socialbooks.DTO.UserPostsDTO;
import com.apiweb.socialbooks.Model.PostsModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostsRepository extends MongoRepository<PostsModel, ObjectId> {
    @Aggregation(pipeline = {
            "{ $group: {_id: 'userId', publicaciones: { $push: '$$ROOT' }}}",
            "{ $lookup: { from: 'Users', localField: '_id', foreignField: '_id', as: 'result'}}",
            "{ $unwind: { path: '$result' } }",
            "{ $project: {_id: '0', user: '$result.credentials.username', profilePicture: '$result.profilePicture', name: '$result.fullName', description: '$result.description', posts: 1}}"
    })
    List<UserPostsDTO> getUserPosts();
}
