package classes;

import interfaces.IShape;

import java.sql.ResultSet;
import java.util.Vector;

public  class Cube implements IShape  {

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

    @Override
    public void saveOnDB() {
        DbConnector con = new DbConnector();
        if (con.isDbConnected()) {
            con.insert(String.format("INSERT IGNORE INTO cube (height, lenght, width)\n" +
                    "  VALUES (%s, %s, %s)", height, length, width));
        }
    }
    @Override
    public void saveAsJson() {
        String cubeString = String.format("Cube|%s|%s|%s", "" + length, "" + height, "" + width);
        JSONFile file = new JSONFile("Cube", cubeString);
    }

    @Override
    public void saveAsText() {
        String cubeString = String.format("Cube|%s|%s|%s", "" + length, "" + height, "" + width);
        TextFile file = new TextFile(cubeString);
    }

    @Override
    public String toString() {
        return String.format("Balk: Volume:%.3f Lengte:%s Hoogte:%s Breedte:%s", getVolume(), length, height, width);
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


    public static Vector<Cube> getCubesFromDB() {
        Vector<Cube> cubes = new Vector<Cube>();
        DbConnector db = new DbConnector();

        try {
            ResultSet rs = db.get("Select * from Cube");
            while (rs.next()) {
                cubes.add(new Cube(Double.parseDouble(rs.getString("lenght")), Double.parseDouble(rs.getString("width")), Double.parseDouble(rs.getString("height"))));
            }
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return cubes;
    }

}
