package com.apiweb.socialbooks.Controller;

import com.apiweb.socialbooks.Exception.ResourceNotFound;
import com.apiweb.socialbooks.Model.PostsModel;
import com.apiweb.socialbooks.Model.UsersModel;
import com.apiweb.socialbooks.Service.IPostsService;
import com.apiweb.socialbooks.Service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiweb/posts")
public class PostsController {
    @Autowired
    IPostsService postsService;

    @Autowired
    IUsersService usersService;

    @PostMapping ("/")
    public ResponseEntity<String> createPost(@RequestBody PostsModel post) {
        try {
            UsersModel user = usersService.getUserById(post.getUserId());
            if (user == null) {
                throw new ResourceNotFound(String.format("Error creating post. The user with id %s was not found", post.getUserId()));
            }

            return new ResponseEntity<>(postsService.createPost(post), HttpStatus.CREATED);
        } catch (ResourceNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<PostsModel>> getPosts() {
        return new ResponseEntity<>(postsService.getPosts(), HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<PostsModel> getPostById(@PathVariable String id) {
//        try {
//            PostsModel postFound =
//        }
//    }
//
//    private PostsModel validateId()
}
