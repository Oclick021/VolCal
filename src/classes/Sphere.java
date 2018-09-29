package classes;

import interfaces.IShape;

public abstract class Sphere implements IShape {
    public Double calculate(Double radius) {
        //3/4 * pi * r^3
        return (.75*Math.PI*Math.pow(radius, 3));
    }

    public void checkVariables() {
    }

    public void save() {
    }
}
