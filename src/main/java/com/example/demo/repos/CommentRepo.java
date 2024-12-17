package com.example.demo.repos;

import com.example.demo.entities.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentRepo extends CrudRepository<Comment, Long> {
    Comment findByIdAndPostId(Long id, Long postId);
}
