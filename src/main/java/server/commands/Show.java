package server.commands;

import server.Answer;

/**
 * Класс события вывода коллекции
 */
public class Show extends UnaryAction {

    @Override
    public Answer execute() {
        return notifyAboutResult(manager.show());
    }
}