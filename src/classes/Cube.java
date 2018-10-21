package classes;

import interfaces.IFile;
import interfaces.IShape;

import java.io.PrintWriter;

public  class Cube implements IShape {

    private double length;
    private  double width;
    private  double height;
    private String appData;

    public Cube(double lenght, double width, double height) {
        this.length = lenght;
        this.width = width;
        this.height = height;
    }

    @Override
    public  double getVolume() {
        return (length * width * height);
    }


    public void saveAsJson(){
        String cubeString = String.format("Cube|%s|%s|%s", ""+length, ""+height, ""+width);
        JSONFile file = new JSONFile("Cube", cubeString);
    }

    public void saveAsDatabase(){

    }
    public void saveAsText(){
        String cubeString = String.format("Cube|%s|%s|%s", ""+length, ""+height, ""+width);
        TextFile file = new TextFile(cubeString);
    }

    @Override
    public String toString() {
        return String.format("Balk: Volume:%s Lengte:%s Hoogte:%s Breedte:%s", getVolume(), length, height,width);
    }

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
}
