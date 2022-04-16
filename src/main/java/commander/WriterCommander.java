package commander;

import controller.WriterController;

import model.Post;
import model.Tag;
import model.Writer;

import java.util.List;
import java.util.*;

public class WriterCommander
{
    Scanner scanner = new Scanner(System.in);
    WriterController writerController = new WriterController();

    public void getById()
    {
        System.out.println("Enter id: ");
        scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        Writer writer = writerController.getById(id);

        String name = writer.getName();
        System.out.print("Name: " + name);
        System.out.print("\nPosts: ");

        List<Post> posts = writer.getPosts();
        for(int i = 0; i < posts.size(); i++)
        {
            Post post = posts.get(i);
            int postId = post.getId();
            String postContent = post.getContent();
            System.out.print("\n[" + postId + ". " + postContent + "   Tags: ");

            List<Tag> tags = post.getTags();
            for(int j = 0; j < tags.size(); j++)
            {
                Tag tag = tags.get(j);
                int tagId = tag.getId();
                String tagName = tag.getName();
                System.out.print("(Id: " + tagId + " ; Name: " + tagName + ")");
            }
            System.out.print("]");
        }
        System.out.println();
    }

    public void deleteById()
    {
        System.out.println("Enter id: ");
        scanner = new Scanner(System.in);
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
                int postId = post.getId();
                String content = post.getContent();
                System.out.print("\n[" + postId + ". " + content + "   Tags: ");

                List<Tag> tags = post.getTags();
                for(int j = 0; j < tags.size(); j++)
                {
                    Tag tag = tags.get(j);
                    int tagId = tag.getId();
                    String tagName = tag.getName();
                    System.out.print("(Id: " + tagId + " ; Name: " + tagName + ")");
                }
                System.out.print("]");
            }
            System.out.println();
        }
    }

    public void update()
    {
        Writer writer = new Writer();

        System.out.println("Enter writer id: ");
        scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        writer.setId(id);

        System.out.println("Enter new name: ");
        scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        writer.setName(name);

        writerController.update(writer);

        System.out.println("Id: " + id + "   Name: " + name);
        System.out.println();
    }

    public void save()
    {
        Writer writer = new Writer();
        System.out.println("Enter name: ");
        scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        writer.setName(name);
        writer = writerController.save(writer);
        int id = writer.getId();

        System.out.println("Id: " + id + "   Name: " + name);
        System.out.println();
    }
}
