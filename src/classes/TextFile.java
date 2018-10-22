package classes;

import interfaces.IFile;
import interfaces.IShape;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;


public class TextFile implements IFile {
    private String appData;
    private String saveString;

    public TextFile(String _saveString){
        saveString = _saveString;
        appData = System.getenv("APPDATA");
    }

    public TextFile(){
        appData = System.getenv("APPDATA");
    }

    @Override
    public void save(){
        if (!checkConnection()){
            System.err.println("Got an exception! ");
            System.err.println("Could not find file.");
            return;
        }

        ArrayList<String> text = getFile();
        if (!saveString.equals("")){
            if (!text.contains(saveString)){
                text.add(saveString);
            }
        }

        try (PrintWriter out = new PrintWriter(appData+"\\VolCal.txt")) {
            for(String line : text){
                out.println(line);
            }
        }
        catch(Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteShape(IShape shape){
        ArrayList<String> shapes = getFile();

        if (shape instanceof Cube){
            Cube s = (Cube)shape;
            String cubeString = String.format("Cube|%s|%s|%s", "" + s.getLength(), "" + s.getHeight(), "" + s.getWidth());
            shapes.remove(cubeString);
        }
        if (shape instanceof Sphere){
            Sphere s = (Sphere)shape;
            String sphereString = String.format("Sphere|%s", ""+s.getRadius());
            shapes.remove(sphereString);
        }
        if (shape instanceof Cylinder){
            Cylinder s = (Cylinder) shape;
            String cylinderString = String.format("Cylinder|%s|%s", ""+s.getHeight(), ""+s.getRadius());
            shapes.remove(cylinderString);

        }
        if (shape instanceof HollowCylinder){
            HollowCylinder s = (HollowCylinder) shape;
            String hollowCylinderString = String.format("HolleCylinder|%s|%s|%s", ""+s.getHeight(), ""+s.getRadius1(), s.getRadius2());
            shapes.remove(hollowCylinderString);

        }
        if (shape instanceof TruncatedCone){
            TruncatedCone s = (TruncatedCone) shape;
            String truncatedConeString = String.format("TruncatedCone|%s|%s|%s", ""+s.getHeight(), ""+s.getRadius1(), s.getRadius2());
            shapes.remove(truncatedConeString);
        }
        save();

    }

    @Override
    public ArrayList<String> getFile(){
        ArrayList<String> text = new ArrayList<>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(appData + "\\VolCal.txt"));

            String line;
            while ((line = bufferreader.readLine()) != null) {
                    text.add(line);
            }

        }  catch (IOException ex) {
            ex.printStackTrace();
        }

        return text;
    }

    @Override
    public Vector<IShape> loadFile(){
        Vector<IShape> vector = new Vector<>();

        for (String line : getFile()){
            String[] arr = line.split("\\|");
            switch(arr[0]){
                case "Cube":
                    //length = 1
                    //width = 3
                    //height = 2
                    Cube cube = new Cube(Double.parseDouble(arr[1]),Double.parseDouble(arr[3]),Double.parseDouble(arr[2]));
                    vector.add(cube);
                    break;
                case "Cylinder":
                    //height = 1
                    //radius = 2
                    Cylinder cylinder = new Cylinder(Double.parseDouble(arr[1]), Double.parseDouble(arr[2]));
                    vector.add(cylinder);
                    break;
                case "Sphere":
                    //radius = 1
                    Sphere sphere = new Sphere(Double.parseDouble(arr[1]));
                    vector.add(sphere);
                    break;
                case "HollowCylinder":
                    //height = 1
                    //radius = 2
                    //radius 2 = 3
                    HollowCylinder hollowCylinder = new HollowCylinder(Double.parseDouble(arr[1]), Double.parseDouble(arr[2]), Double.parseDouble(arr[3]));
                    vector.add(hollowCylinder);
                    break;
                case "TruncatedCone":
                    //height = 1
                    //radius = 2
                    //radius 2 = 3
                    TruncatedCone truncatedCone = new TruncatedCone(Double.parseDouble(arr[1]), Double.parseDouble(arr[2]), Double.parseDouble(arr[3]));
                    vector.add(truncatedCone);
                    break;
            }
        }
        return vector;
    }

    @Override
    public boolean checkConnection(){
        File f = new File(appData + "\\VolCal.txt");
        if(f.exists() && !f.isDirectory()) {
            return true;
        }
        else{
            try{
             boolean result =    f.createNewFile();
                return result;
            }
            catch(Exception e){

            }
        }
        return false;
    }
}
