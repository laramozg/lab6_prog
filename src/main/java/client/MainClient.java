package client;

import server.Answer;
import utility.element.Worker;
import utility.exceptions.IncorrectCommandException;
import utility.interaction.Command;

import java.io.IOException;


public class MainClient {
    public static void main(String[] args) {
        InteractionClient client = new InteractionClient("localhost", 3000);
        CommandReader com = new CommandReader();
        boolean consoleOpen = true;
        System.out.println("Соединение установленно введите help, чтобы узнать список команд!");
        while (consoleOpen) {
            try {
                System.out.print(">>>");
                Command command = com.readCommand();
                client.sendCommand(command);
                Answer answer = client.getAnswer();
                switch (answer.getType()) {
                    case EXIT:
                        consoleOpen = false;
                        break;
                    case RETURN_ACTION:
                        if (!answer.getMessage().equals("Done")) {
                            System.out.println(answer.getMessage());
                        }
                        break;
                    case NEED_ELEMENT:
                        Worker worker = com.readElement();
                        command.setElement(worker);
                        client.sendCommand(command);
                        answer = client.getAnswer();
                        System.out.println(answer.getMessage());
                        break;
                    case GETTER_STATE:
                        boolean getterState = true;
                        while (getterState) {
                            Answer messageAnswer = client.getAnswer();
                            switch (messageAnswer.getType()) {
                                case DIALOG_STATE:
                                    getterState = false;
                                    if (!messageAnswer.getMessage().isEmpty()) {
                                        System.out.println(messageAnswer.getMessage());
                                    }
                                    break;
                                case RETURN_ACTION:
                                    if (!messageAnswer.getMessage().isEmpty()) {
                                        System.out.println(messageAnswer.getMessage());
                                    }
                                    break;
                            }
                        }
                        break;
                }
            } catch (IncorrectCommandException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Сервер временно недоступен.....");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }
}