package commands;

import exceptions.EmptyCollectionException;
import exceptions.IncorrectScriptException;
import exceptions.WrongIDException;
import labwork.LabWork;
import utility.CollectionManager;
import utility.LabWorkAsker;

import java.util.Map;

/**
 * This is command 'update'. Refreshes an element of collection which id equals given one.
 */
public class UpdateIDCommand extends AbstractCommand implements Command {
    CollectionManager collectionManager;
    LabWorkAsker labWorkAsker;

    public UpdateIDCommand(CollectionManager collectionManager, LabWorkAsker labWorkAsker) {
        super("update", " ID - обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.labWorkAsker = labWorkAsker;
    }

    /**
     * Execute of 'update' command.
     */
    @Override
    public void execute(String argument) throws IncorrectScriptException {
        try {
            int i = Integer.parseInt(argument);
            LabWork labWork = collectionManager.getByKey(argument);
            collectionManager.addLabWorkToCollection(
                    argument,
                    new LabWork(
                            labWork.getId(),
                            labWork.getName(),
                            labWorkAsker.askCoordinates(),
                            labWork.getCreationDate(),
                            labWorkAsker.askMinimalPoint(),
                            labWorkAsker.askPersonalQualitiesMinimum(),
                            labWorkAsker.askAveragePoint(),
                            labWorkAsker.askDifficulty(),
                            labWorkAsker.askAuthor()
                    )
            );
        } catch (NumberFormatException e) {
            System.out.println("ID должен быть целым числом");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Непредвиденная ошибка");
        }
    }
}
