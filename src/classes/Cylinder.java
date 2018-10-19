package classes;

import interfaces.IShape;

public  class Cylinder implements IShape {

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    private  double height;
    private  double radius;

    public Cylinder(double height, double radius) {
        this.height = height;
        this.radius = radius;
    }

    public double getVolume() {
        //pi * radius^2 * height
        return (Math.PI* Math.pow(radius, 2) * height);
    }

    public void checkVariables() {
    }

    public void save() {
    }
}
