package com.example.domaci7.services;

import com.example.domaci7.entities.Comment;
import com.example.domaci7.entities.Post;
import com.example.domaci7.repo.post.PostRepo;

import javax.inject.Inject;
import java.util.List;

public class PostService {

    @Inject
    private PostRepo postRepo;

    public Post addPost(Post post) {
        return this.postRepo.addPost(post);
    }

    public List<Post> allPosts() {
        return this.postRepo.allPosts();
    }

    public Post findPost(Integer id) {
        return this.postRepo.findPostById(id);
    }

}
