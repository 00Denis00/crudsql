import commander.PostCommander;
import commander.TagCommander;
import commander.WriterCommander;
import utils.JdbcUtils;

import java.util.Scanner;

public class main
{
    public static void main(String[] args)
    {
        MainMenu menu = new MainMenu();
        menu.view();
    }
}
