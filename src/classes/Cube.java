package classes;

import interfaces.IShape;

public abstract class Cube implements IShape  {

    private double length;
    private  double width;
    private  double height;

    public Cube(double lenght, double width, double height) {
        this.length = lenght;
        this.width = width;
        this.height = height;
    }

    @Override
    public  double getVolume() {
        return (length * width * height);
    }

    @Override
    public void save() {
    }

    @Override
    public String toString() {
        return String.format("Balk: Volume:%s Lengte:%s Hoogte:%s Breedte:%s", getVolume(), length, height,width);
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
