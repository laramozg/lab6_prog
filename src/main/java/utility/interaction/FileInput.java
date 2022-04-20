package utility.interaction;

import server.converter.FieldReaderFromFile;
import utility.element.*;

import java.util.Date;
import java.util.Scanner;

public class FileInput extends Input {

    public FileInput(Scanner reader) {
        super(reader);
    }

    @Override
    public Worker readElement() {
        FieldReaderFromFile reader = new FieldReaderFromFile(this.reader);
        String name = reader.nameReadEvent();
        Coordinates coordinates = reader.coordinatesReadEvent();
        Float salary = reader.salaryReadEvent();
        Date startDate = reader.startDateReadEvent();
        Position position = reader.positionReadEvent();
        Status status = reader.statusReadEvent();
        long employeesCount = reader.employeesCountReadEvent();
        OrganizationType type = reader.typeReadEvent();
        Address street = reader.streetReadEvent();
        return new Worker(name, coordinates, salary, startDate, position, status, new Organization(employeesCount,type,street));
    }
}