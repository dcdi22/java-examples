package com.trilogyed.comment.dao;

import com.trilogyed.comment.model.Comment;

import java.util.List;

public interface CommentDao {

    Comment addComment(Comment comment);

    Comment getComment(int commentId);

    List<Comment> getAllComments();

    List<Comment> getCommentsByPostId(int postId);

    void updateComment(Comment comment);

    void deleteComment(int commentId);

}
