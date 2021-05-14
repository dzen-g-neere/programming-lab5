package commands;

import exceptions.IncorrectScriptException;
import exceptions.WrongArgumentException;
import utility.CollectionManager;

/**
 * This is command 'print_descending'. Prints all elements in the decreasing order.
 */
public class PrintDescendingCommand extends  AbstractCommand implements Command{
    CollectionManager collectionManager;

    public PrintDescendingCommand(CollectionManager collectionManager) {
        super("print_descending", " - вывести элементы коллекции в порядке убывания");
        this.collectionManager = collectionManager;
    }
    /**
     * Execute of 'print_descending' command.
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) {
                throw new WrongArgumentException();
            }
            collectionManager.printDescending();
        } catch (WrongArgumentException e) {
            System.out.println("Некорректный аргумент. Используйте: '" + getName() + "'");
        } catch (Exception e) {
            System.out.println("Ошибка. Повторите ввод.");
        }
    }
}
