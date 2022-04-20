package utility.interaction;

import utility.element.Worker;
import utility.exceptions.IncorrectCommandException;

import java.util.Scanner;

public abstract class Input {
    Scanner reader;

    public Input(Scanner reader) {
        this.reader = reader;
    }

    public abstract Worker readElement();


    public Command readCommand() {
        String[] line = reader.nextLine().trim().split(" ");
        if (line.length > 2) {
            throw new IncorrectCommandException("Команда введена некорректно!");
        }
        if (line.length == 2) {
            return new Command(line[0], line[1]);
        }
        return new Command(line[0], null);
    }

    public boolean hasNext() {
        return reader.hasNext();
    }

}