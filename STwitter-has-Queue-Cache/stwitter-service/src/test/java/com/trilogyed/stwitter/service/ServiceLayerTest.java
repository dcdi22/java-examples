package com.trilogyed.stwitter.service;

import com.trilogyed.stwitter.models.Post;
import com.trilogyed.stwitter.models.PostViewModel;
import com.trilogyed.stwitter.util.feign.CommentClient;
import com.trilogyed.stwitter.util.feign.PostClient;
import com.trilogyed.stwitter.util.messages.Comment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceLayerTest {

    @InjectMocks
    private ServiceLayer serviceLayer;
    @Mock
    private static CommentClient commentClient;
    @Mock
    private PostClient postClient;
    @Mock
    private RabbitTemplate rabbitTemplate;

    @Before
    public void setUp() throws Exception {
//        setUpCommentClientMock();
        setUpPostClientMock();

        serviceLayer = new ServiceLayer(commentClient, postClient, rabbitTemplate);
    }

//    private void setUpCommentClientMock() {
//        Comment expected = new Comment();
//        expected.setCommentId(1);
//        expected.setPostId(1);
//        expected.setCommenterName("Delcie");
//        expected.setCommentDate(LocalDate.of(2019, 06, 19));
//        expected.setComment("A COMMENT");
//
//        doReturn(expected).when(commentClient).getComment(expected.getCommentId());
//    }

    private void setUpPostClientMock() {
        Post expected = new Post();
        expected.setPostID(1);
        expected.setPostDate(LocalDate.of(2019, 06, 19));
        expected.setPosterName("Delcie");
        expected.setPost("A POST");

        Post post0 = new Post();
        post0.setPostDate(LocalDate.of(2019, 06, 19));
        post0.setPosterName("Delcie");
        post0.setPost("A POST");

        List<Post> postList = new ArrayList<>();
        postList.add(expected);

        doReturn(expected).when(postClient).createPost(post0);
        doReturn(expected).when(postClient).getPost(expected.getPostID());
        doReturn(postList).when(postClient).getPostsByPoster("Delcie");
    }


    @Test
    public void getPost() {
        PostViewModel pvm = new PostViewModel();
        pvm.setPostID(1);
        pvm.setPostDate(LocalDate.of(2019, 06, 19));
        pvm.setPosterName("Delcie");
        pvm.setPostContent("A POST");
        pvm.setComments(commentClient.getComments());



        PostViewModel tested = serviceLayer.getPost(1);

        assertEquals(tested, pvm);
    }

    @Test
    public void getPostsByPoster() {
        PostViewModel pvm = new PostViewModel();
        pvm.setPostID(1);
        pvm.setPostDate(LocalDate.of(2019, 06, 19));
        pvm.setPosterName("Delcie");
        pvm.setPostContent("A POST");
        pvm.setComments(commentClient.getComments());

        Post expected = new Post();
        expected.setPostID(1);
        expected.setPostDate(LocalDate.of(2019, 06, 19));
        expected.setPosterName("Delcie");
        expected.setPost("A POST");

        PostViewModel fromService = serviceLayer.getPost(expected.getPostID());

        List<PostViewModel> pvmList =  new ArrayList<>();
        pvmList.add(fromService);



        assertEquals(pvmList, serviceLayer.getPostsForPoster("Delcie"));
    }

    @Test
    public void createPost() {
        PostViewModel pvm = new PostViewModel();
        pvm.setPostID(1);
        pvm.setPostDate(LocalDate.of(2019, 06, 19));
        pvm.setPosterName("Delcie");
        pvm.setPostContent("A POST");
        pvm.setComments(commentClient.getComments());



        PostViewModel expected = new PostViewModel();
        expected.setPostID(1);
        expected.setPostDate(LocalDate.of(2019, 06, 19));
        expected.setPosterName("Delcie");
        expected.setPostContent("A POST");
        expected.setComments(commentClient.getComments());

        assertEquals(serviceLayer.createPost(pvm), expected);



    }

//    @Test
//    public void


    /*
    private int postId;
    private String postContent; // == post
    private LocalDate postDate;
    private String posterName;
    private List<Comment> comments;
     */

//    private static PostViewModel view;
//    private static Post post;
//    private static Comment comment;
//    static {
//        post = new Post(
//                1,
//                LocalDate.of(2019, 06, 19),
//                "Delcie",
//                "This is my post"
//        );
//        comment = new Comment(
//                1,
//                1,
//                LocalDate.of(2019, 06, 19),
//                "Michelle",
//                "This is my comment"
//
//        );
//        List<Comment> comments = new ArrayList<>();
//        comments.add(comment);
//        view = new PostViewModel(
//           1, "POST CONTENT", LocalDate.of(2019, 06, 19), "Delcie", comments
//        );
//    }



}