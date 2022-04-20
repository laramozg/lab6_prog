package server.commands;

import server.Answer;
import server.CollectionManager;

/**
 * Класс события вывода всех команд
 */
public class Help extends UnaryAction {


    @Override
    public Answer execute() {
        return notifyAboutResult(manager.help());
    }
}