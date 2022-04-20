package client;

import utility.element.*;
import utility.exceptions.IncorrectArgumentException;
import utility.interaction.FieldReader;

import java.util.Date;
import java.util.Scanner;

public class FieldReaderFromConsole extends FieldReader {
    /**
     * Чтение элементов из консоли
     * @param reader Поток ввода
     */
    public FieldReaderFromConsole(Scanner reader) {
        super(reader);
    }


    public String nameReadEvent() {
        String name;
        while (true) {
            try {
                name = nameValidation();
                break;
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return name;
    }

    public Coordinates coordinatesReadEvent() {
        Coordinates coordinates;
        while (true) {
            try {
                coordinates = coordinatesValidation();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введены не числа!");
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
        return coordinates;
    }

    public Float salaryReadEvent() {
        Float salary;
        while (true) {
            try {
                salary = salaryValidation();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введено не число!");
            }catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return salary;
    }


    public Date startDateReadEvent() {
        Date startDate;
        while (true) {
            try {
                startDate = startDateValidation();
                break;
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return startDate;
    }


    public Position positionReadEvent() {
        Position position;
        while (true) {
            try {
                position = positionValidation();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Введено некорректное значение!");
            }
        }
        return position;
    }

    public Status statusReadEvent() {
        Status status;
        while (true) {
            try {
                status = statusValidation();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Введено некорректное значение!");
            }
        }
        return status;
    }

    public long employeesCountReadEvent() {
        long employeesCount;
        while (true) {
            try {
                employeesCount = employeesCountValidation();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введено не число!");
            }catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return employeesCount;
    }

    public OrganizationType typeReadEvent() {
        OrganizationType type;
        while (true) {
            try {
                type = typeValidation();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Введено некорректное значение!");
            }
        }
        return type;
    }

    public Address streetReadEvent(){
        Address street;
        while (true) {
            try {
                street = streetValidation();
                break;
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return street;
    }
}