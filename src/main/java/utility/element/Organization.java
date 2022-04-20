package utility.element;

import java.io.Serializable;

public class Organization implements Serializable {
    private final long employeesCount; //Значение поля должно быть больше 0
    private final OrganizationType type; //Поле не может быть null
    private final Address postalAddress; //Поле не может быть null

    public Organization(long employeesCount,OrganizationType type,Address postalAddress){
        this.employeesCount = employeesCount;
        this.type = type;
        this.postalAddress = postalAddress;
    }
    public long getEmployeesCount(){return employeesCount;}
    public OrganizationType getType(){return type;}
    public Address getPostalAddress(){return postalAddress;}

    public boolean isValid(){
        return employeesCount > 0 && type != null && postalAddress.isValid();
    }
    @Override
    public String toString() {
        return "[employeesCount=" + employeesCount +
                ", type=" + type +
                ", postalAddress=" + postalAddress +
                ']';
    }
}
