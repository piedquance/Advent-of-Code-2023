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
        //System.out.println(input);

        String[] inputArray = input.split("\\|");

        String[] temp = inputArray[0].split(":")[1].split(" ");
        ArrayList<String> temp2 = new ArrayList<>();
        for(String n : temp) temp2.add(n);
        temp2.remove(0);

        ArrayList<Seed> seeds = new ArrayList<>(temp2.size());


        for(int n = 0; n < temp2.size(); n+=2) {
            seeds.add(new Seed(Long.parseLong(temp2.get(n)), Long.parseLong(temp2.get(n+1))));
        }

        ArrayList<ArrayList<Seed>> rank = new ArrayList<>(7);
        rank.add(seeds);
        for(int n = 1; n < 7; n++) rank.add(new ArrayList<Seed>());


        ArrayList<ArrayList<Unit>> map = new ArrayList<>(7);
        for(int n = 0; n < 7 ; n++) {
            map.add(n, new ArrayList<Unit>());
        }

        int m = 3;
        for(int n = 0; n < 7; n++) {
            //System.out.println(n);
            int c = 0;
            while(inputArray[m+c] != "") {
                //System.out.println(inputArray[m+c]);

                String[] unitStrings = inputArray[m+c].split(" ");
                long[] units = new long[3];
                for(int x = 0; x < 3; x++) units[x] = Long.parseLong(unitStrings[x]);

                map.get(n).add(new Unit(units[1], units[1] + units[2], units[0], units[2]));

                c++;
                if(m+c == inputArray.length) break;
            }
            m += 2 + c;
        }

        //System.out.println("=====");

        for(ArrayList<Unit> list : map) {
            list.sort(Comparator.naturalOrder());
        }


        for(int x = 0; x < 7; x++) {
            ArrayList<Unit> list = map.get(x);

            if(list.get(0).src_start != 0) {
                map.get(0).add(0, new Unit(0, list.get(0).src_start, 0, list.get(0).src_start));
            }

            for(Unit unit: list) {

            }
        }



        for(int x = 0; x < 7; x++) {
            for(int y = 0; y < rank.get(x).size(); y++) {
                Seed seed = rank.get(x).get(y);
                Unit unitstart = new Unit(0,0,0,0);
                Unit unitend = new Unit(0,0,0,0);
                ArrayList<Unit> units = new ArrayList<>();

                int z = 0;
                for(Unit u: map.get(x)) {

                    //for the list
                    if(seed.start < u.src_start && seed.end > u.src_end) {
                        units.add(u);
                    }

                    //less than smallest unit
                    if(seed.start < map.get(x).get(0).src_start) {
                      //  System.out.print("[" + seed + " " + map.get(x).get(0).src_start + "]");
                        unitstart = new Unit(0,  map.get(x).get(0).src_start, 0,  map.get(x).get(0).src_start);
                        break;
                    }
                    //bigger than biggest unit
                    if(seed.start > map.get(x).get(map.get(x).size()-1).src_end) {
                      //  System.out.print("[" + map.get(x).get(map.get(x).size()-1).src_end + " " + seed + "]");
                        break;
                    }
                    //inside unit
                    if(seed.start >= u.src_start && seed.start < u.src_end) {
                        unitstart = u;
                      //  System.out.print("[" + u.src_start + " " + seed + " " + u.src_end + "]");
                        break;
                    }
                    //between two units
                    else if(seed.start > map.get(x).get(y).src_end && seed.start < map.get(x).get(y+1).src_start) {
                       // System.out.print("[" +  map.get(x).get(y).src_end + " " + seed + " " + map.get(x).get(y+1).src_start + "]");
                        break;
                    }
                    y++;
                }
            }
        }

//
//                int y = 0;
//                for(Unit u: map.get(x)) {
//                    if(seed < map.get(x).get(0).src_start) {
//                        System.out.print("[" + seed + " " + map.get(x).get(0).src_start + "]");
//                        break;
//                    }
//                    if(seed > map.get(x).get(map.get(x).size()-1).src_end) {
//                        System.out.print("[" + map.get(x).get(map.get(x).size()-1).src_end + " " + seed + "]");
//                        break;
//                    }
//                    if(seed >= u.src_start && seed < u.src_end) {
//                        unit = u;
//                        System.out.print("[" + u.src_start + " " + seed + " " + u.src_end + "]");
//                        break;
//                    } else if(seed > map.get(x).get(y).src_end && seed < map.get(x).get(y+1).src_start) {
//                        System.out.print("[" +  map.get(x).get(y).src_end + " " + seed + " " + map.get(x).get(y+1).src_start + "]");
//                        break;
//                    }
//                    y++;
//                }
//
//                System.out.print(seed + " > " );
//                seed = seed - unit.src_start + unit.dest;
//
//            }
//            System.out.println(seed);
//            seeds[n] = seed;
//        }
//
//        ArrayList<Long> Seeds = new ArrayList<>();
//        for(long s: seeds) Seeds.add(s);
//
//        Collections.sort(Seeds);
//        System.out.println(Seeds.get(0));

    }

    public static class Unit implements Comparable {
        public Unit(long src_start, long src_end, long dest, long range) {
            this.src_start = src_start;
            this.src_end = src_end;
            this.dest = dest;
            this.range = range;
        }
        public long src_start;
        public long src_end;
        public long dest;
        public long range;

        @Override
        public String toString() {
            return "[" + src_start + "," + src_end + "] " + dest + " " + range;
        }

        @Override
        public int compareTo(Object o) {
            int res = 0;
            Unit a = ((Unit) o);
            if(src_start > a.src_start) res = 1;
            else if(src_start < a.src_start) res = -1;
            else res = 0;
            return res;
        }
    }

    public static class Seed {
        public Seed(long start, long end) {
            this.start = start;
            this.end = end;
        }
        long start;
        long end;

        @Override
        public String toString() {
            return start + " " + end;
        }
    }

}
