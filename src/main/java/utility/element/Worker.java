package utility.element;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

public class Worker implements Serializable {
    private static final long serialVersionUID = 42L;
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private String creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float salary; //Поле может быть null, Значение поля должно быть больше 0
    private Date startDate; //Поле не может быть null
    private Position position; //Поле может быть null
    private Status status; //Поле может быть null
    private Organization organization; //Поле не может быть null


    public Worker(String name, Coordinates coordinates, Float salary, Date startDate, Position position, Status status, Organization organization){
        this.name = name;
        this.coordinates = coordinates;
        this.salary = salary;
        this.startDate = startDate;
        this.position = position;
        this.status = status;
        this.organization = organization;
    }


    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }


    public String getName() {return name;}
    public int getId(){return id;}
    public Coordinates getCoordinates(){return coordinates;}
    public String getCreationDate(){return creationDate;}
    public Float getSalary(){return salary;}
    public Date getStartDate() {return startDate;}
    public Position getPosition(){return position;}
    public Status getStatus(){return status;}
    public Organization getOrganization(){return organization;}

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return id == worker.id && Objects.equals(name, worker.name) && Objects.equals(coordinates, worker.coordinates) && Objects.equals(creationDate, worker.creationDate) && Objects.equals(salary, worker.salary) && Objects.equals(startDate, worker.startDate) && position == worker.position && status == worker.status && Objects.equals(organization, worker.organization);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return  "[id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", salary=" + salary +
                ", startDate=" + startDate +
                ", position=" + position +
                ", status=" + status +
                ", organization=" + organization +
                ']';
    }

}
