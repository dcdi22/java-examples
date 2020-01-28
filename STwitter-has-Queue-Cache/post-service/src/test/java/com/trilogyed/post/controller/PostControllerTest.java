package com.trilogyed.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.post.dao.PostDao;
import com.trilogyed.post.model.Post;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostDao postDao;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getPostWithJson() throws Exception {
        Post post = new Post(
                1,
                LocalDate.of(2019, 06,19),
                "Delcie",
                "Great Book"
        );

        String outputJson = mapper.writeValueAsString(post);

        when(postDao.getPost(1)).thenReturn(post);

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
    public void createPost() throws Exception {
        Post inPost = new Post();
        inPost.setPostDate(LocalDate.of(2019, 06,19));
        inPost.setPosterName("Michelle");
        inPost.setPost("Great Book");

        String inputJson = mapper.writeValueAsString(inPost);

        Post outPost = new Post(
                1,
                LocalDate.of(2019, 06,19),
                "Michelle",
                "Great Book"
        );

        String outputJson = mapper.writeValueAsString(outPost);

        when(postDao.addPost(inPost)).thenReturn(outPost);

        this.mockMvc.perform(post("/posts")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getAllPosts() throws Exception {
        Post post = new Post(
                1,
                LocalDate.of(2019, 06,19),
                "Delcie",
                "Great Book"
        );
        Post post2 = new Post(
                2,
                LocalDate.of(2019, 06,19),
                "Michelle",
                "Great Book"
        );

        List<Post> posts = new ArrayList<>();
        posts.add(post);
        posts.add(post2);

        when(postDao.getAllPosts()).thenReturn(posts);

        String outputJson = mapper.writeValueAsString(posts);

        this.mockMvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getPostsByPoster() throws Exception {
        Post post = new Post(
                1,
                LocalDate.of(2019, 06,19),
                "Delcie",
                "Great Book"
        );
        Post post2 = new Post(
                2,
                LocalDate.of(2019, 06,19),
                "Delcie",
                "Great Book"
        );

        List<Post> posts = new ArrayList<>();
        posts.add(post);
        posts.add(post2);

        when(postDao.getPostByPoster("Delcie")).thenReturn(posts);

        String outputJson = mapper.writeValueAsString(posts);

        this.mockMvc.perform(get("/posts/poster/Delcie"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void updatePost() throws Exception {
        Post post = new Post(
                1,
                LocalDate.of(2019, 06,19),
                "Delcie",
                "Great Book"
        );

        String inputJson = mapper.writeValueAsString(post);

        this.mockMvc.perform(put("/posts/1")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deletePost() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/posts/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}