package com.example.demo.services;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.repos.CommentRepo;
import org.apache.tomcat.util.http.parser.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostService postService;

    public void addComment(Long postId, Comment comment){

       Post post =  postService.getAPostById(postId);  // fetch the post using the id given to us
        // add the comment to the post
        comment.setPost(post);
        //save the comment to the database
        commentRepo.save(comment);

    }

    public void deleteComment( Long commentId, Long postId){
        Comment comment =  commentRepo.findByIdAndPostId(commentId, postId);
        commentRepo.delete(comment);

    }


}
