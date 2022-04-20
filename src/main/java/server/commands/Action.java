package server.commands;

import server.Answer;
import server.AnswerType;
import server.CollectionManager;
import utility.element.Worker;

/**
 * Общий класс для всех событий,
 * запускаемых командами
 */
public abstract class Action {
    protected CollectionManager manager;
    /**
     *Содержит количество слов в команде
     */
    protected int numOfWords;

    public Answer needElement(){
        return new Answer(AnswerType.NEED_ELEMENT,"Done");
    }

    public Answer notifyAboutResult(String message){
        return new Answer(AnswerType.RETURN_ACTION,message);
    }

    public Worker getElement(){
        return ActionInvoker.getInstance().getCursorCommand().getElement();
    }

    public String getParameter(){
        return ActionInvoker.getInstance().getCursorCommand().getParameter();
    }

    public abstract Answer execute();

    public void setManager(CollectionManager manager) {
        this.manager = manager;
    }


    public int getNumOfWords() {
        return numOfWords;
    }

}