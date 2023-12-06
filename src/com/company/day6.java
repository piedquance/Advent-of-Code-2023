package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class day6 {
    public static void day6() {
        String line1 = "";
        String line2 = "";
        try {
            File file = new File("input/6.txt");
            Scanner scan = new Scanner(file);
            line1 = scan.nextLine();
            line2 = scan.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long b = parser(line1);
        long c = parser(line2);
        double d = Math.sqrt(Math.pow(b,2) - 4*c);

        double x1 = (-b + d) / (-2);
        double x2 = (-b - d) / (-2);
        
        System.out.println(Math.floor(x2) - Math.floor(x1));
        double x = Math.floor(x2) - Math.floor(x1);
        System.out.println(BigDecimal.valueOf(x));


    }

    public static long parser(String s) {
        long result = 0;
        String acc = "";
        String nums = "1234567890";
        int flag = 0;
        for(int n = 0; n < s.length(); n++) {
            if(nums.contains(s.charAt(n) + "")) {
                flag = 0;
                //acc = "";
                while(nums.contains(s.charAt(n+flag) + "")) {
                    acc += s.charAt(n+flag);
                    flag++;
                    if(n+flag >= s.length()) break;
                }
                n += flag;
            }
        }
        result = Long.parseLong(acc);
        return result;
    }
}
