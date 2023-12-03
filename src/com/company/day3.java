package com.company;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;

public class day3 {

    public static void day3() {
        int y = 0;
        int row = 0;
        ArrayList<HashMap<Integer, Boolean>> grid = new ArrayList<>(140);
        for(int x = 0; x < 140; x++) {
            grid.add(x, new HashMap<>());
        }

        LinkedList<Coordinate> coordinates = new LinkedList<>();
        String symbols = "~`!@#$%^&*()_+-={}[]:\"\'|\\<>,?/";
        String numbers = "0123456789";

        try {
            File myObj = new File("input3.test4.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                row = data.length();
               // System.out.print("Line: " + y + " ");

                for(int x = 0; x < data.length(); x++) {
                    if(new String(symbols).contains(data.charAt(x) + "")) {
                      //  System.out.print(data.charAt(x));
                        grid.get(y).put(x, true);
                    }
                    else if(new String(numbers).contains(data.charAt(x) + "")) {
                        String num = data.charAt(x) + "";
                        int offset = 1;
                        while(new String(numbers).contains(data.charAt(x + offset) + "")) {
                            num += data.charAt(x+offset);
                            //System.out.println(y+ " " + x + " " + offset + " " + row);
                            offset++;
                            if(x + offset == row) break;
                        }
                        int Inum = Integer.parseInt(num);
                        coordinates.add(new Coordinate(Inum, x, x+offset, y));
                        x += offset-1;
                    }
                }
                y++;
              //  System.out.println("");
            }
            myReader.close();
        } catch(FileNotFoundException e) {
            System.out.println("An error has occured");
            e.printStackTrace();
        }

        int count = 0;

        for(Coordinate c : coordinates) {
            int spread = c.end - c.start + 2;
            boolean touching = false;

            if(c.start != 0) {
                if(grid.get(c.row).get(c.start-1) != null) touching = true;
            }
            if(c.end != row) {
                if(grid.get(c.row).get(c.end) != null) touching = true;
            }
            if(c.row != 0) {
                for(int x = 0; x < spread; x++) {
                    if(c.start-1+x != -1 && c.start-1+spread != y+1) if(grid.get(c.row - 1).get(c.start-1+x) != null) touching = true;
                }
            }
            if(c.row != y-1) {
               // System.out.print("row: " + c.row + " val: " + c.value + " spread: " + (c.row+1) + " ");
                for(int x = 0; x < spread; x++) {
                   // System.out.print(" " + (c.start-1+x) + " ");
                    if(c.start-1+x != -1 && c.start-1+spread != y+1) if(grid.get(c.row + 1).get(c.start-1+x) != null) touching = true;
                }
               // System.out.println();
            }
            if(touching) {
               // System.out.println(c.value);
                count += c.value;
                c.touch = true;
            }
        }

        for(Coordinate c: coordinates) System.out.println(c.row + " " + c.value + " (" + c.start + ", " + c.end + ") " + c.touch);
        System.out.println(count);
    }

    public static class Coordinate {
        public Coordinate(int value, int start, int end, int row) {
            this.value = value;
            this.start = start;
            this.end = end;
            this.row = row;
            this.touch = false;
        }
        public int value;
        public int start;
        public int end;
        public int row;
        public boolean touch;

        @Override
        public String toString() {
            return value + " " + start + " " + end + " " + row;
        }
    }



}
