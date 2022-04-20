package server.commands;

import server.Answer;
import server.CollectionManager;

/**
 * Класс события очистки коллекции
 */
public class Clear extends UnaryAction {

    @Override
    public Answer execute() {
        manager.clear();
        return notifyAboutResult("Done");
    }
}