package server.commands;


import server.Answer;
import server.AnswerType;
import server.InteractionServer;
import utility.element.*;
import utility.exceptions.*;
import utility.interaction.Command;
import utility.interaction.FileInput;
import utility.interaction.Input;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Класс события исполнения скрипта
 */
public class ExecuteScript extends BinaryAction {
    /**
     * Содержит названия открытых файлов
     */
    private Stack<String> openFiles = new Stack<>();
    private String errorMessage = "";
    private InteractionServer server;
    private Hashtable<String,Worker> collectionReserveCopy;
    private ActionInvoker invoker = ActionInvoker.getInstance();


    public ExecuteScript() {
        this.server = InteractionServer.getInstance();
    }


    @Override
    public Answer execute() {
        setParametersOnStart();
        try {
            executeLoop();
        } catch (IOException | EnvException | IncorrectArgumentException | IncorrectCommandException | IncorrectCollectionException | MissingElementException | StackOverflowError e) {
            errorMessage = openFiles.pop() + ": " + e.getMessage() + "\nНе выполнено!";
        } catch (IllegalArgumentException e) {
            errorMessage = openFiles.pop() + ": " + "Такого значения поля не существует!" + "\nНе выполнено!";
        } catch (NoSuchElementException e) {
            errorMessage = openFiles.pop() + ": Аргумент некорректен" + "\nНе выполнено!";
        }
        if (openFiles.size() == 0) {
            if (!errorMessage.equals("")) {
                manager.updateCollection(collectionReserveCopy);
            }
            return new Answer(AnswerType.DIALOG_STATE, errorMessage);
        }
        return notifyAboutResult("");
    }

    public void setParametersOnStart() {
        if (openFiles.size() == 0) {
            errorMessage = "";
            server.sendAnswer(new Answer(AnswerType.GETTER_STATE, ""));
            saveCollection();
        }
    }

    public void saveCollection() {
        this.collectionReserveCopy = new Hashtable<>(manager.getCollection());
    }


    public void executeLoop() throws IOException {
        String cursorFileName = System.getenv(getParameter());
        fileValidation(cursorFileName);
        openFiles.push(getParameter());
        Path file = Paths.get(cursorFileName);
        Input fileReader = new FileInput(new Scanner(file));
        while (fileReader.hasNext()) {
            try {
                if (!errorMessage.equals("")) {
                    throw new ExecuteScriptException("Произошла ошибка во время исполнения скрипта!");
                }
            } catch (ExecuteScriptException e) {
                break;
            }
            Command command = fileReader.readCommand();
            Answer answer = invoker.execute(command);
            if (answer.getType().toString().equals("NEED_ELEMENT")) {
                Worker newElement = fileReader.readElement();
                command.setElement(newElement);
                answer = invoker.execute(command);
            }
            server.sendAnswer(answer);
        }
        openFiles.pop();
    }

    private void fileValidation(String cursorFileName) {
        boolean isFileRepeat = openFiles.search(getParameter()) != -1;
        if (cursorFileName == null) {
            throw new IncorrectArgumentException("Такой переменной окружения не существует!");
        }
        if (isFileRepeat) {
            throw new StackOverflowError("Обнаружено рекурсивное открытие файла!");
        }

    }

}