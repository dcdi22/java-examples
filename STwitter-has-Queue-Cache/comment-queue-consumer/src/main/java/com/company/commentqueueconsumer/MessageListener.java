package com.company.commentqueueconsumer;

import com.company.commentqueueconsumer.util.feign.CommentServiceClient;
import com.company.commentqueueconsumer.util.messages.Comment;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @Autowired
    CommentServiceClient client;

    public MessageListener(CommentServiceClient client) {
        this.client = client;
    }

    @RabbitListener(queues = CommentQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessages(Comment comment) {
        if (comment.getCommentId() == 0) {
            client.createComment(comment);
        } else {
            client.updateComment(comment, comment.getCommentId());
        }
    }

}
