package com.example.domaci7;

import com.example.domaci7.repo.commnet.CommentRepo;
import com.example.domaci7.repo.commnet.MySqlCommentRepo;
import com.example.domaci7.repo.post.MySqlPostRepo;
import com.example.domaci7.repo.post.PostRepo;
import com.example.domaci7.services.CommentService;
import com.example.domaci7.services.PostService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;


@ApplicationPath("/api")
public class Application extends ResourceConfig {
    public Application() {
        // Ukljucivanje validacije
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisanje implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlPostRepo.class).to(PostRepo.class).in(Singleton.class);
                this.bindAsContract(PostService.class);

                this.bind(MySqlCommentRepo.class).to(CommentRepo.class).in(Singleton.class);
                this.bindAsContract(CommentService.class);
            }
        };
        register(binder);

        packages("com.example.domaci7");
    }
}