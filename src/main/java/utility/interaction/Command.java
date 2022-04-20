package utility.interaction;

import utility.element.Worker;

import java.io.Serializable;

public class Command  implements Serializable {
    private static final long serialVersionUID = 17L;
    String name;
    String parameter;
    Worker element;

    public Command(String name, String parameter) {
        this.name = name;
        this.parameter = parameter;
    }

    public void setElement(Worker element) {
        this.element = element;
    }

    public Worker getElement() {
        return element;
    }

    public String getName() {
        return name;
    }

    public String getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        if (parameter != null) {
            return name + " " + parameter;
        }
        return name;
    }
}