package interfaces;

import java.util.Vector;

public interface IFile {
    void save();
    Object getFile();
    Vector<IShape> loadFile();
    boolean checkConnection();
}
