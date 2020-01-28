package com.company.commentqueueconsumer.util.feign;

import com.company.commentqueueconsumer.util.messages.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "comment-service")
public interface CommentServiceClient {

    @PostMapping(value = "/comments")
    Comment createComment(@RequestBody Comment comment);

    @PutMapping(value = "/comments/{id}")
    void updateComment(@RequestBody Comment comment, @PathVariable int id);

}
