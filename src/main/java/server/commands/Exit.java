
package server.commands;

import server.Answer;
import server.AnswerType;

/**
 * Класс события выхода из программы
 */
public class Exit extends UnaryAction {


    @Override
    public Answer execute() {
        return new Answer(AnswerType.EXIT,"");
    }
}