package commander;

import controller.TagController;
import model.Tag;
import model.TagStatus;

import java.util.List;
import java.util.Scanner;

public class TagCommander
{
    Scanner StrScanner = new Scanner(System.in);
    Scanner IntScanner = new Scanner(System.in);
    TagController tagController = new TagController();

    public void getById()
    {
        System.out.println("Enter id: ");
        Integer id = IntScanner.nextInt();
        Tag tag = tagController.getById(id);
        String name = tag.getName();
        System.out.println("Tag " + id + ": " + name);
        System.out.println();
    }
    public void check()
    {
        System.out.println("Enter id: ");
        Integer id = IntScanner.nextInt();
        TagStatus T = tagController.check(id);
        System.out.println(T);
        System.out.println();
    }
    public void deleteById()
    {
        System.out.println("Enter id: ");
        Integer id = IntScanner.nextInt();
        tagController.deleteById(id);
        System.out.println("Tag " + id + " was deleted");
        System.out.println();
    }
    public void getAll()
    {
        List<Tag> tags = tagController.getAll();
        for(int i = 0; i < tags.size(); i++)
        {
            Tag tag = tags.get(i);
            int id = tag.getId();
            String name = tag.getName();
            String result = "Id: " + id + "   Name: " + name;
            System.out.println(result);
        }
    }
    public void save()
    {
        Tag tag = new Tag();
        System.out.println("Enter name: ");
        String name = StrScanner.nextLine();
        tag.setName(name);
        System.out.println("Enter post id: ");
        Integer postId = IntScanner.nextInt();
        tag.setPostId(postId);
        tagController.save(tag);
        System.out.println();
    }
    public void update()
    {
        Tag tag = new Tag();
        System.out.println("Enter new name: ");
        String name = StrScanner.nextLine();
        tag.setName(name);
        System.out.println("Enter tag id: ");
        Integer id = IntScanner.nextInt();
        tag.setId(id);
        tagController.update(tag);
        System.out.println();
    }
}
