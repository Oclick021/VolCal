package classes;

import interfaces.IFile;
import interfaces.IShape;

import java.io.PrintWriter;

public class Cylinder implements IShape {

    private  double height;
    private  double radius;
    private String appData;

    public Cylinder(double height, double radius) {
        this.height = height;
        this.radius = radius;
    }

    @Override
    public double getVolume() {
        //pi * radius^2 * height
        return (Math.PI* Math.pow(radius, 2) * height);
    }



    public void saveAsJson(){
        String cylinderString = String.format("Cylinder|%s|%s", ""+height, ""+radius);
        JSONFile file = new JSONFile("Sphere", cylinderString);
        file.save();
    }


    public void saveAsDatabase(){

    }

    public void saveAsText(){
        String cylinderString = String.format("Cylinder|%s|%s", ""+height, ""+radius);
        TextFile file = new TextFile(cylinderString);
        file.save();
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
