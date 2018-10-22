package classes;

import interfaces.IShape;
import java.sql.ResultSet;
import java.util.Vector;

public class HollowCylinder implements IShape {

    private  double height;
    private  double radius1;
    private double radius2;
    private String appData;

    public HollowCylinder(double height, double radius1, double radius2) {
        this.height = height;
        this.radius1 = radius1;
        this.radius2 = radius2;
    }

    @Override
    public double getVolume() {
        //pi * radius^2 * height
        return (Math.PI * height) * (Math.pow(radius1, 2) - Math.pow(radius2, 2));
    }

    @Override
    public void saveOnDB() {
        DbConnector con = new DbConnector();
        con.insert(String.format("INSERT ignore INTO hollowcylinder (height, radius1, radius2)\n" +
                "  VALUES (%s, %s, %s)", height,radius1 ,radius2));
    }

    public void saveAsJson(){
        String cylinderString = String.format("HolleCylinder|%s|%s|%s", ""+height, ""+radius1, radius2);
        JSONFile file = new JSONFile("Cylinder", cylinderString);
        file.save();
    }

    public void saveAsText(){
        String cylinderString = String.format("HolleCylinder|%s|%s|%s", ""+height, ""+radius1, radius2);
        TextFile file = new TextFile(cylinderString);
        file.save();
    }

    @Override
    public String toString() {
        return  String.format("Holle Cylinder: Volume:%.3f Straal 1:%s Straal 2:%s Hoogte:%s", getVolume(), radius1,radius2, height);
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



    public static Vector<HollowCylinder> getCylinderFromDB(){
        Vector<HollowCylinder> hollowCylinders = new Vector<HollowCylinder>();
        DbConnector db = new DbConnector();

        try{
            ResultSet rs = db.get("Select * from hollowcylinder");
            while (rs.next()) {
                hollowCylinders.add(new HollowCylinder(Double.parseDouble(rs.getString("height")),Double.parseDouble(rs.getString("radius1")),Double.parseDouble(rs.getString("radius2"))));
            }
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return hollowCylinders;
    }

}
