package server.commands;

import server.Answer;
import utility.element.Worker;
import utility.exceptions.IncorrectArgumentException;

/**
 * Класс заменяет элемент по ключу, если
 */
public class ReplaceIfGreater extends BinaryAction {
    @Override
    public Answer execute() {
        checkKeyExist(getParameter());
        Worker element = getElement();
        if (element == null) {
            return needElement();
        }
        manager.replaceIfGreater(getParameter(),element);
        return notifyAboutResult("Done");
    }
    private void checkKeyExist(String key) {
        if (manager.getCollection().containsKey(key)) {
            throw new IncorrectArgumentException("Такого ключа не существует!");
        }
    }
}