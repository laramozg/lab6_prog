package utility.element;

import java.io.Serializable;

public class Address implements Serializable {
    private final String street; //Длина строки не должна быть больше 130, Поле не может быть null

    public Address(String street){
        this.street = street;
    }
    public String getStreet(){return street;}

    public final static int MAX_STREET = 130;

    public boolean isValid(){
        return street.length() <= Address.MAX_STREET;
    }
    @Override
    public String toString() {
        return street ;
    }
}
