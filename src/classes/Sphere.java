package classes;

import interfaces.IShape;

public abstract class Sphere implements IShape {

    private  double radius;
    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double getVolume() {
        //3/4 * pi * r^3
        return (.75*Math.PI*Math.pow(radius, 3));
    }

    @Override
    public void save() {
    }

    public double getRadius() {
        return radius;
    }
    public void setRadius(Double radius) {
        this.radius = radius;
    }
}
