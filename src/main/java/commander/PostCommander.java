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
    Scanner StrScanner = new Scanner(System.in);
    Scanner IntScanner = new Scanner(System.in);
    PostController postController = new PostController();

    public void getById()
    {
        System.out.println("Enter id: ");
        Integer id = IntScanner.nextInt();
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
        Integer id = IntScanner.nextInt();
        postController.deleteById(id);
        System.out.println("Post " + id + " was deleted");
        System.out.println();
    }

    public void update()
    {
        Post post = new Post();

        System.out.println("Enter new content: ");
        String content = StrScanner.nextLine();
        post.setContent(content);

        System.out.println("Enter id: ");
        Integer id = IntScanner.nextInt();
        post.setId(id);

        postController.update(post);
        System.out.println();
    }

    public void getAll()
    {
        List<Post> result = postController.getAll();
        for(int i = 0; i < result.size(); i++)
        {
            Post post = result.get(i);
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
        String content = StrScanner.nextLine();
        post.setContent(content);
        System.out.println("Enter writer id: ");
        Integer writerId = IntScanner.nextInt();
        post.setWriterId(writerId);
        postController.save(post);
        System.out.println();
    }
}
