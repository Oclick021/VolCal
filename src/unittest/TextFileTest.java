package classes;

import static org.junit.jupiter.api.Assertions.*;

class TextFileTest {

    @org.junit.jupiter.api.Test
    void save() {
        //Cube with a length,width and height of 10
        String saveString = "Cube|10|10|10";
        TextFile file = new TextFile(saveString);
        file.save();
    }

    @org.junit.jupiter.api.Test
    void loadFile() {
        TextFile file = new TextFile();
        System.out.print(file.loadFile().toString());
    }
}