package server.commands;

import server.Answer;

public class RemoveKey extends BinaryAction{

    @Override
    public Answer execute() {
        manager.removeKey(getParameter());
        return notifyAboutResult("Done");
    }

}
