package com.company;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;

public class day3 {

    public static void day3() {
        int y = 0;
        ArrayList<HashMap<Integer, Boolean>> grid = new ArrayList<>(140);
        LinkedList<Coordinate> coordinates = new LinkedList<>();
        String[] symbols = {"+", "#", "@", "$", "-", "*", "/", "=", "&"};
        String[] numbers = {"1","2","3","4","5","6","7","8","9","0"};

        try {
            File myObj = new File("input2.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                y++;
                for(int x = 0; x < data.length(); x++) {
                    if(Arrays.asList(symbols).contains(data.charAt(x))) {

                    }
                    else if(Arrays.asList(numbers).contains(data.charAt(x))) {
                        String num = data.charAt(x) + "";
                        int offset = 1;
                        while(Arrays.asList(numbers).contains(data.charAt(x+offset))) {
                            offset++;
                            num += data.charAt(x+offset);
                        }
                        int Inum = Integer.parseInt(num);
                        coordinates.add(new Coordinate(Inum, x, x+offset));
                        x += offset;
                    }
                }

            }
            myReader.close();
        } catch(FileNotFoundException e) {
            System.out.println("An error has occured");
            e.printStackTrace();
        }
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
    }



}
