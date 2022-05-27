package com.example.domaci7.repo.post;

import com.example.domaci7.entities.Post;

import java.util.List;

public interface PostRepo {
    public Post addPost(Post post);
    public List<Post> allPosts();
    public Post findPostById(int id);
}
