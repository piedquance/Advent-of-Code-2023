package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day7 {
    public static void day7() {

        ArrayList<Hand> deck = new ArrayList<>();

        try {
            File file = new File("input/7.txt");
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()) {
                String data = scan.nextLine();
                String[] values = data.split(" ");
                deck.add(new Hand(values[0], Integer.parseInt(values[1])));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        deck.sort(new Comparator<Hand>() {
            @Override
            public int compare(Hand o1, Hand o2) {
                return o1.compareTo(o2);
            }
        });

        int result = 0;
        for(int n = 0; n < deck.size(); n++) {
            System.out.println(deck.get(n));
            result += (n+1) * deck.get(n).bid;
        }
        System.out.println(result);


    }

    public static class Hand implements Comparable{
        public Hand(String hand, int bid) {
            this.type = getType(hand);
            this.bid = bid;
            this.hand = hand;
            //System.out.println(this);
        }
        public int type;
        public int bid;
        public String hand;

        public static int getType(String s) {
            int type = 0;
            char firstCard = '0';
            char secondCard = '0';
            int jokers = 0;
            HashMap<Character, Integer> store = new HashMap<>();

            for(int n  = 0; n < s.length(); n++) {
                char c = s.charAt(n);
                if(c == 'J') jokers++;
                int x = store.getOrDefault(c, -1);

                if(x == -1) store.put(c, 1);
                else store.replace(c, store.get(c)+1);

                if(store.get(c) == 2 && firstCard == '0') firstCard = c;
                if(store.get(c) == 2 && firstCard != '0' && firstCard != c) secondCard = c;

            }

            if(store.containsValue(5)) {
                type = 7;
            } else if(store.containsValue(4)) {
                type = 6;
            } else if(store.containsValue(3)) {
                if(store.containsValue(2)) {
                    type = 5;
                } else type = 4;
            } else if(store.containsValue(2)) {
                if(store.get(firstCard) == store.get(secondCard)) type = 3;
                else type = 2;
            } else  {
                type = 1;
            }
            int adder = 0;
            String map = type + "" + jokers;
            System.out.println(map + " " + s);
            switch (map) {
                case "11":
                    adder = 1;
                    break;
                case "12":
                    adder = 3;
                    break;
                case "13":
                    adder = 5;
                    break;
                case "14":
                    adder = 6;
                    break;
                case "21":
                    adder = 2;
                    break;
                case "22":
                    adder = 2;
                    break;
                case "31":
                    adder = 2;
                    break;
                case "32":
                    adder = 3;
                    break;
                case "41":
                    adder = 2;
                    break;
                case "43":
                    adder = 2;
                    break;
                case "52":
                    adder = 2;
                    break;
                case "53":
                    adder = 2;
                    break;
                case "61":
                    adder = 1;
                    break;
                case "64":
                    adder = 1;
                    break;
                default: break;
            }
            //System.out.println(adder);
            return type + adder;
        }

        @Override
        public String toString() {
            return hand + " " + type + " " + bid;
        }

        @Override
        public int compareTo(Object o) {
            String compare = "J23456789TQKA";
            Hand secondCard = (Hand) o;

            int m = type - secondCard.type;
            if(m != 0) return m;
            else {
                for (int n = 0; n < 5; n++) {
                    int a = compare.indexOf(hand.charAt(n));
                    int b = compare.indexOf(secondCard.hand.charAt(n));
                    if (a > b) return 1;
                    else if (b > a) return -1;
                }
            }

            return 0;
        }
    }
}
