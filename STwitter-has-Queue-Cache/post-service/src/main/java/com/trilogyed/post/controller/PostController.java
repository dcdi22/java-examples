package com.trilogyed.post.controller;

import com.trilogyed.post.dao.PostDao;
import com.trilogyed.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    PostDao postDao;
/**/
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Post createPost(@RequestBody @Valid Post post) {
        return postDao.addPost(post);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Post getPost(@PathVariable int id) throws EntityNotFoundException {
        try {
            postDao.getPost(id).getPostID();
        } catch (Exception e) {
            throw new EntityNotFoundException("ID " + id + " does not exist");
        }
        return postDao.getPost(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Post> getAllPosts() {
        if (postDao.getAllPosts().size() > 0) {
            return postDao.getAllPosts();
        } else {
            throw new IllegalArgumentException("No posts found");
        }
    }

    @RequestMapping(value = "/poster/{posterName}",  method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Post> getPostsByPoster(@PathVariable String posterName) {
        return postDao.getPostByPoster(posterName);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updatePost(@RequestBody @Valid Post post, @PathVariable int id) {
//        try {
//            postDao.getPost(id).getPostID();
//        } catch (Exception e) {
//            throw new IllegalArgumentException("ID " + id + " does not exist");
//        }
        post.setPostID(id);
        postDao.updatePost(post);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePost(@PathVariable int id) {
//        try {
//            postDao.getPost(id).getPostID();
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Cannot Delete the ID " + id + " does not exist");
//        }

        postDao.deletePost(id);

    }
}
