package commands;

import exceptions.IncorrectScriptException;
import utility.ConsoleManager;
/**
 * This is command 'execute_script'. It executes commands from script.
 */
public class ExecuteScriptCommand extends AbstractCommand implements Command{

    public ExecuteScriptCommand() {
        super("execute_script", " file_name - считать и исполнить скрипт из указанного файла. " +
                "В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");

    }
    /**
     * Execute of 'execute_script' command.
     */
    @Override
    public void execute(String argument) {

    }
}
