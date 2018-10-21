package interfaces;

public interface IShape {
    double getVolume();
    void saveOnDB();
    void saveAsJson();
    void saveAsText();
}
