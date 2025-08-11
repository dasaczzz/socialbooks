package com.apiweb.socialbooks.Controller;

import com.apiweb.socialbooks.Lib.BaseResponse;
import com.apiweb.socialbooks.Lib.Utils;
import com.apiweb.socialbooks.Model.PostsModel;
import com.apiweb.socialbooks.Service.IPostsService;
import com.apiweb.socialbooks.Service.IUsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiweb/posts")
public class PostsController implements BaseController<PostsModel> {

    //  --------- Dependency injection ------------------
    final IPostsService postsService;
    final IUsersService usersService;

    public PostsController(IPostsService postsService, IUsersService usersService) {
        this.postsService = postsService;
        this.usersService = usersService;
    }
    // --------------------------------------------------

    @PostMapping ("/")
    public ResponseEntity<BaseResponse> createRecord(@RequestBody PostsModel post) {
        Utils.validateEntryId(post.getUserIdAsString(), usersService);
        return new ResponseEntity<>(postsService.createRecord(post), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PostsModel>> getRecords() {
        return new ResponseEntity<>(postsService.getRecords(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostsModel> getRecordById(@PathVariable String id) {
        PostsModel postFound = Utils.validateEntryId(id, postsService);
        return new ResponseEntity<>(postFound, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteRecord(@PathVariable String id) {
        PostsModel postFound = Utils.validateEntryId(id, postsService);
        return new ResponseEntity<>(postsService.deleteRecord(postFound.getId()), HttpStatus.NO_CONTENT);
    }

}
