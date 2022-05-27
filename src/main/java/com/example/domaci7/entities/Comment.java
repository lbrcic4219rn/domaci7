package com.example.domaci7.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Comment {

    private int id;

    @NotEmpty(message = "Name of author is required")
    @NotNull(message = "Name of author is required")
    private String name;

    @NotEmpty(message = "Content is required")
    @NotNull(message = "Content is required")
    private String comment;

    private int postId;

    public Comment() {
    }

    public Comment(int id, int postId, String name, String comment) {
        this.id = id;
        this.postId = postId;
        this.name = name;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
