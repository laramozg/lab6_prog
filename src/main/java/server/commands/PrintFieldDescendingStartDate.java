package server.commands;


import server.Answer;

public class PrintFieldDescendingStartDate extends UnaryAction{

    @Override
    public Answer execute() {
        return notifyAboutResult(manager.printFieldDescendingStartDate());
    }
}

