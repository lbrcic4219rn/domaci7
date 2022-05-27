package com.example.domaci7.repo.commnet;

import com.example.domaci7.entities.Comment;
import com.example.domaci7.entities.Post;

import java.util.List;

public interface CommentRepo {
    public Comment addComment(Comment comment);
    public List<Comment> getCommentsByPostId(int id);
}
