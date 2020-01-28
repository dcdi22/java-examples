package com.trilogyed.stwitter.util.feign;

import com.trilogyed.stwitter.util.messages.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "comment-service")
public interface CommentClient {

    @GetMapping(value = "/comments/{id}")
    Comment getComment(@PathVariable int id);

    @GetMapping(value = "/comments")
    List<Comment> getComments();

    @GetMapping(value = "/comments/postId/{id}")
    List<Comment> getCommentsByPostId(@PathVariable int id);

    @DeleteMapping(value = "/comments/{id}")
    void deleteComment(@PathVariable int id);

}
