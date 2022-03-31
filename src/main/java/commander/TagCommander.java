package commander;

import controller.TagController;
import model.Tag;
import model.TagStatus;

import java.util.List;
import java.util.Scanner;

public class TagCommander
{
    Scanner scanner = new Scanner(System.in);
    TagController tagController = new TagController();

    public void getById()
    {
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        Tag tag = tagController.getById(id);
        String name = tag.getName();
        System.out.println("Tag " + id + ": " + name);
        System.out.println();
    }
    public void check()
    {
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        TagStatus T = tagController.check(id);
        System.out.println(T);
        System.out.println();
    }
    public void deleteById()
    {
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
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
        String name = scanner.nextLine();
        tag.setName(name);
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        tag.setId(id);
        tagController.save(tag);
        System.out.println("Tag was saved");
        System.out.println();
    }
    public void update()
    {
        Tag tag = new Tag();
        System.out.println("Enter new name: ");
        String name = scanner.nextLine();
        tag.setName(name);
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        tag.setId(id);
        tagController.deleteById(id);
        tagController.save(tag);
        System.out.println("Tag " + id + " was updated");
        System.out.println();
    }
}
