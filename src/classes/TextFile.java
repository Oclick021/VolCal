package classes;

import interfaces.IFile;

import java.io.File;
import java.io.PrintWriter;
import java.time.temporal.TemporalQueries;

public class TextFile implements IFile {
    private String appData;
    private String saveString;

    public TextFile(String _saveString){
        saveString = _saveString;
        appData = System.getenv("APPDATA");
    }
    @Override
    public void save(){
        if (!checkConnection()){
            System.err.println("Got an exception! ");
            System.err.println("Could not find file.");
            return;
        }


        try (PrintWriter out = new PrintWriter(appData+"VolCal.txt")) {
            out.println(saveString);
        }
        catch(Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
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
