package controller;

import model.Post;
import repository.Jdbc.JdbcPostRepositoryImpl;
import repository.PostRepository;

import java.util.List;

public class PostController
{
    PostRepository postRepository = new JdbcPostRepositoryImpl(){};

    public Post getById(Integer id)
    {
        return postRepository.getById(id);
    }
    public void deleteById(Integer id)
    {
        postRepository.deleteById(id);
    }
    public List<Post> getAll()
    {
        return postRepository.getAll();
    }
    public Post save(Post post)
    {
        return postRepository.save(post);
    }
    public Post update(Post post)
    {
        return postRepository.update(post);
    }
}
