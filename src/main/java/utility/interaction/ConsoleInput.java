package utility.interaction;

import client.FieldReaderFromConsole;
import utility.element.*;

import java.util.Date;
import java.util.Scanner;

public class ConsoleInput extends Input {


    public ConsoleInput(Scanner reader) {
        super(reader);
    }

    @Override
    public Worker readElement() {
        FieldReaderFromConsole reader = new FieldReaderFromConsole(this.reader);
        System.out.println("Введите имя:");
        String name = reader.nameReadEvent();
        System.out.println("Введите координаты в формате: X Y");
        Coordinates coordinates = reader.coordinatesReadEvent();
        System.out.println("Введите зарплату работника:");
        Float salary = reader.salaryReadEvent();
        System.out.println("Введите дату:");
        Date startDate = reader.startDateReadEvent();
        System.out.println("Введите должность (HUMAN_RESOURCES, HEAD_OF_DIVISION, DEVELOPER):");
        Position position = reader.positionReadEvent();
        System.out.println("Введите статус (FIRED,HIRED,RECOMMENDED_FOR_PROMOTION, REGULAR, PROBATION):");
        Status status = reader.statusReadEvent();
        System.out.println("Введите количество сотрудников:");
        long employeesCount = reader.employeesCountReadEvent();
        System.out.println("Введите тип организации (COMMERCIAL, GOVERNMENT, OPEN_JOINT_STOCK_COMPANY):");
        OrganizationType type = reader.typeReadEvent();
        System.out.println("Введите почтовый адрес:");
        Address street = reader.streetReadEvent();
        return new Worker(name, coordinates, salary, startDate, position, status, new Organization(employeesCount,type,street));
    }
}
