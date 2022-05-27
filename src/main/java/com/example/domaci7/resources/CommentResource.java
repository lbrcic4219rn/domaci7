package com.example.domaci7.resources;

import com.example.domaci7.entities.Comment;
import com.example.domaci7.entities.Post;
import com.example.domaci7.services.CommentService;
import com.example.domaci7.services.PostService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comments")
public class CommentResource {

    @Inject
    private CommentService commentService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Comment create(@Valid Comment comment) {
        return this.commentService.addComment(comment);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> find(@PathParam("id") int id) {
        return this.commentService.getCommentsByPostId(id);
    }

}
