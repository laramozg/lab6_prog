package utility.interaction;

import utility.element.*;
import utility.exceptions.IncorrectArgumentException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * Класс чтения полей ввода
 */
public abstract class FieldReader {
    Scanner reader;

    public FieldReader(Scanner reader) {
        this.reader = reader;
    }

    public String nameValidation() {
        String name = getInputValue();
        killIfValueIsNull(name);
        return name;
    }

    public Coordinates coordinatesValidation() {
        ArrayList<String> coordinatesArr = new ArrayList<>(Arrays.asList(getInputValue().split(" ")));
        coordinatesArr.removeIf(element->element.equals(""));
        Coordinates coordinates;
        if (coordinatesArr.size() == 2) {
            Double x = Double.parseDouble(coordinatesArr.get(0));
            double y = Double.parseDouble(coordinatesArr.get(1));
            coordinates = new Coordinates(x, y);
            if (!coordinates.isValid()) {
                throw new IncorrectArgumentException("Координаты не попадают в область допустимых значений!");
            }
        } else {
            throw new IncorrectArgumentException("Координаты введены неверно!");
        }
        return coordinates;
    }

    public Float salaryValidation() {
        String salaryLine = getInputValue();
        Float salary = null;
        if (!salaryLine.isEmpty()) {
            salary = Float.parseFloat(salaryLine);
            if (salary <= 0) {
                throw new IncorrectArgumentException("Зарплата не может быть меньше или равной нулю!");
            }
        }
        return salary;
    }

    public Date startDateValidation() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        String date = getInputValue();
        killIfValueIsNull(date);
        Date startDate;
        try {
            startDate = formatForDateNow.parse(date);
        }catch (ParseException e) {
            throw new IncorrectArgumentException("Введите дату в правильном формате!");}
        return startDate;

    }

    public Position positionValidation() {
        String positionLine = getInputValue().toUpperCase();
        Position position = null;
        if (!positionLine.isEmpty()) {
            position = Position.valueOf(positionLine);
        }
        return position;
    }


    public Status statusValidation() {
        Status status = null;
        String statusLine = getInputValue().toUpperCase();
        if (!statusLine.isEmpty()) {
            status = Status.valueOf(statusLine);
        }
        return status;
    }

    public long employeesCountValidation() {
        String countLine = getInputValue();
        long employeesCount = Long.parseLong(countLine);
        killIfValueIsNull(countLine);
        if (employeesCount <= 0) {
            throw new IncorrectArgumentException("Количество сотрудников не может быть меньше или равной нулю!");
        }
        return employeesCount;
    }

    public OrganizationType typeValidation() {
        OrganizationType type;
        String typeLine = getInputValue().toUpperCase();
        type = OrganizationType.valueOf(typeLine);
        killIfValueIsNull(typeLine);
        return type;
    }

    public Address streetValidation() {
        String street = getInputValue();
        killIfValueIsNull(street);
        if (!new Address(street).isValid()) {
            throw new IncorrectArgumentException("Длина адреса не попадает в область допустимых значений!");
        }
        return new Address(street);
    }

    private void killIfValueIsNull(String line) {
        if (line.trim().isEmpty()) {
            throw new IncorrectArgumentException("Значение не может быть пустым!");
        }
    }

    private String getInputValue() {
        return reader.nextLine().trim();
    }

}