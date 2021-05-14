package commands;

import exceptions.IncorrectScriptException;
import exceptions.WrongArgumentException;
import labwork.LabWork;
import utility.CollectionManager;
import utility.LabWorkAsker;

/**
 * This is command 'insert'. Inserts a new element to collection.
 */
public class InsertCommand extends AbstractCommand {
    CollectionManager collectionManager;
    LabWorkAsker labWorkAsker;

    public InsertCommand(CollectionManager collectionManager, LabWorkAsker labWorkAsker) {
        super("insert", " \"key\" - добавить новый элемент с заданным ключом");
        this.collectionManager = collectionManager;
        this.labWorkAsker = labWorkAsker;
    }

    /**
     * Execute of 'insert' command.
     */
    @Override
    public void execute(String argument) throws IncorrectScriptException {
//        Integer id,String name, Coordinates coordinates, Date creationDate, Long minimalPoint, long personalQualitiesMinimum, float averagePoint, Difficulty difficulty, Person author
        try {
            if (argument.isEmpty()) throw new WrongArgumentException();
            labWorkAsker.checkKey(argument.trim());
            collectionManager.addLabWorkToCollection(
                    argument.trim(),
                    new LabWork(
                            labWorkAsker.askID(),
                            argument,
                            labWorkAsker.askCoordinates(),
                            labWorkAsker.askDate(),
                            labWorkAsker.askMinimalPoint(),
                            labWorkAsker.askPersonalQualitiesMinimum(),
                            labWorkAsker.askAveragePoint(),
                            labWorkAsker.askDifficulty(),
                            labWorkAsker.askAuthor()
                    )
            );
        } catch (IncorrectScriptException e) {
            throw new IncorrectScriptException();
        } catch (WrongArgumentException e) {
            System.out.println("Ключ не может содержать символ ';', ключ не может быть пустой строкой");
        }
    }
}
