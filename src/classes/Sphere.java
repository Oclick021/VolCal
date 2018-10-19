package classes;

import interfaces.IShape;

public  class Sphere implements IShape {


    public double getRadius() {
        return radius;
    }
    public void setRadius(Double radius) {
        this.radius = radius;
    }


    private  double radius;
    public Sphere(Double radius) {
        this.radius = radius;
    }
    @Override
    public double getVolume() {
        //3/4 * pi * r^3
        return (.75*Math.PI*Math.pow(radius, 3));
    }

    @Override
    public String toString() {
      return String.format("Bol:  Volume:%.3f,  Straal:%s",getVolume(),radius);

    }

    public void checkVariables() {
    }
    public void save() {
    }
}
