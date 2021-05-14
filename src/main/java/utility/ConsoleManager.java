package utility;

import exceptions.IncorrectScriptException;
import exceptions.NoFileAccessException;
import exceptions.ScriptRecursionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * Operates command input.
 */
public class ConsoleManager {
    static int recurs = 0;
    static int recurs_max = 2;
    CommandManager commandManager;
    LabWorkAsker labWorkAsker;
    FileManager fileManager;
    private Scanner scanner;
    private HashSet<String> scriptsInProcess = new HashSet<String>();

    public ConsoleManager(Scanner scanner, CommandManager commandManager, LabWorkAsker labWorkAsker) {
        this.scanner = scanner;
        this.commandManager = commandManager;
        this.fileManager = fileManager;
        this.labWorkAsker = labWorkAsker;
    }

    /**
     * Mode for work with commands from user input.
     */
    public void interectiveMode() {
        String[] command;
        try {
            while (true) {
                System.out.println("Введите команду: ");
                command = (scanner.nextLine().trim() + " ").split(" ", 2);
                command[1] = command[1].trim();
                runCommand(command);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Введён конец файла! Завершение программы.");
            System.exit(0);
        } catch (IncorrectScriptException e) {
            System.out.println("Ошибка при считываниии команды пользователя!");
        }

    }

    /**
     * Mode for work with commands from a script.
     */
    public void scriptMode(String argument) {
        String[] command;
        boolean isReadable = true;
        Scanner temp = labWorkAsker.getUserScanner();
//
        try {
            File file = new File(argument);
            if (file.exists() && !file.canRead()) {
                isReadable = false;
                throw new NoFileAccessException();
            }
        } catch (NoFileAccessException e) {
            System.out.println("Расширьте права файла на чтение и запись, и попробуйте снова.");
        }
        if (isReadable) {
            try (Scanner scriptScanner = new Scanner(new File(argument))) {
                if (!scriptScanner.hasNext()) throw new NoSuchElementException();
                labWorkAsker.setUserScanner(scriptScanner);
                labWorkAsker.setScriptMode(true);
                do {
                    command = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    command[1] = command[1].trim();
                    while (scriptScanner.hasNextLine() && command[0].isEmpty()) {
                        command = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                        command[1] = command[1].trim();
                    }
                    System.out.println(String.join(" ", command));
                    try {
                        if (scriptsInProcess.contains(command[1]) && command[0].equals("execute_script")) {
                            recurs++;
                            //System.out.println(recurs);
                        } else if (command[0].equals("execute_script")){
                            scriptsInProcess.add(command[1]);
                            recurs++;
                            //System.out.println(recurs);
                        }
                        if (command[0].equals("execute_script")) {
                            if (scriptsInProcess.contains(command[1]) && recurs >= recurs_max) {
                                System.out.println("В вашем скрипте присутствует бесконечная рекурсия. Продолжить выполнение? yes/no");
                                try {
                                    Scanner scanner = new Scanner(System.in);
                                    if (scanner.hasNextLine()) {
                                        String line = scanner.nextLine();
                                        if (line.equals("yes"))
                                            recurs = -1000000;
                                        else continue;
                                    }
                                } catch (NoSuchElementException e) {
                                    System.exit(0);
                                }
                            }
                        }
                        runCommand(command);
                    } catch (Error e) {
                        System.out.println("В вашем скрипте присутствует бесконечная рекурсия. Скрипт продолжит выполняться со следующей команды.");
                    }
                } while (scriptScanner.hasNextLine());
            } catch (FileNotFoundException exception) {
                System.out.println("Файл со скриптом не найден!");
            } catch (NoSuchElementException exception) {
                System.out.println("Файл со скриптом пуст!");
            } catch (IncorrectScriptException e) {
                System.out.println("Ошибка в скрипте. Скрипт закрыт.");
            } catch (Exception e) {
                System.out.println("Ошибка. Перезапустите программу.");
            }
        }
        recurs = 0;
        labWorkAsker.setUserScanner(temp);
        labWorkAsker.setScriptMode(false);
    }

    /**
     * Selects and start command execution.
     */
    public void runCommand(String[] userCommand) throws IncorrectScriptException {
        try {
            switch (userCommand[0]) {
                case "insert":
                    commandManager.insertLWToCollection(userCommand[1]);
                    break;
                case "update":
                    commandManager.updateID(userCommand[1]);
                    break;
                case "show":
                    commandManager.showCollection(userCommand[1]);
                    break;
                case "help":
                    commandManager.help(userCommand[1]);
                    break;
                case "info":
                    commandManager.info(userCommand[1]);
                    break;
                case "remove_key":
                    commandManager.remove_key(userCommand[1]);
                    break;
                case "clear":
                    commandManager.clear(userCommand[1]);
                    break;
                case "save":
                    commandManager.save(userCommand[1]);
                    break;
                case "execute_script":
                    scriptMode(userCommand[1]);
                    break;
                case "exit":
                    commandManager.exit(userCommand[1]);
                    break;
                case "replace_if_greater":
                    commandManager.replace_if_greater(userCommand[1]);
                    break;
                case "replace_if_lowe":
                    commandManager.replace_if_lowe(userCommand[1]);
                    break;
                case "remove_greater_key":
                    commandManager.remove_greater_key(userCommand[1]);
                    break;
                case "group_counting_by_creation_date":
                    commandManager.group_counting_by_creation_date(userCommand[1]);
                    break;
                case "filter_greater_than_average_point":
                    commandManager.filter_greater_than_average_point(userCommand[1]);
                    break;
                case "print_descending":
                    commandManager.print_descending(userCommand[1]);
                    break;
                default:
                    System.out.println("Не является внутренней командой. Повтороте ввод или напишите help для получения актуального списка команд.");
            }
        } catch (ExceptionInInitializerError e) {
            System.out.println("Непредвиденная ошибка");
            e.printStackTrace();
        } catch (IncorrectScriptException e) {
            throw new IncorrectScriptException();
        }
    }
}
