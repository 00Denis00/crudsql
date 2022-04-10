package controller;

import model.Post;
import repository.Jdbc.JdbcPostRepositoryImpl;

import java.util.List;

public class PostController
{
    JdbcPostRepositoryImpl postRepository = new JdbcPostRepositoryImpl(){};
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
    public void update(Post post)
    {
        postRepository.update(post);
    }
}
