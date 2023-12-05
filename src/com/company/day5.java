package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day5 {

    public static void day5() {
        String input = "";

        try {
            File file = new File("5.1.txt");
            Scanner scan = new Scanner(file);
            while(scan.hasNext()) {
                input += scan.nextLine() + "|";
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(input);

        String[] inputArray = input.split("\\|");

        String[] seedsString = inputArray[0].split(":")[1].split(" ");
        long[] seeds = new long[100];
        for(int n = 1; n < seedsString.length; n++) {
            seeds[n-1] = Integer.parseInt(seedsString[n]);
            System.out.println(seedsString[n]);
        }
        System.out.println("=====");

        ArrayList<ArrayList<Unit>> map = new ArrayList<>();
        int m = 3;
        for(int n = 0; n < 7; n++) {
            int c = 0;
            while(inputArray[m+c] != "") {
                System.out.println(inputArray[m+c]);
                c++;
                if(m+c == inputArray.length) break;
            }
            m += 3;
        }

    }

    public static class Unit {
        public Unit(int src, int dest, int range) {
            this.src = src;
            this.dest = dest;
            this.range = range;
        }
        public int src;
        public int dest;
        public int range;
    }


}
