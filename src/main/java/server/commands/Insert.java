package server.commands;

import server.Answer;
import utility.element.Worker;

/**
 * Класс события добавления
 */
public class Insert extends BinaryAction {
    @Override
    public Answer execute() {
        Worker element = getElement();
        if (element == null) {
            return needElement();
        }
        manager.insert(getParameter(),element);
        return notifyAboutResult("Done");
    }
}