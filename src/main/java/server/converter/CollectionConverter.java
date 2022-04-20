package server.converter;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import utility.element.*;
import utility.exceptions.FileRootsException;
import utility.exceptions.IncorrectCollectionException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;


public class CollectionConverter {
    public Hashtable<String,Worker> manager = new Hashtable<>();
    /**
     * Метод для преобразования содержимого файла в массив элементов класса Worker
     *
     * @param file Файл, из которого берём данные
     * @return Массив объектов класса Worker
     */
    public Hashtable<String,Worker> readFile(Path file) throws IOException {
        if (!Files.exists(file)) {
            Files.createFile(file);
        }
        if (!Files.isReadable(file)) {
            throw new FileRootsException("Отсутствуют права на чтение файла!");
        }
        Worker worker;
        Position position;
        Status status;
        FileReader Data = new FileReader(String.valueOf(file));
        CSVParser parser = CSVParser.parse(Data, CSVFormat.DEFAULT);
        for (CSVRecord csvRecord : parser) {
            try {
                if (csvRecord.get(8).trim().equals("")) {position = null;}
                else {position = Position.valueOf(csvRecord.get(8).trim());}
                if (csvRecord.get(9).trim().equals("")) {status = null;}
                else {status = Status.valueOf(csvRecord.get(9).trim());}
                if (csvRecord.get(0) == null || csvRecord.get(1) == null || csvRecord.get(2) == null || csvRecord.get(3) == null || csvRecord.get(4) == null || csvRecord.get(10) == null || csvRecord.get(11) == null || csvRecord.get(12) == null) {
                    break;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                Date startDate = simpleDateFormat.parse(csvRecord.get(7).trim());
                worker = new Worker(csvRecord.get(2).trim(), new Coordinates(Double.valueOf(csvRecord.get(3).trim()), Double.parseDouble(csvRecord.get(4).trim())), Float.parseFloat(csvRecord.get(6).trim()), startDate, position, status, new Organization(Long.parseLong(csvRecord.get(10).trim()), OrganizationType.valueOf(csvRecord.get(11).trim()), new Address(csvRecord.get(12).trim())));
                worker.setId(Integer.parseInt(csvRecord.get(1)));
                int i = 0;
                Enumeration<String> key = manager.keys();
                while (key.hasMoreElements()) {
                    String k = key.nextElement();
                    if (manager.get(k).getId() == worker.getId()) {
                        i = 1;
                    }
                }if(i!=0){
                    break;
                }
                worker.setCreationDate(csvRecord.get(5));
                String[] dateComponents = worker.getCreationDate().split("-");
                if (dateComponents.length != 3) {
                    break;
                }
                LocalDate.of(Integer.parseInt(dateComponents[0]), Integer.parseInt(dateComponents[1]), Integer.parseInt(dateComponents[2]));
                if (!worker.getOrganization().isValid() || !worker.getCoordinates().isValid() || worker.getSalary() <= 0){
                    break;
                }
                manager.put(csvRecord.get(0).trim(), worker);
            }catch (DateTimeParseException | IllegalArgumentException |ArrayIndexOutOfBoundsException | IncorrectCollectionException| ParseException e){
                break;
            }
        }
        return new Hashtable<>(manager);
    }


    /**
     * Метод для записи коллекции в файл
     * @param collection наша коллекция
     * @param file файл для записи
     */
    public void recordFile(Hashtable<String,Worker> collection, Path file) {
        if (!Files.isWritable(file)) {
            throw new FileRootsException("Файл не имеет прав на запись!");
        }
        File outfile = new File(String.valueOf(file));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outfile))) {
            collection.forEach((key, value) -> {
                try {
                    String position;
                    if (value.getPosition() == null) {
                        position = "";
                    }else position = value.getPosition().toString();
                    String salary;
                    if (value.getSalary() == null) {
                        salary = "";
                    }else salary = value.getSalary().toString();
                    String status;
                    if (value.getStatus() == null) {
                        status = "";
                    }else status = value.getStatus().toString();
                    String contact = key + "," + value.getId() + "," + value.getName() +
                            "," + value.getCoordinates().getX() + "," + value.getCoordinates().getY() +
                            "," + value.getCreationDate() + "," + salary +
                            "," + value.getStartDate() + "," + position +
                            "," + status + "," + value.getOrganization().getEmployeesCount() +
                            "," + value.getOrganization().getType() + "," + value.getOrganization().getPostalAddress() + "\n";
                    writer.write(contact);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception s) {
            System.out.println("Не сохраняется " + s.getMessage());
        }
    }

}