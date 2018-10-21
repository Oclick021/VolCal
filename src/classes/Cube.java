package classes;

import com.mysql.cj.protocol.Resultset;
import interfaces.IShape;

import java.sql.ResultSet;
import java.util.Vector;

public  class Cube implements IShape  {

    private  int cubeID = 0;
    private double length;
    private  double width;
    private  double height;

    public Cube(double lenght, double width, double height) {
        this.length = lenght;
        this.width = width;
        this.height = height;
    }
    public Cube(int id,double lenght, double width, double height) {
        this.length = lenght;
        this.width = width;
        this.height = height;
        this.cubeID = id;
    }

    @Override
    public  double getVolume() {
        return (length * width * height);
    }

    @Override
    public void saveOnDB() {
        DbConnector con = new DbConnector();
        if (cubeID == 0)
            con.Insert(String.format("INSERT IGNORE INTO cube (height, lenght, width)\n" +
                    "  VALUES (%s, %s, %s)",height,length,width));
        else
        con.Insert(String.format("UPDATE cube SET height = %s, lenght = %s,width = %s WHERE cub1 = %s",height,length,width,cubeID));
    }

    @Override
    public String toString() {
        return String.format("Balk: Volume:%.3f Lengte:%s Hoogte:%s Breedte:%s", getVolume(), length, height,width);
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



    public static Vector<Cube> GetCubesFromDB(){
        Vector<Cube> cubes = new Vector<Cube>();
        DbConnector db = new DbConnector();

        try{
            ResultSet rs = db.get("Select * from Cube");
            while (rs.next()) {
                cubes.add(new Cube(Double.parseDouble(rs.getString("lenght")),Double.parseDouble(rs.getString("width")),Double.parseDouble(rs.getString("height"))));
            }
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
       return cubes;
    }

}
