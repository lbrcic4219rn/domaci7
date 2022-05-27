package com.example.domaci7.services;

import com.example.domaci7.entities.Comment;
import com.example.domaci7.entities.Post;
import com.example.domaci7.repo.commnet.CommentRepo;
import com.example.domaci7.repo.post.PostRepo;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    @Inject
    private CommentRepo commentRepo;

    public Comment addComment(Comment comment) {
        return this.commentRepo.addComment(comment);
    }


    public List<Comment> getCommentsByPostId(Integer id) {
        return this.commentRepo.getCommentsByPostId(id);
    }
}
