package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PostTest {

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
        Post post = new Post("Day 1: Intor");
        Post otherPost = new Post("How to pair successsfully");
        assertTrue(Post.getAll().contains(post));
        assertTrue(Post.getAll().contains(otherPost));
    }

    @Test
    public void NewPostObjectGetsCorrectlyCreated_true() {
        Post post = new Post("Day 1: Intro");
        assertEquals(true, post instanceof  Post);
    }

    @Test
    public void PostInstantiatesWithContent_true() {
        Post post = new Post("Day 1: Intro");
        assertEquals("Day 1: Intro", post.getContent());
    }
}