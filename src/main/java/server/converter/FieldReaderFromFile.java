package server.converter;

import utility.element.*;
import utility.interaction.FieldReader;

import java.util.Date;
import java.util.Scanner;

public class FieldReaderFromFile extends FieldReader {
    public FieldReaderFromFile(Scanner reader) {
        super(reader);
    }

    public String nameReadEvent() {
        return nameValidation();
    }

    public Coordinates coordinatesReadEvent() {
        return coordinatesValidation();
    }

    public Float salaryReadEvent() {
        return salaryValidation();
    }

    public Date startDateReadEvent() {
        return startDateValidation();
    }

    public Position positionReadEvent() {
        return positionValidation();
    }


    public Status statusReadEvent() {
        return statusValidation();
    }


    public long employeesCountReadEvent() {
        return employeesCountValidation();
    }

    public OrganizationType typeReadEvent(){
        return typeValidation();
    }

    public Address streetReadEvent(){
        return streetValidation();
    }
}