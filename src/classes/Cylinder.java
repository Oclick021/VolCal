package classes;

import interfaces.IFile;
import interfaces.IShape;

public class Cylinder implements IShape {

    private  double height;
    private  double radius;

    public Cylinder(double height, double radius) {
        this.height = height;
        this.radius = radius;
    }

    @Override
    public double getVolume() {
        //pi * radius^2 * height
        return (Math.PI* Math.pow(radius, 2) * height);
    }

    @Override
    public void save() {

    }

    @Override
    public String toString() {
        return  String.format("Cylinder: Volume:%s Straal:%s Hoogte:%s", getVolume(), radius, height);
    }

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

}
