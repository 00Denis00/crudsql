package controller;

import model.Tag;
import model.TagStatus;
import repository.Jdbc.JdbcTagRepositoryImpl;
import repository.TagRepository;

import java.util.List;

public class TagController
{
    TagRepository tagRepository = new JdbcTagRepositoryImpl(){};

    public Tag getById(Integer id)
    {
        return tagRepository.getById(id);
    }
    public TagStatus check(Integer id)
    {
        return tagRepository.check(id);
    }
    public void deleteById(Integer id)
    {
        tagRepository.deleteById(id);
    }
    public List<Tag> getAll()
    {
        return tagRepository.getAll();
    }
    public Tag save(Tag tag)
    {
        return tagRepository.save(tag);
    }
    public Tag update(Tag tag)
    {
        return tagRepository.update(tag);
    }
}