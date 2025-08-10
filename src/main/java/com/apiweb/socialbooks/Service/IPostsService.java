package com.apiweb.socialbooks.Service;

import com.apiweb.socialbooks.DTO.UserPostsDTO;
import com.apiweb.socialbooks.Model.PostsModel;

import java.util.List;

public interface IPostsService extends BaseService<PostsModel> {
    List<UserPostsDTO> getUserPosts();
}
