package Managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Scanner;

public class SubtitlesManager {
    File file;

    public void getFile(String drive, String path, String name) {
        file = new File(drive + ":\\" + path + "\\" + name);
        System.out.println("Existes: " + file.exists());
    }

    public String UpdateFile(int h, int m, int s) throws FileNotFoundException {
        String fileContent = "";
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine()) {
            do {
                fileContent += getTimeLine(scanner.nextLine(), h, m, s);
            } while (scanner.hasNextLine());
        }
        if (fileContent.equals("")) {
            return "Time line not found!";
        }
        return fileContent;
    }

    private String getTimeLine(String line, int h, int m, int s) {
        String[] lineParts = line.split(" --> ");
        if (lineParts.length > 1) {
            return getTimeComponents(lineParts, h, m, s) + "\n";
        } else {
            return "";
        }
    }

    private String getTimeComponents(String[] lineParts, int h, int m, int s) {
        String[] timeS = lineParts[0].split(":");
        String[] timeE = lineParts[1].split(":");
        return addTime(timeS, 0,0, 0) + " --> " + addTime(timeE, h, m, s);
    }

    private String addTime(String[] time, int hour, int min, int sec) {
        String[] sParts = time[2].split(",");
        int[] s = correctTime(Integer.parseInt(sParts[0]) + sec);
        int[] m = correctTime(Integer.parseInt(time[1]) + min + s[1]);
        int h = Integer.parseInt(time[0]) + hour + m[1];
        return h + ":" + m[0] + ":" + s[0] + "," + sParts[1];
    }

    private int[] correctTime(int time) {
        int res = time;
        int extra = 0;
        boolean done = false;

        while (!done) {
            if (res > 60) {
                extra++;
                res -= 60;
            } else {
                done = true;
            }
        }
        int[] ret = {res, extra};
        return ret;
    }
}
