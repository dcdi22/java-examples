package com.trilogyed.comment.dao;

import com.trilogyed.comment.model.Comment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentDaoTest {

    @Qualifier("commentDao")
    @Autowired
    CommentDao commentDao;

    private Comment comment1, comment2;

    @Before
    public void setUp() throws Exception {
        commentDao.getAllComments().forEach(c -> {
            commentDao.deleteComment(c.getCommentId());
        });


        comment1 = new Comment();
        comment1.setCommentId(1);
        comment1.setPostId(1);
        comment1.setCreateDate(LocalDate.of(2019, 06, 19));
        comment1.setCommenterName("Delcie");
        comment1.setComment("A comment");

        comment2 = new Comment();
        comment2.setCommentId(2);
        comment2.setPostId(2);
        comment2.setCreateDate(LocalDate.of(2019, 06, 19));
        comment2.setCommenterName("Michelle");
        comment2.setComment("A comment");

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getAddDeleteComment() {
        comment1 = commentDao.addComment(comment1);
        Comment test = commentDao.getComment(comment1.getCommentId());
        assertEquals(comment1, test);

        commentDao.deleteComment(comment1.getCommentId());

        test = commentDao.getComment(comment1.getCommentId());

        assertNull(test);
    }

    @Test
    public void getAllComments() {
        comment1 = commentDao.addComment(comment1);
        comment2 = commentDao.addComment(comment2);

        List<Comment> comments = commentDao.getAllComments();

        assertEquals(2, comments.size());
    }

    @Test
    public void getCommentsByPostId() {
        comment1 = commentDao.addComment(comment1);
        comment2 = commentDao.addComment(comment2);

        List<Comment> comments = commentDao.getCommentsByPostId(1);

        assertEquals(1, comments.size());
    }

    @Test
    public void updateComment() {
        comment1 = commentDao.addComment(comment1);

        comment1.setCommenterName("Richard");

        commentDao.updateComment(comment1);

        Comment test = commentDao.getComment(comment1.getCommentId());

        assertEquals(comment1, test);
    }
}