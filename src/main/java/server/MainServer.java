package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.commands.ActionInvoker;
import server.converter.CollectionFileTuner;
import utility.exceptions.IncorrectArgumentException;
import utility.exceptions.IncorrectCommandException;
import utility.exceptions.MissingElementException;
import utility.interaction.Command;
import java.io.IOException;

public class MainServer {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(MainServer.class);
        CollectionFileTuner tuner = new CollectionFileTuner();
        InteractionServer server;
        ActionInvoker invoker = ActionInvoker.getInstance();
        tuner.connectCollection("csv");
        server = InteractionServer.getInstance();
        logger.debug("Сервер запущен!");
        while (true) {
            Answer answer;
            Command command = server.getCommand();
            logger.info("Обработка команды: " + command.getName());
            try {
                answer = invoker.execute(command);
            } catch (IncorrectCommandException | IncorrectArgumentException | MissingElementException e) {
                answer = new Answer(AnswerType.RETURN_ACTION, e.getMessage());
                logger.error("Команда " + command.getName() + " не выполнена. Ошибка: " + e.getMessage() + " Аргумент: " + command.getParameter());
            } catch (IllegalArgumentException e) {
                answer = new Answer(AnswerType.RETURN_ACTION, "Аргумент команды некорректен!");
                logger.error("Аргумент команды " + command.getName() + " некорректен. Введено: " + command.getParameter());
            }
            try {
                if (answer.getType().toString().equals("EXIT")) {
                    invoker.save();
                    logger.debug("Клиентское приложение завершено. Коллекция сохранена");
                }
            } catch (IOException e) {
                answer = new Answer(AnswerType.RETURN_ACTION, e.getMessage());
                logger.error("Клиентское приложение не завершено. Файл утерян или не имеет прав на запись");
            }
            server.sendAnswer(answer);
        }
    }
}