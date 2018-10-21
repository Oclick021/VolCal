package classes;

import interfaces.IFile;
import interfaces.IShape;

import java.io.PrintWriter;

public  class Sphere implements IShape{

    private  double radius;
    private String appData;

    public Sphere(Double radius) {
        this.radius = radius;
    }
    @Override
    public double getVolume() {
        //3/4 * pi * r^3
        return (.75*Math.PI*Math.pow(radius, 3));
    }

    public void saveAsJson(){
        String sphereString = String.format("Sphere|%s", ""+radius);
        JSONFile file = new JSONFile("Sphere", sphereString);
        file.save();
    }


    public void saveAsDatabase(){

    }
    public void saveAsText(){
        String sphereString = String.format("Sphere|%s", ""+radius);
        TextFile file = new TextFile(sphereString);
        file.save();
    }

    @Override
    public String toString() {
      return String.format("Bol:  Volume:%.3f,  Straal:%s",getVolume(),radius);

    }

    public double getRadius() {
        return radius;
    }
    public void setRadius(Double radius) {
        this.radius = radius;
    }
}
