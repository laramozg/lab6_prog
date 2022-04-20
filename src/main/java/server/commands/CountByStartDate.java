package server.commands;

import server.Answer;
import utility.exceptions.IncorrectArgumentException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CountByStartDate extends BinaryAction{

    @Override
    public Answer execute() {
        try{
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
            Date start_date;
            start_date = formatForDateNow.parse(getParameter());
            return notifyAboutResult(manager.countByStartDate(start_date));
        }catch (ParseException e) {
            throw new IncorrectArgumentException("Дату необходимо ввести в формате год-месяц-день!");
        }
    }
}
