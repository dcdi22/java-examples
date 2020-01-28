package com.trilogyed.comment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.comment.dao.CommentDao;
import com.trilogyed.comment.model.Comment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CommentController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentDao commentDao;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getCommentReturnWithJson() throws Exception {
        /*
        comment_id int not null auto_increment primary key,
        post_id int not null,
        create_date date not null,
        commenter_name varchar(50) not null,
        comment varchar(255)
         */
        Comment comment = new Comment(
                1,
                1,
                LocalDate.of(2019, 06,19),
                "Delcie",
                "A comment"
        );

        String outputJson = mapper.writeValueAsString(comment);

        when(commentDao.getComment(1)).thenReturn(comment);

        this.mockMvc.perform(get("/comments/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getCommentReturn404() throws Exception {
        this.mockMvc.perform(get("/comments/999"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void createComment() throws Exception {
        Comment inComment = new Comment();
        inComment.setPostId(1);
        inComment.setCreateDate(LocalDate.of(2019, 06, 19));
        inComment.setCommenterName("Delcie");
        inComment.setComment("A comment");

        String inputJson = mapper.writeValueAsString(inComment);

        Comment outComment = new Comment(
                1,
                1,
                LocalDate.of(2019, 06,19),
                "Delcie",
                "A comment"
        );

        String outputJson = mapper.writeValueAsString(outComment);

        when(commentDao.addComment(inComment)).thenReturn(outComment);

        this.mockMvc.perform(post("/comments")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getAllComments() throws Exception {
        Comment comment1 = new Comment(
                1,
                1,
                LocalDate.of(2019, 06,19),
                "Delcie",
                "A comment"
        );

        Comment comment2 = new Comment(
                2,
                1,
                LocalDate.of(2019, 06,19),
                "Delcie",
                "A comment"
        );

        List<Comment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);

        when(commentDao.getAllComments()).thenReturn(comments);

        String outputJson = mapper.writeValueAsString(comments);

        this.mockMvc.perform(get("/comments"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getCommentsByPostId() throws Exception {
        Comment comment1 = new Comment(
                1,
                1,
                LocalDate.of(2019, 06,19),
                "Delcie",
                "A comment"
        );

        Comment comment2 = new Comment(
                2,
                1,
                LocalDate.of(2019, 06,19),
                "Delcie",
                "A comment"
        );

        List<Comment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);

        when(commentDao.getCommentsByPostId(1)).thenReturn(comments);

        String outputJson = mapper.writeValueAsString(comments);

        this.mockMvc.perform(get("/comments/postId/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void updateComment() throws Exception {
        Comment comment1 = new Comment(
                1,
                1,
                LocalDate.of(2019, 06,19),
                "Delcie",
                "A comment"
        );

        String inputJson = mapper.writeValueAsString(comment1);

        this.mockMvc.perform(put("/comments/1")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
// :)
    @Test
    public void deleteComment() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/comments/1"))
              .andDo(print())
              .andExpect(status().isOk())
                .andExpect(content().string(""));

    }
}