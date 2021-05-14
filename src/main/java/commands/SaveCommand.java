package commands;

import exceptions.WrongArgumentException;
import utility.CollectionManager;
import utility.FileManager;


/**
 * Command 'save'. Saves the collection to a file.
 */
public class SaveCommand extends AbstractCommand implements Command {
    CollectionManager collectionManager;
    FileManager fileManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save ", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
    }

    /**
     * Execute of 'save' command.
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) {
                throw new WrongArgumentException();
            }
            collectionManager.saveCollection();
        } catch (WrongArgumentException e) {
            System.out.println("Используйте: '" + getName() + "'");
        } catch (Exception e) {
            System.out.println("Что-то пошло не так. Повторите ввод.");
        }
    }
}
