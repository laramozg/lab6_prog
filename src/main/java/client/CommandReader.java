package client;


import utility.interaction.Command;
import utility.interaction.ConsoleInput;
import utility.interaction.Input;

import utility.element.*;


import java.util.Scanner;

/**
 * Класс получения и анализа данных из командной строки
 */
public class CommandReader {

    private final Input input;

    Scanner reader;

    public CommandReader() {
        this.reader = new Scanner(System.in);
        this.input=new ConsoleInput(reader);
    }
    public Command readCommand(){
        return input.readCommand();
    }

    public Worker readElement() {
        return input.readElement();
    }
}