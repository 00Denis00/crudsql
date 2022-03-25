package commander;

import controller.PostController;
import model.Post;
import model.Tag;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.*;

public class PostCommander
{
    public static final String ANSI_BLUE = "\u001B[38;5;33m";
    public static final String ANSI_RESET = "\u001B[0m";
    Scanner scanner = new Scanner(System.in);
    PostController postController = new PostController();

    public void getById()
    {
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        String result = postController.getById(id);
        System.out.println(result);
        System.out.println();
    }

    public void deleteById()
    {
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        postController.deleteById(id);
        System.out.println("Post " + id + " was deleted");
        System.out.println();
    }

    public void getAll()
    {
        List<String> result = postController.getAll();
        for(int i = 0; i < result.size(); i++)
        {
            String res = result.get(i);
            System.out.println(res);
            System.out.println();
        }
    }

    public void save()
    {
        Post post = new Post();
        int x = 0;
        List<Tag> tags = new ArrayList<>();
        System.out.println(ANSI_BLUE + "Write \"new\" to create a new tag in list of tags" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Write \"continue\" to move to the next step" + ANSI_RESET);
        System.out.println();
        while(x == 0)
        {
            String str = scanner.nextLine();
            if(str.equals("new"))
            {
                Tag tag = new Tag();
                Scanner scr = new Scanner(System.in);
                System.out.println("Enter id: ");
                Integer id = scanner.nextInt();
                tag.setId(id);
                System.out.println("Enter name: ");
                String name = scr.nextLine();
                tag.setName(name);
                tags.add(tag);
                System.out.println("Tag was saved");
                System.out.println();
                System.out.println(ANSI_BLUE + "Write \"new\" to create a new tag in list of tags" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "Write \"continue\" to move to the next step" + ANSI_RESET);
                System.out.println();
            }
            else if(str.equals("continue"))
            {
                post.setTags(tags);
                x++;
                System.out.println();
            }
        }
        System.out.println("Enter first name: ");
        String fname = scanner.nextLine();
        post.setFirstName(fname);
        System.out.println("Enter last name: ");
        String lname = scanner.nextLine();
        post.setLastName(lname);
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        post.setId(id);
        postController.save(post);
        System.out.println("Post was saved");
        System.out.println();
    }

    public void update()
    {
        Post post = new Post();
        int x = 0;
        List<Tag> tags = new ArrayList<>();
        System.out.println(ANSI_BLUE + "Write \"new\" to create new tag" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Write \"continue\" to move to the next step" + ANSI_RESET);
        System.out.println();
        while(x == 0)
        {
            String str = scanner.nextLine();
            if(str.equals("new"))
            {
                Tag tag = new Tag();
                Scanner scr = new Scanner(System.in);
                System.out.println("Enter id: ");
                Integer id = scanner.nextInt();
                tag.setId(id);
                System.out.println("Enter name: ");
                String name = scr.nextLine();
                tag.setName(name);
                tags.add(tag);
                System.out.println("New tag was saved");
                System.out.println();
                System.out.println(ANSI_BLUE + "Write \"new\" to create a new tag in list of tags" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "Write \"continue\" to move to the next step" + ANSI_RESET);
                System.out.println();
            }
            else if(str.equals("continue"))
            {
                post.setTags(tags);
                x++;
                System.out.println();
            }
        }
        System.out.println("Enter new first name: ");
        String fname = scanner.nextLine();
        post.setFirstName(fname);
        System.out.println("Enter new last name: ");
        String lname = scanner.nextLine();
        post.setLastName(lname);
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        post.setId(id);
        postController.deleteById(id);
        postController.save(post);
        System.out.println("Post " + id + " was updated");
        System.out.println();
    }
}
