package commands;

import exceptions.IncorrectScriptException;
import exceptions.WrongArgumentException;
import utility.CollectionManager;
import utility.LabWorkAsker;

/**
 * This is command 'group_counting_by_creation_date'. Groups and print elements of collection by creationDate.
 */
public class GroupCountingByCreationDateCommand extends AbstractCommand implements Command{
    CollectionManager collectionManager;

    public GroupCountingByCreationDateCommand(CollectionManager collectionManager) {
        super("group_counting_by_creation_date", " - сгруппировать элементы коллекции по значению поля creationDate, вывести количество элементов в каждой группе)");
        this.collectionManager = collectionManager;
    }
    /**
     * Execute of 'group_counting_by_creation_date' command.
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) {
                throw new WrongArgumentException();
            }
            collectionManager.groupCountingByCrDate();
        } catch (WrongArgumentException e) {
            System.out.println("Используйте: '" + getName() + "'");
        } catch (Exception e) {
            System.out.println("Ошибка. Повторите ввод.");
        }
    }
}
