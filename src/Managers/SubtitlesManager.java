package Managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Scanner;

public class SubtitlesManager {
    File file;

    public void getFile(String drive, String path, String name) {
        file = new File(drive + ":\\" + path + "\\" + name);
        System.out.println("Existes: " + file.exists());
    }

    public String UpdateFile() throws FileNotFoundException {
        String fileContent = "";
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine()) {
            do {
                fileContent += getTimeLine(scanner.nextLine());
            } while (scanner.hasNextLine());
        }
        return fileContent;
    }

    private String getTimeLine(String line) {
        String[] lineParts = line.split(":");
        if (lineParts.length > 3) {
            return line + "\n";
        } else {
            return "";
        }
    }
}
