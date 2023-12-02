package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day2 {
    public static void day2() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();



                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

