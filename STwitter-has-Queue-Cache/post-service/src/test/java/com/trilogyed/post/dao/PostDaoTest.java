package com.trilogyed.post.dao;

import com.trilogyed.post.model.Post;
import javafx.geometry.Pos;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostDaoTest {

    @Qualifier("postDao")
    @Autowired
    PostDao postDao;

    private Post post1, post2;

    @Before
    public void setUp() throws Exception {
        List<Post> posts = postDao.getAllPosts();
        for (Post post: posts) {
            postDao.deletePost(post.getPostID());
        }

        post1 = new Post();
        post1.setPostDate(LocalDate.of(2019, 06, 19));
        post1.setPosterName("Delcie");
        post1.setPost("Post");

        post2 = new Post();
        post2.setPostDate(LocalDate.of(2019, 05, 23));
        post2.setPosterName("Michelle");
        post2.setPost("My Post");
    }

    @Test
    public void getAddDeletePost() {
        post1 = postDao.addPost(post1);

        Post test = postDao.getPost(post1.getPostID());
        assertEquals(test, post1);

        postDao.deletePost(post1.getPostID());
        test = postDao.getPost(post1.getPostID());
        assertNull(test);
    }

    @Test
    public void getAllPosts() {
        post1 = postDao.addPost(post1);
        post2 = postDao.addPost(post2);

        List<Post> postList = postDao.getAllPosts();

        assertEquals(2, postList.size());
    }

    @Test
    public void getPostsByPosterName() {
        post1 = postDao.addPost(post1);
        post2 = postDao.addPost(post2);

        List<Post> postList = postDao.getPostByPoster("Delcie");

        assertEquals(1, postList.size());
    }

    @Test
    public void updatePost() {
        post1 = postDao.addPost(post1);

        post1.setPosterName("Richard");

        postDao.updatePost(post1);

        Post test = postDao.getPost(post1.getPostID());

        assertEquals(test, post1);
    }
}