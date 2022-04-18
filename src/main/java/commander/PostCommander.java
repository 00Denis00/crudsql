package commander;

import controller.PostController;
import model.Post;
import model.Tag;
import model.Writer;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.*;

public class PostCommander
{
    Scanner scanner = new Scanner(System.in);
    PostController postController = new PostController();

    public void getById()
    {
        System.out.println("Enter id: ");
        scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        Post post = postController.getById(id);

        String content = post.getContent();
        List<Tag> tags = post.getTags();
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for(int i = 0; i < tags.size(); i++)
        {
            Tag tag = tags.get(i);
            builder.append("(");
            builder.append("Id: ");
            builder.append(tag.getId());
            builder.append(" ; ");
            builder.append("Name: ");
            builder.append(tag.getName());
            builder.append(")");
        }
        builder.append("]");
        String result = builder.toString();
        System.out.println("Content: " + content);
        System.out.println("Tags: " + result);
    }

    public void deleteById()
    {
        System.out.println("Enter id: ");
        scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        postController.deleteById(id);
        System.out.println("Post " + id + " was deleted");
        System.out.println();
    }

    public void update()
    {
        Post post = new Post();

        System.out.println("Enter post id: ");
        scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        post.setId(id);
        System.out.println("Enter new content: ");
        scanner = new Scanner(System.in);
        String content = scanner.nextLine();
        post.setContent(content);

        postController.update(post);
        System.out.println("Id: " + id + "   Content: " + content);
        System.out.println();
    }

    public void getAll()
    {
        List<Post> posts = postController.getAll();
        for(int i = 0; i < posts.size(); i++)
        {
            Post post = posts.get(i);
            int id = post.getId();
            String content = post.getContent();
            List<Tag> tags = post.getTags();
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            for(int j = 0; j < tags.size(); j++)
            {
                Tag tag = tags.get(j);
                builder.append("(");
                builder.append("Id: ");
                builder.append(tag.getId());
                builder.append(" ; ");
                builder.append("Name: ");
                builder.append(tag.getName());
                builder.append(")");
            }
            builder.append("]");
            String build = builder.toString();
            System.out.println("Id: " + id);
            System.out.println("Content: " + content);
            System.out.println("Tags: " + build);
            System.out.println();
        }
    }

    public void save()
    {
        Post post = new Post();

        System.out.println("Enter content: ");
        scanner = new Scanner(System.in);
        String content = scanner.nextLine();
        post.setContent(content);

        System.out.println("Enter writer id: ");
        scanner = new Scanner(System.in);
        int writerId = scanner.nextInt();
        Writer writer = new Writer();
        writer.setId(writerId);
        post.setWriter(writer);

        System.out.println();

        List<Tag> tags = new ArrayList<>();
        String command = "continue";
        while(command.equals("continue"))
        {
            Tag tag = new Tag();
            System.out.println("Enter tag id: ");
            scanner = new Scanner(System.in);
            int tagId = scanner.nextInt();
            tag.setId(tagId);
            tags.add(tag);

            System.out.println("To continue write \"continue\"");
            System.out.println("To stop write \"stop\"");
            scanner = new Scanner(System.in);
            command = scanner.nextLine();
        }
        post.setTags(tags);

        post = postController.save(post);
        int id = post.getId();

        System.out.println("Id: " + id + "   Content: " + content);
        System.out.println();
    }
}
