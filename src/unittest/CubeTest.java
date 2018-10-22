package unittest;

import classes.Cube;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CubeTest {

    @Test
    void getVolume() {
        Cube cube = new Cube(10, 10, 10);
        assertEquals(1000.0, cube.getVolume(), "Volume must be 1000.0");

        cube = new Cube(1, 1, 1);
        assertEquals(1.0, cube.getVolume(), "Volume must be 1.0");

        cube = new Cube(48.6, 12.5, 32.5);
        assertEquals(19743.75, cube.getVolume(), "Volume must be 19743.75");
    }

    @Test
    void getCubesFromDB() {
        System.out.print(Cube.getCubesFromDB().toString());
    }
}