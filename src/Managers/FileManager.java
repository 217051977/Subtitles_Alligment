package Managers;

import java.io.File;

public class FileManager {
    File file;

    public void setFile(String filePath) {
        this.file = new File(filePath);
    }

    public boolean checkIfFileExists() {
        return file.exists();
    }
}
