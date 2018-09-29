package classes;

import interfaces.IShape;

public abstract class Cube implements IShape  {

    public double calculate(double length, double width, double height) {
        return (length * width * height);
    }

    public void save() {
    }

    public void checkVariables() {
    }
}
