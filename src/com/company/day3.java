package com.company;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class day3 {

    public static void day3() {
        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
            }
            myReader.close();
        } catch(FileNotFoundException e) {
            System.out.println("An error has occured");
            e.printStackTrace();
        }
    }
}
