package commander;

import controller.WriterController;

import model.Post;
import model.Tag;
import model.Writer;

import java.util.List;
import java.util.*;

public class WriterCommander
{
    Scanner StrScanner = new Scanner(System.in);
    Scanner IntScanner = new Scanner(System.in);
    WriterController writerController = new WriterController();

    public void getById()
    {
        System.out.println("Enter id: ");
        Integer id = IntScanner.nextInt();
        Writer writer = writerController.getById(id);

        String name = writer.getName();
        System.out.print("Name: " + name);
        System.out.print("\nPosts: ");

        List<Post> posts = writer.getPosts();
        for(int i = 0; i < posts.size(); i++)
        {
            Post post = posts.get(i);
            int ide = post.getId();
            String content = post.getContent();
            System.out.print("\n[" + ide + ". " + content + "   Tags: ");

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

    public void deleteById()
    {
        System.out.println("Enter id: ");
        Integer id = IntScanner.nextInt();
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
                String content = post.getContent();
                System.out.print("\n[" + ide + ". " + content + "   Tags: ");

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
        String name = StrScanner.nextLine();
        writer.setName(name);
        System.out.println("Enter id: ");
        Integer id = IntScanner.nextInt();
        writer.setId(id);
        writerController.update(writer);
        System.out.println();
    }

    public void save()
    {
        Writer writer = new Writer();
        System.out.println("Enter name: ");
        String name = StrScanner.nextLine();
        writer.setName(name);
        writerController.save(writer);
        System.out.println();
    }

}
