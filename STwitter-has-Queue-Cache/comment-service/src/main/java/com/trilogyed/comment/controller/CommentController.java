package com.trilogyed.comment.controller;

import com.trilogyed.comment.dao.CommentDao;
import com.trilogyed.comment.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping(value = "/comments")
public class CommentController {

    @Autowired
    CommentDao commentDao;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Comment createComment(@RequestBody @Valid Comment comment) {
        return commentDao.addComment(comment);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Comment getComment(@PathVariable int id) throws EntityNotFoundException {
        try {
            commentDao.getComment(id).getCommentId();
        } catch (Exception e) {
            throw new EntityNotFoundException("ID " + id + " does not exist");
        }
        return commentDao.getComment(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Comment> getAllComments() {
        if (commentDao.getAllComments().size() > 0) {
            return commentDao.getAllComments();
        } else {
            throw new IllegalArgumentException("No Comments found");
        }
    }

    @RequestMapping(value = "/postId/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Comment> getCommentsByPostId(@PathVariable int id) {
        return commentDao.getCommentsByPostId(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateComment(@RequestBody @Valid Comment comment, @PathVariable int id) {
//        try {
//            commentDao.getcomment(id).getcommentID();
//        } catch (Exception e) {
//            throw new IllegalArgumentException("ID " + id + " does not exist");
//        }
        comment.setCommentId(id);
        commentDao.updateComment(comment);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteComment(@PathVariable int id) {
//        try {
//            commentDao.getcomment(id).getcommentID();
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Cannot Delete the ID " + id + " does not exist");
//        }

        commentDao.deleteComment(id);

    }
}

