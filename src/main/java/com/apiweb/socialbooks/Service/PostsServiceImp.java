package com.apiweb.socialbooks.Service;

import com.apiweb.socialbooks.DTO.UserPostsDTO;
import com.apiweb.socialbooks.Model.PostsModel;
import com.apiweb.socialbooks.Repository.PostsRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImp implements IPostsService {

    //  --------- Dependency injection ------------------
    final PostsRepository postsRepository;

    public PostsServiceImp(PostsRepository postsRepository) { this.postsRepository = postsRepository; }
    // --------------------------------------------------

    @Override
    public String getRecordType() { return "post"; }

    @Override
    public String createRecord(PostsModel post) {
        postsRepository.save(post);
        return String.format("The post with id %s has been created successfully", post.getId());
    }

    @Override
    public List<PostsModel> getRecords() { return postsRepository.findAll(); }

    @Override
    public PostsModel getRecordById(ObjectId id) { return postsRepository.findById(id).orElse(null); }

    @Override
    public String deleteRecord(ObjectId id) {
        postsRepository.deleteById(id);
        return String.format("The post with id %s has been deleted successfully", id);
    }

    @Override
    public List<UserPostsDTO> getUserPosts() { return postsRepository.getUserPosts(); }
}
