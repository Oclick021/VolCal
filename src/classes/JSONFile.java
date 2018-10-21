package classes;

import interfaces.IFile;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class JSONFile implements IFile {

    private String appData;
    private String type;
    private String shape;
    private JSONArray shapes;

    public JSONFile(String _type, String _shape){
        shapes = getFile();
        shape = _shape;
        type = _type;
        appData = System.getenv("APPDATA");
    }

    @Override
    public void save(){
        if (!checkConnection()){
            System.err.println("Got an exception! ");
            System.err.println("Could not find file.");
            return;
        }
        String[] shapeArray = shape.split("|");
        JSONObject obj = new JSONObject();

        switch (type){
            case "Cube":
                obj.put("Shape", "Cube");
                obj.put("Length", shapeArray[1]);
                obj.put("Height", shapeArray[2]);
                obj.put("Width", shapeArray[3]);
                break;
            case "Sphere":
                obj.put("Shape", "Sphere");
                obj.put("Radius", shapeArray[1]);
                break;
            case "Cylinder":
                obj.put("Shape", "Cylinder");
                obj.put("Height", shapeArray[1]);
                obj.put("Radius", shapeArray[2]);
                break;
        }

        shapes.add(obj);

        //Write JSON file
        try (FileWriter file = new FileWriter(appData+"VolCal.json")) {
            file.write(shapes.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONArray getFile(){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(appData+"VolCal.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray shapes = (JSONArray) obj;

            return shapes;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkConnection(){
        File f = new File(appData + "VolCal.json");
        if(f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }

}
