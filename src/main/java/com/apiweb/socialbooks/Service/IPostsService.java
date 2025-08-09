package com.apiweb.socialbooks.Service;

import com.apiweb.socialbooks.DTO.UserPostsDTO;
import com.apiweb.socialbooks.Model.PostsModel;
import org.bson.types.ObjectId;

import java.util.List;

public interface IPostsService {
    String createPost(PostsModel post);
    List<PostsModel> getPosts();
    PostsModel getPostById(ObjectId id);
    String deletePost(ObjectId id);
    List<UserPostsDTO> getUserPosts();
}
