package com.example.demo.services;

import com.example.demo.ResourceNotFoundException;
import com.example.demo.entities.Post;
import com.example.demo.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    public List<Post> getAllPosts(){
        List<Post> postList = new ArrayList<>();  // make a list to store all the post into
        // repeatedly fetch all the post and store each post into the list
        for(Post post : postRepo.findAll()){
            postList.add(post);
        }
        // return the list
        return postList;
    }

    //create post
    public void addPost(Post post){
      postRepo.save(post);
    }

    // fetch the post using the id given to us
    public Post getAPostById(Long postId){
        for(Post post: getAllPosts() ){
            if(post.getId().equals(postId)){
                return post;
            }
        }
         throw new ResourceNotFoundException("Post with id of " + postId + " not found");
    }





}
