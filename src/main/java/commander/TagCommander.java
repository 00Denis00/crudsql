package commander;

import controller.TagController;
import model.Tag;
import model.TagStatus;

import java.util.List;
import java.util.Scanner;

public class TagCommander
{
    Scanner scanner;
    TagController tagController = new TagController();

    public void getById()
    {
        System.out.println("Enter id: ");
        scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();

        Tag tag = new Tag();
        tag = tagController.getById(id);
        String name = tag.getName();

        System.out.println("Id: " + id + "   Name: " + name);
        System.out.println();
    }

    public void check()
    {
        System.out.println("Enter id: ");
        scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        System.out.println(tagController.check(id));
        System.out.println();
    }

    public void deleteById()
    {
        System.out.println("Enter id: ");
        scanner = new Scanner(System.in);
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

            System.out.println("Id: " + id + "   Name: " + name);
        }
        System.out.println();
    }

    public void save()
    {
        Tag tag = new Tag();
        System.out.println("Enter name: ");
        scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        tag.setName(name);

        tag = tagController.save(tag);
        int id = tag.getId();

        System.out.println("Id: " + id + "   Name: " + name);
        System.out.println();
    }

    public void update()
    {
        Tag tag = new Tag();
        System.out.println("Enter tag id: ");
        scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        tag.setId(id);
        System.out.println("Enter new name: ");
        scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        tag.setName(name);
        tagController.update(tag);
        System.out.println("Id: " + id + "   Name: " + name);
        System.out.println();
    }
}
