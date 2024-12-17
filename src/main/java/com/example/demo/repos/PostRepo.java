package com.example.demo.repos;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepo extends CrudRepository<Post, Long> {


}
