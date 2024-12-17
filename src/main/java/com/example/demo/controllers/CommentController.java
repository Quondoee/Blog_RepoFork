package com.example.demo.controllers;

import com.example.demo.entities.Comment;
import com.example.demo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {


    @Autowired
    private CommentService commentService;

    @PostMapping("/comments/{postId}/comments")
    public void addCommentToPost(@PathVariable Long postId, @RequestBody Comment comment){
        commentService.addComment(postId, comment);
    }

    //Delete a comment
    @DeleteMapping("/comments/{commentId}/posts/{postId}")
    public void deleteComment(@PathVariable Long commentId, @PathVariable Long postId){
        commentService.deleteComment(commentId, postId);
    }

    //Get All comments

    //Get a comment  - throw a resource not found exception

    // GetALLCommentsByPost




}
