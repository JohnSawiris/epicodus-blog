package models;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PostTest {

    public Post setupNewPost() {
        return new Post("Day 1: Intro");
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Post.clearAllPosts();
    }

    @Test
    public void AllPostsAreCorrectlyReturned_ture() {
        Post post = new Post("Day 1: intro");
        Post otherPost = new Post("How to pair successfully");
        assertEquals(2, Post.getAll().size());
    }

    @Test
    public void AllPostsContainsAllPosts_ture() {
        Post post = new Post("Day 1: Intro");
        Post otherPost = new Post("How to pair successsfully");
        assertTrue(Post.getAll().contains(post));
        assertTrue(Post.getAll().contains(otherPost));
    }

    @Test
    public void NewPostObjectGetsCorrectlyCreated_true() {
        Post post = setupNewPost();
        assertEquals(true, post instanceof  Post);
    }

    @Test
    public void PostInstantiatesWithContent_true() {
        Post post = setupNewPost();
        assertEquals("Day 1: Intro", post.getContent());
    }

    @Test
    public void getPublished_isFalseAfterInstantiation_false() {
        Post post = setupNewPost();
        assertEquals(false, post.getPublished());
    }

    @Test
    public void getCreateAt_instantiatesWithCurrentTime_today() {
        Post post = setupNewPost();
        assertEquals(LocalDateTime.now().getDayOfWeek(), post.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void getId_postsInstantiateWithAnId_1() {
        Post.clearAllPosts();
        Post post = setupNewPost();
        assertEquals(1, post.getId());
    }

    @Test
    public void findReturnsCorrectPost() {
        Post post = setupNewPost();
        assertEquals(1, Post.findById(post.getId()).getId());
    }

    @Test
    public void findReturnsCorrectPostWhereMoreThanOnePostExists() {
        Post post = setupNewPost();
        Post secPost = new Post("Second Post");
        assertEquals(2, Post.findById(secPost.getId()).getId());
    }
}