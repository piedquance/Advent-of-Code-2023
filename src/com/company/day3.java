package com.company;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;

public class day3 {

    public static void day3() {
        int y = 0;
        ArrayList<HashMap<Integer, Boolean>> grid = new ArrayList<>(140);
        for(HashMap<Integer, Boolean> hm : grid) {

        }
        LinkedList<Coordinate> coordinates = new LinkedList<>();
        char[] symbols = {'+', '#', '@', '$', '-', '*', '/', '=', '&'};
        String numbers = "123456789";

        try {
            File myObj = new File("input3.test.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.print("Line: " + y + " ");

                for(int x = 0; x < data.length(); x++) {
                    if(new String(symbols).contains(data.charAt(x) + "")) {
                        System.out.print(data.charAt(x));
                        grid.get(y).put(x, true);
                    }
                    else if(new String(numbers).contains(data.charAt(x) + "")) {
                        String num = data.charAt(x) + "";
                        int offset = 1;
                        while(new String(numbers).contains(data.charAt(x + offset) + "")) {
                            num += data.charAt(x+offset);
                            offset++;
                        }
                        int Inum = Integer.parseInt(num);
                        coordinates.add(new Coordinate(Inum, x, x+offset));
                        x += offset;
                    }
                }
                y++;
                System.out.println("");
            }
            myReader.close();
        } catch(FileNotFoundException e) {
            System.out.println("An error has occured");
            e.printStackTrace();
        }

        for(Coordinate c : coordinates) {
            System.out.println(c);
        }
        System.out.println(grid.get(0).get(0));
    }

    public class Row {
        public Row() {

        }
    }

    public static class Coordinate {
        public Coordinate(int value, int start, int end) {
            this.value = value;
            this.start = start;
            this.end = end;
        }
        public int value;
        public int start;
        public int end;

        @Override
        public String toString() {
            return value + " " + start + " " + end;
        }
    }



}
