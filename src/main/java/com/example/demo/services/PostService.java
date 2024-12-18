package com.example.demo.services;

import com.example.demo.ResourceNotFoundException;
import com.example.demo.entities.Post;
import com.example.demo.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    // Autowiring the PostRepo to access the database
    @Autowired
    private PostRepo postRepo;

    // Fetch all posts
    public List<Post> getAllPosts() {
        return (List<Post>) postRepo.findAll();  // This fetches all posts from the database
    }

    // Create a new post
    public void addPost(Post post) {
        postRepo.save(post);  // This saves the new post into the database
    }

    // Fetch a post by its ID
    public Post getAPostById(Long postId) {
        // Using the findById method to check if the post exists
        Optional<Post> post = postRepo.findById(postId);

        // If the post is found, return it. If not, throw an exception.
        if (post.isPresent()) {
            return post.get();  // Returns the post if found
        } else {
            // If no post is found, throw a ResourceNotFoundException
            throw new ResourceNotFoundException("Post with id " + postId + " not found");
        }
    }

    // Fetch posts by title (search posts by title)
    public List<Post> getPostsByTitle(String title) {
        // This is a simple example. We'll assume a method exists in the repository to find posts by title
        return postRepo.findByTitleContainingIgnoreCase(title);  // Finds posts with titles containing the search term
    }

    // Delete a post by its ID
    public boolean deletePost(Long postId) {
        // Check if the post exists before trying to delete
        Optional<Post> post = postRepo.findById(postId);
        if (post.isPresent()) {
            postRepo.delete(post.get());  // Deletes the post from the database
            return true;  // Return true to indicate the post was deleted
        } else {
            return false;  // Return false if no post was found to delete
        }
    }

    // Update an existing post
    public Post updatePost(Long postId, Post post) {
        // Fetch the existing post by ID
        Optional<Post> existingPostOptional = postRepo.findById(postId);

        if (existingPostOptional.isPresent()) {
            Post existingPost = existingPostOptional.get();
            existingPost.setTitle(post.getTitle());  // Update the title
            existingPost.setContent(post.getContent());  // Update the content
            return postRepo.save(existingPost);  // Save the updated post and return it
        } else {
            throw new ResourceNotFoundException("Post with id " + postId + " not found");  // Throw exception if post is not found
        }
    }
}