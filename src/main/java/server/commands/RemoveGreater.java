package server.commands;

import server.Answer;
import utility.element.Worker;

public class RemoveGreater extends UnaryAction{

    @Override
    public Answer execute() {
        Worker element = getElement();
        if (element == null) {
            return needElement();
        }
        manager.removeGreater(element);
        return notifyAboutResult("Done");
    }
}
