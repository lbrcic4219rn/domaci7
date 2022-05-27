package com.example.domaci7.repo.commnet;

import com.example.domaci7.entities.Comment;
import com.example.domaci7.repo.MySqlAbstractRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentRepo extends MySqlAbstractRepo implements CommentRepo {
    @Override
    public Comment addComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO comment (postID, comment, name) VALUES (?, ?, ?)",
                    generatedColumns
            );
            preparedStatement.setInt(1, comment.getPostId());
            preparedStatement.setString(2, comment.getComment());
            preparedStatement.setString(3, comment.getName());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comment;    }

    @Override
    public List<Comment> getCommentsByPostId(int id) {
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM comment WHERE postID = ?"
            );
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                comments.add(new Comment(
                        resultSet.getInt("commentID"),
                        resultSet.getInt("postID"),
                        resultSet.getString("name"),
                        resultSet.getString("comment")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }
}
