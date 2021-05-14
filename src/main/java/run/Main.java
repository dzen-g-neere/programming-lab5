package run;

import commands.*;
import utility.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Main application class. Creates all instances and runs the program.
 *
 * @author Дмитрий Залевский P3112
 */
public class Main {

    public static void main(String[] args) {
        String path = System.getenv("envVariable");
        if (path == null){
            path = "backup";
            System.out.println("Переменная окружения не найдена, записано значение по умолчанию - backup");
        }
//        try {
//            PrintWriter printWriter = new PrintWriter("try_me.txt");
//            printWriter.println("fucher");
//            printWriter.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        String heh = (" s;g;h;j; ").replaceAll(";","\n");
//        Scanner scanner = new Scanner(heh);
//        System.out.println(scanner.nextLine());
//        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date;
//        try {
//            date = df.parse("11/04/2021 22:36:27");
//            System.out.println(date);
//        } catch (ParseException e) {
//            System.out.println("loh");
//        }
        Scanner userScanner = new Scanner(System.in);
        final String envVariable = path;


        LabWorkAsker labWorkAsker = new LabWorkAsker(userScanner);
        FileManager fileManager = new FileManager(envVariable, labWorkAsker);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        collectionManager.loadCollection();
        CommandManager commandManager = new CommandManager(
                new InsertCommand(collectionManager, labWorkAsker),
                new ShowCommand(collectionManager),
                new ExitCommand(),
                new UpdateIDCommand(collectionManager, labWorkAsker),
                new InfoCommand(collectionManager),
                new ClearCommand(collectionManager),
                new ExecuteScriptCommand(),
                new FilterGreaterThanAveragePointCommand(collectionManager),
                new GroupCountingByCreationDateCommand(collectionManager),
                new HelpCommand(),
                new PrintDescendingCommand(collectionManager),
                new RemoveGreaterKey(collectionManager),
                new RemoveKeyCommand(collectionManager),
                new ReplaceIfGreaterCommand(collectionManager, labWorkAsker),
                new ReplaceIfLowerCommand(collectionManager, labWorkAsker),
                new SaveCommand(collectionManager)
        );
//        InsertCommand insertCommand,
//        ShowCommand showCommand,
//        ExitCommand exitCommand,
//        UpdateIDCommand updateIDCommand,
//        InfoCommand infoCommand,
//        ClearCommand clearCommand,
//        ExecuteScriptCommand executeScriptCommand,
//        FilterGreaterThanAveragePointCommand filterGreaterThanAveragePointCommand,
//        GroupCountingByCreationDateCommand groupCountingByCreationDateCommand,
//        HelpCommand helpCommand,
//        PrintDescendingCommand printDescendingCommand,
//        RemoveGreaterKey removeGreaterKey,
//        RemoveKeyCommand removeKeyCommand,
//        ReplaceIfGreaterCommand replaceIfGreaterCommand,
//        ReplaceIfLowerCommand replaceIfLowerCommand,
//        SaveCommand saveCommand
        ConsoleManager consoleManager = new ConsoleManager(userScanner, commandManager, labWorkAsker);
        consoleManager.interectiveMode();
    }

}
