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
        if (!text.contains(saveString)){
            text.add(saveString);
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
    public ArrayList<String> getFile(){
        ArrayList<String> text = new ArrayList<>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(appData + "\\VolCal.txt"));

            String line;
            while ((line = bufferreader.readLine()) != null) {
                    text.add(line);
            }

        } catch (FileNotFoundException ex) {
            try{
                String path = appData + "VolCal.json";
                File file = new File(path);
                file.createNewFile();
            }catch(Exception e){

            }
            ex.printStackTrace();
        } catch (IOException ex) {
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
        File f = new File(appData + "\\VolCal.json");
        if(f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }
}
