package server.commands;

import server.Answer;
import utility.element.Worker;
import utility.exceptions.IncorrectArgumentException;

public class ReplaceIfLowe extends BinaryAction{
    @Override
    public Answer execute() {
        checkKeyExist(getParameter());
        Worker element = getElement();
        if (element == null) {
            return needElement();
        }
        manager.replaceIfLowe(getParameter(),element);
        return notifyAboutResult("Done");
    }
    private void checkKeyExist(String key) {
        if (manager.getCollection().containsKey(key)) {
            throw new IncorrectArgumentException("Такого ключа не существует!");
        }
    }
}
