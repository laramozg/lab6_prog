package server.commands;

import server.Answer;
import server.CollectionManager;
import utility.exceptions.IncorrectCommandException;
import utility.interaction.Command;

import java.io.IOException;
import java.util.HashMap;

/**
 * Класс запуска события
 */
public class ActionInvoker {
    /**
     * Хэштаблица для хранения событий с командами, которые их запускают
     */
    private HashMap<String, Action> commands = new HashMap<>();
    public static ActionInvoker invoker;
    private CollectionManager manager;
    private Command cursorCommand;

    public Command getCursorCommand() {
        return cursorCommand;
    }

    private ActionInvoker() {
    }

    public static ActionInvoker getInstance() {
        if (invoker == null) {
            invoker = new ActionInvoker();
        }
        return invoker;
    }

    public void setManager(CollectionManager manager) {
        this.manager = manager;
        invoker.addCommand("help", new Help());
        invoker.addCommand("insert", new Insert());
        invoker.addCommand("show", new Show());
        invoker.addCommand("count_by_start_date", new CountByStartDate());
        invoker.addCommand("clear", new Clear());
        invoker.addCommand("info", new Info());
        invoker.addCommand("update", new Update());
        invoker.addCommand("print_field_descending_start_date", new PrintFieldDescendingStartDate());
        invoker.addCommand("replace_if_lowe", new ReplaceIfLowe());
        invoker.addCommand("replace_if_greater", new ReplaceIfGreater());
        invoker.addCommand("remove_key", new RemoveKey());
        invoker.addCommand("remove_greater", new RemoveGreater());
        invoker.addCommand("filter_greater_than_position", new FilterGreaterThanPosition());
        invoker.addCommand("execute_script", new ExecuteScript());
        invoker.addCommand("exit", new Exit());
    }

    public void addCommand(String name, Action action) {
        action.setManager(manager);
        commands.put(name, action);
    }

    public void save() throws IOException {
        manager.save();
    }

    /**
     * Выбрать и выполнить команду
     *
     * @param command команда, которую исполняем
     */
    public Answer execute(Command command) {
        Answer answer;
        checkCommandExistence(command);
        String commandName = command.getName();
        Action action = getAction(commandName);
        this.cursorCommand = command;
        answer = action.execute();
        return answer;
    }

    public Action getAction(String commandName) {
        return commands.get(commandName);
    }

    /**
     * Проверка команды на корректность
     */
    public void checkCommandExistence(Command command) {
        String commandName = command.getName();

        if (!commands.containsKey(commandName)) {
            throw new IncorrectCommandException("Команды не существует!");
        }
        if (!isCorrectCommandForm(command)) {
            throw new IncorrectCommandException("Команда не может содержать такой аргумент!");
        }

    }


    private boolean isCorrectCommandForm(Command command) {
        return isUnaryCommand(command) || isBinaryCommand(command);
    }

    /**
     * Команда из одного слова или нет
     */
    private boolean isUnaryCommand(Command command) {
        return isSecondParameterNull(command) && getNumOfParameters(command) == 1;
    }

    /**
     * Команда из двух слов или нет
     */
    private boolean isBinaryCommand(Command command) {
        return !isSecondParameterNull(command) && getNumOfParameters(command) == 2;
    }


    private int getNumOfParameters(Command command) {
        return getAction(command.getName()).getNumOfWords();
    }

    private boolean isSecondParameterNull(Command command) {
        return command.getParameter() == null;
    }


}
