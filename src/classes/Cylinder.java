package classes;

import interfaces.IFile;
import interfaces.IShape;

import java.sql.ResultSet;
import java.util.Vector;

public class Cylinder implements IShape {
    private  int cylinderID = 0;

    private  double height;
    private  double radius;


    public Cylinder(double height, double radius) {
        this.height = height;
        this.radius = radius;
    }
    public Cylinder(int id ,double height, double radius) {
        this.height = height;
        this.radius = radius;
        cylinderID = id;
    }
    @Override
    public double getVolume() {
        //pi * radius^2 * height
        return (Math.PI* Math.pow(radius, 2) * height);
    }

    @Override
    public void saveOnDB() {
        DbConnector con = new DbConnector();
        if (cylinderID == 0)
        con.Insert(String.format("INSERT IGNORE INTO cylinder (radius, height)\n" +
                "  VALUES (%s, %s)",getRadius(),height));
        else
            con.Insert(String.format("UPDATE cylinder SET radius = %s, height = %s WHERE cylinderID = %s",getRadius(),height, cylinderID));
    }

    @Override
    public String toString() {
        return  String.format("Cylinder: Volume:%.3f Straal:%s Hoogte:%s", getVolume(), radius, height);
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


    public static Vector<Cylinder> GetCylinderFromDB(){
        Vector<Cylinder> cylinders = new Vector<Cylinder>();
        DbConnector db = new DbConnector();

        try{
            ResultSet rs = db.get("SELECT  * FROM  cylinder");
            while (rs.next()) {
                cylinders.add(new Cylinder(Double.parseDouble(rs.getString("height")),Double.parseDouble(rs.getString("radius"))));
            }
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return cylinders;
    }
}
