package unittest;

import classes.Cube;
import classes.JSONFile;
import org.junit.jupiter.api.Test;

class JSONFileTest {
    @Test
    void save() {
        //Cube with a length,width and height of 10
        String saveString = "Cube|10.0|10.0|10.0";
        JSONFile file = new JSONFile("Cube",saveString);
        file.save();
    }

    @Test
    void loadFile() {
        JSONFile file = new JSONFile();
        System.out.print(file.loadFile().toString());
    }

    @Test
    void deleteShape() {
        Cube cube = new Cube(10,10,10);
        JSONFile file = new JSONFile();
        file.deleteShape(cube);
    }
}