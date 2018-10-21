package classes;

import interfaces.IShape;

import java.sql.ResultSet;
import java.util.Vector;

public class TruncatedCone implements IShape {
    private  double height;
    private  double radius1;
    private double radius2;
    private String appData;

    public TruncatedCone(double height, double radius1, double radius2) {
        this.height = height;
        this.radius1 = radius1;
        this.radius2 = radius2;
    }

    @Override
    public double getVolume() {
        //pi * radius^2 * height
        return  (Math.PI / 3) * height * (Math.pow(radius1, 2) + Math.pow(radius2, 2) + radius2 * radius1);
    }

    @Override
    public void saveOnDB() {
        DbConnector con = new DbConnector();
        con.insert(String.format("UPDATE truncatedCone SET radius1 = %s radius2 = %s, height = %s:", radius1,radius2 ,height));
    }

    public void saveAsJson(){
        String truncatedConeString = String.format("TruncatedCone|%s|%s|%s", ""+height, ""+radius1, radius2);
        JSONFile file = new JSONFile("TruncatedCone", truncatedConeString);
        file.save();
    }

    public void saveAsText(){
        String truncatedConeString = String.format("TruncatedCone|%s|%s|%s", ""+height, ""+radius1, radius2);
        TextFile file = new TextFile(truncatedConeString);
        file.save();
    }

    @Override
    public String toString() {
        return  String.format("TruncatedCone: Volume:%.3f Straal 1:%s Straal 2:%s Hoogte:%s", getVolume(), radius1,radius2, height);
    }

    public double getHeight() {
        return height;
    }



    public double getRadius1() {
        return radius1;
    }
    public double getRadius2() {
        return radius2;
    }


    public void setRadius1(double radius) {
        this.radius1 = radius;
    }
    public void setRadius2(double radius) {
        this.radius2 = radius;
    }



    public static Vector<TruncatedCone> GetTruncatedConeFromDB(){
        Vector<TruncatedCone> truncatedCones = new Vector<TruncatedCone>();
        DbConnector db = new DbConnector();

        try{
            ResultSet rs = db.get("SELECT  * FROM  TruncatedCone");
            while (rs.next()) {
                truncatedCones.add(new TruncatedCone(Double.parseDouble(rs.getString("height")),Double.parseDouble(rs.getString("radius1")),Double.parseDouble(rs.getString("radius2"))));
            }
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return truncatedCones;
    }
}
