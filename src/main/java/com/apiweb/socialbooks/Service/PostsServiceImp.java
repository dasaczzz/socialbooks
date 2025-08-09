package com.apiweb.socialbooks.Service;

import com.apiweb.socialbooks.DTO.UserPostsDTO;
import com.apiweb.socialbooks.Model.PostsModel;
import com.apiweb.socialbooks.Repository.PostsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImp implements IPostsService {
    @Autowired
    PostsRepository postsRepository;

    @Override
    public String createPost(PostsModel post) {
        postsRepository.save(post);
        return String.format("The post with id %s has been created successfully", post.getId());
    }

    @Override
    public List<PostsModel> getPosts() { return postsRepository.findAll(); }

    @Override
    public PostsModel getPostById(ObjectId id) { return postsRepository.findById(id).orElse(null); }

    @Override
    public String deletePost(ObjectId id) {
        postsRepository.deleteById(id);
        return String.format("The post with id %s has been deleted successfully", id);
    }

    @Override
    public List<UserPostsDTO> getUserPosts() { return postsRepository.getUserPosts(); }
}
