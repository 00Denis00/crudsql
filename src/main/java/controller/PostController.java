package controller;

import model.Post;
import repository.PostRepository;

import java.util.List;

public class PostController
{
    PostRepository postRepository = new PostRepository(){};
    public Post getById(Integer id)
    {
        Post post = postRepository.getById(id);
        return post;
    }
    public void deleteById(Integer id)
    {
        postRepository.deleteById(id);
    }
    public List<Post> getAll()
    {
        List<Post> result = postRepository.getAll();
        return result;
    }
    public void save(Post post)
    {
        postRepository.save(post);
    }
}
