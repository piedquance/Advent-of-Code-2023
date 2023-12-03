package com.company;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day2 {
    public static void day2() {
        try {
            int count = 0;

            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int blue = 0; int red = 0; int green = 0;

                String[] dataArray = data.substring(5).split(": ", -2);
                String[] GrabArray = dataArray[1].split("; ", -2);

                for(String string : GrabArray) {
                    String[] ColorArray = string.split(", ", -2);
                    for(String string2 : ColorArray) {
                        String[] NumberArray = string2.split(" ", -2);
                        switch (NumberArray[1]) {
                            case "red":
                                if(Integer.parseInt(NumberArray[0]) > red) red = Integer.parseInt(NumberArray[0]);
                                break;
                            case "blue":
                                if(Integer.parseInt(NumberArray[0]) > blue) blue = Integer.parseInt(NumberArray[0]);
                                break;
                            case "green":
                                if(Integer.parseInt(NumberArray[0]) > green) green = Integer.parseInt(NumberArray[0]);
                                break;
                        }
                    }
                }

                System.out.println(dataArray[0]);
                int power = red * green * blue;
                count += power;
//                if(red <= 12 && green <= 13 && blue <= 14) {
//                    count += Integer.parseInt(dataArray[0]);
//                }
            }
            myReader.close();
            System.out.println(count);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}



