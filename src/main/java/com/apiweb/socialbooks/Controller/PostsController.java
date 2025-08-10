package com.apiweb.socialbooks.Controller;

import com.apiweb.socialbooks.Exception.ResourceNotFound;
import com.apiweb.socialbooks.Model.PostsModel;
import com.apiweb.socialbooks.Model.UsersModel;
import com.apiweb.socialbooks.Service.IPostsService;
import com.apiweb.socialbooks.Service.IUsersService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiweb/posts")
public class PostsController implements BaseController<PostsModel> {
    @Autowired
    IPostsService postsService;

    @Autowired
    IUsersService usersService;

    @PostMapping ("/")
    public ResponseEntity<String> createRecord(@RequestBody PostsModel post) {
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
    public ResponseEntity<List<PostsModel>> getRecords() {
        return new ResponseEntity<>(postsService.getPosts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecordById(@PathVariable String id) {
        try {
            PostsModel postFound = validateId(id);
            return new ResponseEntity<>(postFound, HttpStatus.OK);
        } catch (ResourceNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable String id) {
        try {
            PostsModel postFound = validateId(id);
            return new ResponseEntity<>(postsService.deletePost(postFound.getId()), HttpStatus.OK);
        } catch(ResourceNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    private PostsModel validateId(String id) throws ResourceNotFound {
        if (!ObjectId.isValid(id)){
            throw new ResourceNotFound(String.format("Post with id %s was not found", id));
        }
        PostsModel post = postsService.getPostById(new ObjectId(id));
        if (post == null) {
            throw new ResourceNotFound(String.format("Post with id %s was not found", id));
        }
        return post;
    }
}
