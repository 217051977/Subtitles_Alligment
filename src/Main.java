import Managers.DirectoryManager;
import Managers.SubtitlesManager;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final SubtitlesManager subtitlesManager = new SubtitlesManager();
    private static final DirectoryManager directoryManager = new DirectoryManager();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Set drive: ");
        String drive = scanner.nextLine();
        setDrive(drive);
        System.out.print("Set path: ");
        String path = scanner.nextLine();
        setPath(path);
        System.out.print("Set file name: ");
        String name = scanner.nextLine();
        System.out.print("Set time to add ->\nHours: ");
        int h = scanner.nextInt();
        System.out.print("Minutes: ");
        int m = scanner.nextInt();
        System.out.print("Seconds: ");
        int s = scanner.nextInt();

        subtitlesManager.getFile(drive, path, name);
//        subtitlesManager.UpdateFile(h, m, s);
        System.out.println(subtitlesManager.UpdateFile(h, m, s));
    }

    private static void setDrive(String drive) {
        directoryManager.setDrive(drive);
    }

    private static void setPath(String path) {
        directoryManager.setPath(path);
    }
}
