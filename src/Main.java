import Managers.DirectoryManager;
import Managers.SubtitlesManager;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static SubtitlesManager subtitlesManager = new SubtitlesManager();
    private static DirectoryManager directoryManager = new DirectoryManager();
    private static String fileName = "";

    public static void main(String[] args) throws FileNotFoundException {
        switch (args.length) {

        }
        Scanner scanner = new Scanner(System.in);
        String drive = scanner.nextLine();
        setDrive(drive);
        String path = scanner.nextLine();
        setPath(path);
        String name = scanner.nextLine();

        subtitlesManager.getFile(drive, path, name);
        System.out.println(subtitlesManager.UpdateFile());
    }

    private static void setDrive(String drive) {
        directoryManager.setDrive(drive);
    }

    private static void setPath(String path) {
        directoryManager.setPath(path);
    }
}
