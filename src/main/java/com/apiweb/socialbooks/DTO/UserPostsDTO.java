package com.apiweb.socialbooks.DTO;

import com.apiweb.socialbooks.Model.PostsModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostsDTO {
    private String user;
    private String profilePic;
    private String name;
    private String description;
    private List<PostsModel> posts = new ArrayList<>();
}
