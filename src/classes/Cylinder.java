package classes;

import interfaces.IShape;

import java.sql.ResultSet;
import java.util.Vector;

public class Cylinder implements IShape {
    private  int cylinderID = 0;

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

    @Override
    public void saveOnDB() {
        DbConnector con = new DbConnector();
        if (cylinderID == 0)
            con.insert(String.format("INSERT IGNORE INTO cylinder (radius, height)\n" +
                    "  VALUES (%s, %s)", radius, height));
        else
            con.insert(String.format("UPDATE cylinder SET radius = %s, height = %s WHERE cylinderID = %s", radius, height, cylinderID));
    }

    public void saveAsJson(){
        String cylinderString = String.format("Cylinder|%s|%s", ""+height, ""+radius);
        JSONFile file = new JSONFile("Cylinder", cylinderString);
        file.save();
    }

    public void saveAsText(){
        String cylinderString = String.format("Cylinder|%s|%s", ""+height, ""+radius);
        TextFile file = new TextFile(cylinderString);
        file.save();
    }

    @Override
    public String toString() {
        return  String.format("Cylinder: Volume:%.3f Straal:%s Hoogte:%s", getVolume(), radius, height);
    }

    public double getHeight() {
        return height;
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
