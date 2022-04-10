package controller;

import model.Tag;
import model.TagStatus;
import repository.Jdbc.JdbcTagRepositoryImpl;

import java.util.List;

public class TagController
{
    JdbcTagRepositoryImpl tagRepository = new JdbcTagRepositoryImpl(){};
    public Tag getById(Integer id)
    {
        Tag tag = tagRepository.getById(id);
        return tag;
    }
    public TagStatus check(Integer id)
    {
        TagStatus T = tagRepository.check(id);
        return T;
    }
    public void deleteById(Integer id)
    {
        tagRepository.deleteById(id);
    }
    public List<Tag> getAll()
    {
        List<Tag> tags = tagRepository.getAll();
        return tags;
    }
    public void save(Tag tag)
    {
        tagRepository.save(tag);
    }
    public void update(Tag tag)
    {
        tagRepository.update(tag);
    }
}