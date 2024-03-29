package com.example.domaci7.repo.post;

import com.example.domaci7.entities.Post;
import com.example.domaci7.repo.MySqlAbstractRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlPostRepo extends MySqlAbstractRepo implements PostRepo {
    @Override
    public Post addPost(Post post) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO post (author, title, content, upload_date) VALUES (?, ?, ?, NOW())",
                    generatedColumns
            );

            preparedStatement.setString(1, post.getAuthor());
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getContent());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                System.out.println("RESULT SET: " + resultSet.toString());
                System.out.println("datum create: " + resultSet.getDate("upload_date"));
                System.out.println("vreme create: " + resultSet.getTime("upload_date"));

                post.setId(resultSet.getInt(1));
                post.setUploadDate(resultSet.getDate("upload_date"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return post;
    }

    @Override
    public List<Post> allPosts() {
        List<Post> posts = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT * FROM post"
            );

            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getInt("postID"),
                        resultSet.getString("author"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("upload_date")
                ));
                System.out.println("vreme: " + resultSet.getDate("upload_date"));
                System.out.println("vreme create: " + resultSet.getTime("upload_date"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return posts;
    }

    @Override
    public Post findPostById(int id) {
        Post post = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM post WHERE postID=?"
            );
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                post = new Post(
                        resultSet.getInt("postID"),
                        resultSet.getString("author"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("upload_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return post;
    }
}
