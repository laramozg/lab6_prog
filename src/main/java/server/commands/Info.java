package server.commands;

import server.Answer;

/**
 * Класс события вывода информации о коллекции
 */
public class Info extends UnaryAction {

    @Override
    public Answer execute() {
        return notifyAboutResult(manager.info());
    }
}