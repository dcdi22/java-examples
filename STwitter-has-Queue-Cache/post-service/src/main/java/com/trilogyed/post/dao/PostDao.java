package com.trilogyed.post.dao;

import com.trilogyed.post.model.Post;

import java.util.List;

public interface PostDao {

    Post addPost(Post post);

    Post getPost(int postID);

    List<Post> getAllPosts();

    List<Post> getPostByPoster(String posterName);

    void updatePost(Post post);

    void deletePost(int postID);

}
