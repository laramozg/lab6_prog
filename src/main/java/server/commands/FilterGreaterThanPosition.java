package server.commands;

import server.Answer;
import utility.element.Position;

import java.util.Locale;

public class FilterGreaterThanPosition extends BinaryAction{

    @Override
    public Answer execute(){
        Position position = Position.valueOf(getParameter());
        return notifyAboutResult(manager.filterGreaterThanPosition(position));
    }
}
