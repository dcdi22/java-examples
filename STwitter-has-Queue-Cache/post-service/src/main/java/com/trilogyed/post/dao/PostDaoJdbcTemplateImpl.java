package com.trilogyed.post.dao;

import com.trilogyed.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("postDao")
public class PostDaoJdbcTemplateImpl implements PostDao {

    // ========== PREPARED STATEMENTS ==========

    public static final String INSERT_POST =
            "insert into post (post_date, poster_name, post) values (?,?,?)";
    public static final String SELECT_POST =
            "select * from post where post_id = ?";
    public static final String SELECT_ALL_POSTS =
            "select * from post";
    public static final String SELECT_POSTS_BY_POSTER_NAME = "" +
            "select * from post where poster_name = ?";
    public static final String UPDATE_POST =
            "update post set post_date = ?, poster_name = ?, post = ? where post_id = ?";
    public static final String DELETE_POST =
            "delete from post where post_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PostDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ==========================================

    @Override
    @Transactional
    public Post addPost(Post post) {
        jdbcTemplate.update(INSERT_POST,
                post.getPostDate(),
                post.getPosterName(),
                post.getPost());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        post.setPostID(id);
        return post;
    }

    @Override
    public Post getPost(int postID) {
        try {
            return jdbcTemplate.queryForObject(SELECT_POST, this::mapRowToPost, postID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Post> getAllPosts() {
        return jdbcTemplate.query(SELECT_ALL_POSTS, this::mapRowToPost);
    }

    @Override
    public List<Post> getPostByPoster(String posterName) {
        return jdbcTemplate.query(SELECT_POSTS_BY_POSTER_NAME, this::mapRowToPost, posterName);
    }

    @Override
    public void updatePost(Post post) {
        jdbcTemplate.update(UPDATE_POST,
                post.getPostDate(),
                post.getPosterName(),
                post.getPost(),
                post.getPostID());
    }

    @Override
    public void deletePost(int postID) {
        jdbcTemplate.update(DELETE_POST, postID);
    }

    private Post mapRowToPost(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();
        post.setPostID(rs.getInt("post_id"));
        post.setPostDate(rs.getDate("post_date").toLocalDate());
        post.setPosterName(rs.getString("poster_name"));
        post.setPost(rs.getString("post"));
        return post;
    }

}
