package com.apiweb.socialbooks.Service;

import com.apiweb.socialbooks.DTO.UserPostsDTO;
import com.apiweb.socialbooks.Lib.BaseResponse;
import com.apiweb.socialbooks.Model.PostsModel;
import com.apiweb.socialbooks.Repository.PostsRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
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
    public BaseResponse createRecord(PostsModel post) {
        postsRepository.save(post);
        return new BaseResponse(
            HttpStatus.CREATED.value(),
            String.format("The post with id %s has been created successfully", post.getId()),
            "POST",
            "/apiweb/posts/"
        );
    }

    @Override
    public List<PostsModel> getRecords() { return postsRepository.findAll(); }

    @Override
    public PostsModel getRecordById(ObjectId id) { return postsRepository.findById(id).orElse(null); }

    @Override
    public BaseResponse deleteRecord(ObjectId id) {
        postsRepository.deleteById(id);
        return new BaseResponse(
                HttpStatus.NO_CONTENT.value(),
                String.format("The post with id %s has been deleted successfully", id),
                "DELETE",
                String.format("/apiweb/posts/%s", id)
        );
    }

    @Override
    public List<UserPostsDTO> getUserPosts() { return postsRepository.getUserPosts(); }
}
