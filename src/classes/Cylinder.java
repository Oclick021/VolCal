package classes;

import interfaces.IFile;
import interfaces.IShape;

public abstract class Cylinder implements IShape {

    public double calculate(double height, double radius) {
        //pi * radius^2 * height
        return (Math.PI* Math.pow(radius, 2) * height);
    }

    public Double calculate(Double radius, Double height) {
        //pi*R^2*H
        return (Math.PI * (Math.pow(radius, 2) * height));
    }

    public void checkVariables() {
    }

    public void save() {
    }
}
