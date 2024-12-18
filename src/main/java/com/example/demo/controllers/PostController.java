package com.example.demo.controllers;

import com.example.demo.entities.Post;
import com.example.demo.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    // Get all posts
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // Create a new post
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

    // Update a post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post post) {
        Post updatedPost = postService.updatePost(postId, post);
        if (updatedPost != null) {
            logger.info("Post with id " + postId + " has been updated.");
            return ResponseEntity.ok(updatedPost);
        } else {
            logger.warn("Post with id " + postId + " not found.");
            return ResponseEntity.notFound().build();
        }
    }

    // Get a single post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        Post post = postService.getAPostById(postId);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get posts by title (using query parameters)
    @GetMapping("/posts/search")
    public ResponseEntity<List<Post>> getPostsByTitle(@RequestParam String title) {
        List<Post> posts = postService.getPostsByTitle(title);
        return ResponseEntity.ok(posts);
    }

    // Delete a post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        boolean deleted = postService.deletePost(postId);
        if (deleted) {
            logger.info("Post with id " + postId + " has been deleted.");
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Post with id " + postId + " not found.");
            return ResponseEntity.notFound().build();
        }
    }
}
