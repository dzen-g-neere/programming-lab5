package commands;

import exceptions.IncorrectScriptException;

public interface Command {
    /**
     * Interface for all commands.
     */

    void execute(String argument) throws IncorrectScriptException;

    String getName();
    String getDescription();
}
