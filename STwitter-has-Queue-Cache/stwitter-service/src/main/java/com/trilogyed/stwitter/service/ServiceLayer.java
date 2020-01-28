package com.trilogyed.stwitter.service;

import com.trilogyed.stwitter.models.Post;
import com.trilogyed.stwitter.models.PostViewModel;
import com.trilogyed.stwitter.util.feign.CommentClient;
import com.trilogyed.stwitter.util.feign.PostClient;
import com.trilogyed.stwitter.util.messages.Comment;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    /*
    Queue Name: comment-queue

    Exchange Name: comment-exchange

    Routing Key: comment.create.#
     */

    @Autowired
    private CommentClient commentClient;

    @Autowired
    private PostClient postClient;

    public static final String EXCHANGE = "comment-exchange";
    public static final String ROUTING_KEY = "comment.create.service.controller";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public ServiceLayer(CommentClient commentClient, PostClient postClient, RabbitTemplate rabbitTemplate) {
        this.commentClient = commentClient;
        this.postClient = postClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    // =============== CREATE POST ===============

    /**
     *
     * @param pvm
     * @return PostViewModel
     */

    public PostViewModel createPost(PostViewModel pvm) {
        Post post = new Post();
        post.setPostDate(pvm.getPostDate());
        post.setPosterName(pvm.getPosterName());
        post.setPost(pvm.getPostContent());

        Post newPost = postClient.createPost(post);

        //int test = post.getPostId();

        pvm.setPostID(newPost.getPostID());

        return pvm;
    }

    // =============== CREATE COMMENT ===============


    /**
     * Added create comment method in order to give user the ability to
     * add comment beyond default post
     * @param comment
     * @return String
     */

    public String createComment(Comment comment) {
        Comment msg = new Comment(
                comment.getCommentId(),
                comment.getPostId(),
                comment.getCommentDate(),
                comment.getCommenterName(),
                comment.getComment()
        );
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
        System.out.println("Message sent");
        return comment.getComment();
    }

    // =============== GET POST ===============

    /**
     *
     * @param postId
     * @return PostViewModel
     */


    public PostViewModel getPost(int postId) {
//        PostViewModel pvm = buildPostViewModel(postClient.getPost(postId));
//        List<Comment> commentsList = commentClient.getCommentsByPostId(pvm.getPostId());
//        pvm.setComments(commentsList);
//        return pvm;
//        return buildPostViewModel(postClient.getPost(postId));

        /*
        private int postId;
        private String postContent;
        private LocalDate postDate;
        private String posterName;
        private List<Comment> comments;
         */

        Post post = postClient.getPost(postId);
        PostViewModel pvm = new PostViewModel();

        pvm.setPostID(post.getPostID());
        pvm.setPostContent(post.getPost());
        pvm.setPostDate(post.getPostDate());
        pvm.setPosterName(post.getPosterName());
        pvm.setComments(commentClient.getCommentsByPostId(postId));

        return pvm;

    }

    // =============== GET POST BY POSTER ===============

    /**
     *
     * @param posterName
     * @return List of PostViewModel
     */


    public List<PostViewModel> getPostsForPoster(String posterName) {
        List<Post> postList = postClient.getPostsByPoster(posterName);
        List<PostViewModel> pvmList = new ArrayList<>();

        for(Post post: postList) {
//            List<Comment> commentsList = commentClient.getCommentsByPostId(post.getPostId());
            PostViewModel pvm = buildPostViewModel(post);
//            pvm.setComments(commentsList);
            pvmList.add(pvm);
        }

        return pvmList;
    }


    // =============== BUILD POST VIEW MODEL HELPER ===============


    private PostViewModel buildPostViewModel(Post post) {
        /*
        private int postId;
        private String postContent; // == post
        private LocalDate postDate;
        private String posterName;
        private List<Comment> comments;
         */
        PostViewModel pvm = new PostViewModel();
        pvm.setPostID(post.getPostID());
        pvm.setPostContent(post.getPost());
        pvm.setPostDate(post.getPostDate());
        pvm.setPosterName(post.getPosterName());
        pvm.setComments(commentClient.getCommentsByPostId(post.getPostID()));

        return pvm;

    }
}
