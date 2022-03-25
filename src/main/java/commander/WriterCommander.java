package commander;

import controller.WriterController;

import model.Post;
import model.Tag;
import model.Writer;

import java.sql.SQLOutput;
import java.util.List;
import java.util.*;

public class WriterCommander
{
    public static final String ANSI_BLUE = "\u001B[38;5;33m";
    public static final String ANSI_RESET = "\u001B[0m";
    Scanner scanner = new Scanner(System.in);
    WriterController writerController = new WriterController();

    public void getById()
    {
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        Writer writer = writerController.getById(id);

        String name = writer.getName();
        System.out.print("Name: " + name);
        System.out.print("\nPosts: ");

        List<Post> posts = writer.getPosts();
        for(int i = 0; i < posts.size(); i++)
        {
            Post post = posts.get(i);
            int ide = post.getId();
            String fname = post.getFirstName();
            String lname = post.getLastName();
            System.out.print("\n[" + ide + ". " + fname + " " + lname + "   Tags: ");

            List<Tag> tags = post.getTags();
            for(int j = 0; j < tags.size(); j++)
            {
                Tag tag = tags.get(j);
                int idi = tag.getId();
                String nm = tag.getName();
                System.out.print("(Id: " + idi + " ; Name: " + nm + ")");
            }
            System.out.print("]");
        }
    }

    public void deleteById()
    {
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        writerController.deleteById(id);
        System.out.println("Writer " + id + " was deleted");
        System.out.println();
    }

    public void getAll()
    {
        List<Writer> writers = writerController.getAll();
        for(int z = 0; z < writers.size(); z++)
        {
            Writer writer = writers.get(z);

            int id = writer.getId();
            System.out.print("\nId: " + id);
            String name = writer.getName();
            System.out.print("\nName: " + name);
            System.out.print("\nPosts: ");

            List<Post> posts = writer.getPosts();
            for(int i = 0; i < posts.size(); i++)
            {
                Post post = posts.get(i);
                int ide = post.getId();
                String fname = post.getFirstName();
                String lname = post.getLastName();
                System.out.print("\n[" + ide + ". " + fname + " " + lname + "   Tags: ");

                List<Tag> tags = post.getTags();
                for(int j = 0; j < tags.size(); j++)
                {
                    Tag tag = tags.get(j);
                    int idi = tag.getId();
                    String nm = tag.getName();
                    System.out.print("(Id: " + idi + " ; Name: " + nm + ")");
                }
                System.out.print("]");
            }
            System.out.println();
        }
    }

    public void update()
    {
        Writer writer = new Writer();
        System.out.println("Enter new name: ");
        String name = scanner.nextLine();
        writer.setName(name);
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        writer.setId(id);
        int z = 0;
        List<Post> posts = new ArrayList<>();
        System.out.println(ANSI_BLUE + "Write \"post\" to create a new post in list of posts" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Write \"save\" to save list of posts" + ANSI_RESET);
        System.out.println();
        String result = "";
        while(z == 0)
        {
            String com = scanner.nextLine();
            if(com.equals("post"))
            {
                Post post = new Post();
                System.out.println("Enter first name: ");
                String fname = scanner.nextLine();
                post.setFirstName(fname);
                System.out.println("Enter last name: ");
                String lname = scanner.nextLine();
                post.setLastName(lname);
                System.out.println("Enter id: ");
                Integer ide = scanner.nextInt();
                post.setId(ide);
                int x = 0;
                List<Tag> tags = new ArrayList<>();
                System.out.println(ANSI_BLUE + "Write \"new\" to create a new tag in list of tags" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "Write \"continue\" to move to the next step" + ANSI_RESET);
                System.out.println();
                String res = "";
                while(x == 0)
                {
                    String str = scanner.nextLine();
                    if(str.equals("new"))
                    {
                        Tag tag = new Tag();
                        Scanner scr = new Scanner(System.in);
                        System.out.println("Enter id: ");
                        Integer ind = scanner.nextInt();
                        tag.setId(ind);
                        System.out.println("Enter name: ");
                        String noname = scr.nextLine();
                        tag.setName(noname);
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
                posts.add(post);
                System.out.println("Post was saved");
                System.out.println();
                System.out.println(ANSI_BLUE + "Write \"post\" to create a new post in list of posts" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "Write \"save\" to save list of posts" + ANSI_RESET);
                System.out.println();
            }
            else if(com.equals("save"))
            {
                writer.setPosts(posts);
                z++;
                System.out.println();
                System.out.println("List of posts was saved");
                System.out.println();
            }
        }
        writerController.deleteById(id);
        writerController.save(writer);
        System.out.println("Writer was updated");
        System.out.println();
    }

    public void save()
    {
        Writer writer = new Writer();
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        writer.setName(name);
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        writer.setId(id);
        int z = 0;
        List<Post> posts = new ArrayList<>();
        System.out.println(ANSI_BLUE + "Write \"post\" to create a new post in list of posts" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Write \"save\" to save list of posts" + ANSI_RESET);
        System.out.println();
        String result = "";
        while(z == 0)
        {
            String com = scanner.nextLine();
            if(com.equals("post"))
            {
                Post post = new Post();
                System.out.println("Enter first name: ");
                String fname = scanner.nextLine();
                post.setFirstName(fname);
                System.out.println("Enter last name: ");
                String lname = scanner.nextLine();
                post.setLastName(lname);
                System.out.println("Enter id: ");
                Integer ide = scanner.nextInt();
                post.setId(ide);
                int x = 0;
                List<Tag> tags = new ArrayList<>();
                System.out.println(ANSI_BLUE + "Write \"new\" to create a new tag in list of tags" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "Write \"continue\" to move to the next step" + ANSI_RESET);
                System.out.println();
                String res = "";
                while(x == 0)
                {
                    String str = scanner.nextLine();
                    if(str.equals("new"))
                    {
                        Tag tag = new Tag();
                        Scanner scr = new Scanner(System.in);
                        System.out.println("Enter id: ");
                        Integer ind = scanner.nextInt();
                        tag.setId(ind);
                        System.out.println("Enter name: ");
                        String noname = scr.nextLine();
                        tag.setName(noname);
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
                posts.add(post);
                System.out.println("Post was saved");
                System.out.println();
                System.out.println(ANSI_BLUE + "Write \"post\" to create a new post in list of posts" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "Write \"save\" to save list of posts" + ANSI_RESET);
                System.out.println();
            }
            else if(com.equals("save"))
            {
                writer.setPosts(posts);
                z++;
                System.out.println();
                System.out.println("List of posts was saved");
                System.out.println();
            }
        }
        writerController.save(writer);
        System.out.println("Writer was saved");
        System.out.println();
    }
}
