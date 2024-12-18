package com.example.demo.controllers;

import com.example.demo.ResourceNotFoundException;
import com.example.demo.entities.Comment;
import com.example.demo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Add a comment to a post
    @PostMapping("/{postId}/comments")
    public void addCommentToPost(@PathVariable Long postId, @RequestBody Comment comment){
        commentService.addComment(postId, comment);
    }

    // Delete a comment by ID and post ID
    @DeleteMapping("/comments/{commentId}/posts/{postId}")
    public void deleteComment(@PathVariable Long commentId, @PathVariable Long postId){
        commentService.deleteComment(commentId, postId);
    }

    // Get all comments for a post
    @GetMapping("/{postId}/comments")
    public List<Comment> getAllCommentsByPost(@PathVariable Long postId) {
        return commentService.getAllCommentsByPost(postId);
    }

    // Get a comment by its ID
    @GetMapping("/comments/{commentId}")
    public Comment getComment(@PathVariable Long commentId) {
        return commentService.getComment(commentId);
    }

    // Handle resource not found errors globally with a custom exception handler
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFound(ResourceNotFoundException ex) {
        return ex.getMessage();
    }
}

