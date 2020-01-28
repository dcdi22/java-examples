package com.trilogyed.stwitter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.stwitter.models.PostViewModel;
import com.trilogyed.stwitter.service.ServiceLayer;
import com.trilogyed.stwitter.util.messages.Comment;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@RunWith(SpringRunner.class)
@WebMvcTest(StwitterController.class)
public class StwitterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ServiceLayer service;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getPostReturnWithJson() throws Exception {
        PostViewModel view = new PostViewModel(
                1,
                "Post Content",
                LocalDate.of(2019, 06, 19),
                "Delcie",
                null
        );

        String outputJson = mapper.writeValueAsString(view);

        when(service.getPost(1)).thenReturn(view);

        this.mockMvc.perform(get("/posts/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getPostReturn404() throws Exception {
        this.mockMvc.perform(get("/posts/999"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getPostsForPosterReturnWithJson() throws Exception {
        PostViewModel view = new PostViewModel(
                1,
                "Post Content",
                LocalDate.of(2019, 06, 19),
                "Delcie",
                null
        );

        List<PostViewModel> pvmList = new ArrayList<>();
        pvmList.add(view);

        String outputJson = mapper.writeValueAsString(pvmList);


        when(service.getPostsForPoster("Delcie")).thenReturn(pvmList);

        this.mockMvc.perform(get("/posts/user/Delcie"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getPostsForPosterReturn404() throws Exception {
        this.mockMvc.perform(get("/posts/user/Michelle"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void createComment() throws Exception {
        // input with out id
        Comment inComment = new Comment();
        inComment.setPostId(1);
        inComment.setCommentDate(LocalDate.of(2019,06,19));
        inComment.setCommenterName("Delcie");
        inComment.setComment("A comment");

        String inputJson = mapper.writeValueAsString(inComment);

        String outputJson = mapper.writeValueAsString(inComment.getComment());

        when(service.createComment(inComment)).thenReturn(inComment.getComment());

        this.mockMvc.perform(post("/comments")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void createPost() throws Exception {
        PostViewModel inPost = new PostViewModel();
        inPost.setPostContent("Post Content");
        inPost.setPostDate(LocalDate.of(2019, 06, 19));
        inPost.setPosterName("Delcie");
        inPost.setComments(null);

        String inputJson = mapper.writeValueAsString(inPost);


        PostViewModel outPost = new PostViewModel(
                1,
                "Post Content",
                LocalDate.of(2019, 06, 19),
                "Delcie",
                null
        );

        String outputJson = mapper.writeValueAsString(outPost);

        when(service.createPost(inPost)).thenReturn(outPost);

        this.mockMvc.perform(post("/posts")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }
}