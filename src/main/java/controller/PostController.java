package controller;

import model.Post;
import repository.PostRepository;

import java.util.List;

public class PostController
{
    PostRepository postRepository = new PostRepository(){};
    public String getById(Integer id)
    {
        String result = postRepository.getById(id);
        return result;
    }
    public void deleteById(Integer id)
    {
        postRepository.deleteById(id);
    }
    public List<String> getAll()
    {
        List<String> result = postRepository.getAll();
        return result;
    }
    public void save(Post post)
    {
        postRepository.save(post);
    }
}
