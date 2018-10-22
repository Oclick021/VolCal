package classes;

import interfaces.IShape;

import java.sql.ResultSet;
import java.util.Vector;

public  class Sphere implements IShape {

    public double getRadius() {
        return radius;
    }
    public void setRadius(Double radius) {
        this.radius = radius;
    }


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
    @Override
    public void saveAsJson(){
        String sphereString = String.format("Sphere|%s", ""+radius);
        JSONFile file = new JSONFile("Sphere", sphereString);
        file.save();
    }


    @Override
    public void saveAsText(){
        String sphereString = String.format("Sphere|%s", ""+radius);
        TextFile file = new TextFile(sphereString);
        file.save();
    }


    public String toString() {
      return String.format("Bol:  Volume:%.3f,  Straal:%s",getVolume(),radius);

    }

    public void checkVariables() {
    }
    public void saveOnDB() {
        DbConnector con = new DbConnector();
        con.insert(String.format("INSERT IGNORE INTO sphere (radius) VALUES (%s)",getRadius()));
    }
    public static Vector<Sphere> getCylinderFromDB(){
        Vector<Sphere> spheres= new Vector<Sphere>();
        DbConnector db = new DbConnector();

        try{
            ResultSet rs = db.get("SELECT  * FROM  Sphere");
            while (rs.next()) {
                spheres.add(new Sphere(Double.parseDouble(rs.getString("radius"))));
            }
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return spheres;
    }
}
