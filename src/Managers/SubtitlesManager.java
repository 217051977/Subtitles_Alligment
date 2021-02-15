package Managers;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SubtitlesManager {
    File file;
    List<Integer> timeLines = new ArrayList<>();

    public void getFile(String drive, String path, String name) {
        file = new File(drive + ":\\" + path + "\\" + name);
        System.out.println("Existes: " + file.exists());
    }

    public void readFile() throws FileNotFoundException {
        int i = 0;
        Scanner reader = new Scanner(file);
        String line;
        while (reader.hasNextLine()){
            line = reader.nextLine();
            if (!getTimeLine(line, 0, 0, 0).equals("")) {
                timeLines.add(i);
            }
            i++;
        }
    }

    public void printTimeLines() throws FileNotFoundException {
        int i = 0;
        Scanner reader = new Scanner(file);
        String line;
        int lineIndex = 0;
        while (reader.hasNextLine()){
            line = reader.nextLine();
            if (i == timeLines.get(lineIndex)) {
                System.out.println(line);
                lineIndex++;
            }
            i++;
        }
    }

    public String UpdateFile(int h, int m, int s) throws IOException {
        String fileContent = "";
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine()) {
            do {
                fileContent += getTimeLine(scanner.nextLine(), h, m, s);
            } while (scanner.hasNextLine());
        }
        scanner.close();
        if (fileContent.equals("")) {
            return "Time line not found!";
        }
        FileWriter writer = new FileWriter(file);

        writer.write(fileContent);
        writer.close();

        return fileContent;
    }

    private String getTimeLine(String line, int h, int m, int s) {
        String[] lineParts = line.split(" --> ");
        if (lineParts.length > 1) {
            return getTimeComponents(lineParts, h, m, s) + "\n";
        } else {
            return line + "\n";
        }
    }

    private String getTimeComponents(String[] lineParts, int h, int m, int s) {
        String[] timeS = lineParts[0].split(":");
        String[] timeE = lineParts[1].split(":");
        return addTime(timeS, h,m, s) + " --> " + addTime(timeE, h, m, s);
    }

    private String addTime(String[] time, int hour, int min, int sec) {
        String[] sParts = time[2].split(",");
        int[] s = correctTime(Integer.parseInt(sParts[0]) + sec);
        int[] m = correctTime(Integer.parseInt(time[1]) + min + s[1]);
        int h = Integer.parseInt(time[0]) + hour + m[1];
        String ret = "";

        if (h < 10) {
            ret += "0" + h + ":";
        } else {
            ret += ret + ":";
        }

        if (m[0] < 10) {
            ret += "0" + m[0] + ":";
        } else {
            ret += m[0] + ":";
        }

        if (s[0] < 10) {
            ret += "0" + s[0] + "," + sParts[1];
        } else {
            ret += s[0] + "," + sParts[1];
        }
        return ret;
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
