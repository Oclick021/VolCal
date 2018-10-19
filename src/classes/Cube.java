package classes;

import interfaces.IShape;

public  class Cube implements IShape  {

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

    private double length;
    private  double width;
    private  double height;

    public Cube(double lenght, double width, double height) {
        this.length = lenght;
        this.width = width;
        this.height = height;
    }

    public  double getVolume() {
        return (length * width * height);
    }

    public void save() {
    }

    public void checkVariables() {
    }
}
