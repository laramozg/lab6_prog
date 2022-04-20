package utility.element;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private final Double x; //Значение поля должно быть больше -86, Поле не может быть null
    private final double y;

    public Coordinates(Double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public Double getX() {
        return x;
    }

    @Override
    public String toString() {
        return "[x=" + x +
                ", y=" + y +
                ']';
    }

    public final static int SUPREMUM_X = -86;

    public boolean isValid(){
        return x > Coordinates.SUPREMUM_X;
    }
}