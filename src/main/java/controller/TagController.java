package controller;

import model.Tag;
import model.TagStatus;
import repository.TagRepository;

import java.util.List;
import java.util.Scanner;

public class TagController
{
    TagRepository tagRepository = new TagRepository(){};
    public String getById(Integer id)
    {
        String str = tagRepository.getById(id);
        return str;
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
    public List<String> getAll()
    {
        return tagRepository.getAll();
    }
    public void save(Tag tag)
    {
        tagRepository.save(tag);
    }
}