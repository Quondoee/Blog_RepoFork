package com.example.demo.services;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.repos.CommentRepo;
import org.apache.tomcat.util.http.parser.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostService postService;

    public void addComment(Long postId, Comment comment) {

        Post post = postService.getAPostById(postId);

        comment.setPost(post);

        commentRepo.save(comment);

    }

    public void deleteComment(Long commentId, Long postId) {
        Comment comment = commentRepo.findByIdAndPostId(commentId, postId);
        commentRepo.delete(comment);

    }


    public Comment getComment(Long commentId) {
        return commentRepo.findById(commentId).orElse(null);
    }

    public List<Comment> getAllCommentsByPost(Long postId) {
        return commentRepo.findByPostId(postId);
    }
}
