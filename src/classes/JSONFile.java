package classes;

import interfaces.IFile;
import interfaces.IShape;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Vector;

public class JSONFile implements IFile {

    private String appData;
    private String type;
    private String shape;
    private JSONArray shapes;

    public JSONFile(String _type, String _shape){
        appData = System.getenv("APPDATA");
        shapes = getFile();
        shape = _shape;
        type = _type;
    }

    public JSONFile(){
        appData = System.getenv("APPDATA");
    }

    @Override
    public void save(){
        if (!checkConnection()){
            System.err.println("Got an exception! ");
            System.err.println("Could not find file.");
            return;
        }
        String[] shapeArray = shape.split("\\|");
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
            case "HollowCylinder":
                obj.put("Shape", "HollowCylinder");
                obj.put("Height", shapeArray[1]);
                obj.put("Radius1", shapeArray[2]);
                obj.put("Radius2", shapeArray[3]);
                break;
            case "TruncatedCone":
                obj.put("Shape", "TruncatedCone");
                obj.put("Height", shapeArray[1]);
                obj.put("Radius1", shapeArray[2]);
                obj.put("Radius2", shapeArray[3]);
                break;
        }
        if (!shapes.contains(obj)){
            shapes.add(obj);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(appData+"\\VolCal.json")) {
                file.write(shapes.toJSONString());
                file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONArray getFile(){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(appData+"\\VolCal.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray shapes = (JSONArray) obj;

            return shapes;

        } catch (FileNotFoundException e) {
            try{
                String path = appData + "VolCal.json";
                File file = new File(path);
                file.createNewFile();
            }catch(Exception ex){

            }
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }
    @Override
    public Vector<IShape> loadFile(){
        Vector<IShape> vector = new Vector<>();
        JSONArray arr = getFile();

        for (Object obj: arr) {
             if (obj instanceof JSONObject){
                 JSONObject jo = (JSONObject)obj;
                 switch (jo.size()){
                     case 2:
                         Sphere sphere = new Sphere(Double.parseDouble(jo.get("Radius").toString()));
                         vector.add(sphere);
                         break;
                     case 3:
                         Cylinder cylinder = new Cylinder(Double.parseDouble(jo.get("Height").toString()), Double.parseDouble(jo.get("Radius").toString()));
                         vector.add(cylinder);
                         break;
                     case 4:
                         Cube cube = new Cube(Double.parseDouble(jo.get("Length").toString()),Double.parseDouble(jo.get("Width").toString()),Double.parseDouble(jo.get("Height").toString()));
                         vector.add(cube);
                         break;
                     case 5:
                         if (jo.get("Name").toString() == "TruncatedCone"){
                             TruncatedCone cone = new TruncatedCone(Double.parseDouble(jo.get("Height").toString()), Double.parseDouble(jo.get("Radius1").toString()), Double.parseDouble(jo.get("Radius2").toString()));
                             vector.add(cone);
                         }
                         else if(jo.get("Name").toString() == "HollowCylinder"){
                             HollowCylinder hCylinder = new HollowCylinder(Double.parseDouble(jo.get("Height").toString()), Double.parseDouble(jo.get("Radius1").toString()), Double.parseDouble(jo.get("Radius2").toString()));
                            vector.add(hCylinder);
                         }
                 }
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
