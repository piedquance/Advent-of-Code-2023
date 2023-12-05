package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Object.*;


public class day4 {
    public static void day4() {

        int result = 0;
        int row = 0;
        ArrayList<Integer> wins = new ArrayList<>();
        ArrayList<Integer> cards = new ArrayList<>();

        try {
            File file = new File("4.txt");
            Scanner reader = new Scanner(file);
            while(reader.hasNext()) {
                String data = reader.nextLine();
               // System.out.println(data);
                ArrayList<Integer> nums = parser(data);
                nums.remove(0);
                Collections.sort(nums);
               // for(int n : nums)  System.out.print("[" + n + "]" + " ");
               // System.out.println("");

                int count = 0;
                for(int x = 0; x < nums.size(); x++) {
                    if(x+1 < nums.size()) {
                        if (nums.get(x) == nums.get(x + 1)) {
                            //System.out.println("{" + nums.get(x) + "}");
                            count ++;
                        }
                    }
                }
                wins.add(count);
                cards.add(1);

                row++;
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        for(int n = 0; n < row; n++) {
            //System.out.println(wins.get(n) + " " + cards.get(n));
            for(int m = 1; m <= wins.get(n); m++) {
                cards.set(n+m, cards.get(n+m) + cards.get(n));
            }
        }

        for(int n: cards) {
            result += n;
        }

        System.out.println(result);

    }

    public static ArrayList<Integer> parser(String str) {
        String nums = "0123456789";
        ArrayList<Integer> array = new ArrayList<>();
        int value;
        String valueString;
        for(int n = 0; n < str.length(); n++) {
            valueString = "";
            int count = 0;
                while (nums.contains(str.charAt(n + count) + "")) {
                    valueString += str.charAt(n + count);
                    count++;
                   // System.out.println(n + count + " " + str.length());
                    if(n + count >= str.length()) break;
                }

            n += count;
            if(valueString != "") {
                value = Integer.parseInt(valueString);
                array.add(value);
            }
        }

        return array;
    }
}
