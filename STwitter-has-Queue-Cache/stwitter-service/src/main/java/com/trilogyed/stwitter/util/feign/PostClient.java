package com.trilogyed.stwitter.util.feign;

import com.trilogyed.stwitter.models.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "post-service")
public interface PostClient {

    @PostMapping(value = "/posts")
    Post createPost(@RequestBody Post post);

    @GetMapping(value = "/posts/{id}")
    Post getPost(@PathVariable int id);

    @GetMapping(value = "/posts")
    List<Post> getAllPosts();

    /*
    @RequestMapping(value = "/poster/{posterName}",  method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Post> getPostsByPoster(@PathVariable String posterName)
     */
    @GetMapping(value = "/posts/poster/{posterName}")
    List<Post> getPostsByPoster(@PathVariable String posterName);

    @PutMapping(value = "/posts/{id}")
    void updatePost(@RequestBody Post post, @PathVariable int id);

    @DeleteMapping(value = "/posts/{id}")
    void deletePost(@PathVariable int id);

}
