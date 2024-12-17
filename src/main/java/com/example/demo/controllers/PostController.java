package com.example.demo.controllers;

import com.example.demo.entities.Post;
import com.example.demo.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping("/posts")
    public ResponseEntity<Void> createPost(@RequestBody Post post){
        postService.addPost(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        logger.info("Created a new post with an id of " + post.getId());
        return ResponseEntity.created(location).build();
    }

    //Update a Post

    // get a single post

    //getAllPostByTitle using Query Parameters =  @RequestParam

    //delete a post


}
