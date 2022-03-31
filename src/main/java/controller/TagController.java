package controller;

import model.Tag;
import model.TagStatus;
import repository.TagRepository;

import java.util.List;
import java.util.Scanner;

public class TagController
{
    TagRepository tagRepository = new TagRepository(){};
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
}