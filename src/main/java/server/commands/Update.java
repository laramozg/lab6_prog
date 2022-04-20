package server.commands;

import server.Answer;
import utility.element.Worker;
import utility.exceptions.IncorrectArgumentException;

public class Update extends BinaryAction {

    @Override
    public Answer execute() {
        int id = getId();
        checkIdExist(id);
        Worker element = getElement();
        if (element == null) {
            return needElement();
        }
        manager.update(id, element);
        return notifyAboutResult("Done");
    }

    private void checkIdExist(int id) {
        if (manager.getKeyById(id) == null) {
            throw new IncorrectArgumentException("Такого id не существует!");
        }
    }

    private int getId() {
        return Integer.parseInt(getParameter());
    }
}
