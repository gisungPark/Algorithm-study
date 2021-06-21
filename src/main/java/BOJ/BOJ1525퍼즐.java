package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BOJ1525퍼즐 {

    static int ans;
    static int[] dx = {-1, 1, -3, 3};
    static Set<String> set;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String map = "";
        for(int i=0; i<3; i++){
            map += br.readLine();
        }

        set = new HashSet<>();
        map = map.replace(" ", "");
        set.add(map);

        process(map);

    }

    private static int process(String start) {

        return -1;
    }

    private static void swapChar(String map, int obj, int target) {

    }

    public static class Item{
        int x;
        String map;

        public Item(int x, String map) {
            this.x = x;
            this.map = map;
        }
    }
}
