package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day_template {
    public static void day_template() {
        try {
            File file = new File("");
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()) {
                String data = scan.nextLine();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
