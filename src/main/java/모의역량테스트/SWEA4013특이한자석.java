package 모의역량테스트;

import java.util.*;
import java.io.*;

public class SWEA4013특이한자석 {

    static int T, K, N;
    static boolean[] visited;
    static List<Magnet> magnets;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = 8;
        visited = new boolean[4];
        magnets = new ArrayList<>();
        for(int i=0; i<4; i++){
            magnets.add(new Magnet());
        }
        for(int tc = 1; tc <= T; tc++){
            K = Integer.parseInt(br.readLine());
            StringTokenizer st = null;

            for(int i = 0; i< 4; i++ ){
                magnets.get(i).list.clear();
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<8; j++){
                    magnets.get(i).add(Integer.parseInt(st.nextToken()));
                }
            }
//            print();
            for(int i=0; i<K; i++){
                if(i != 0) init();
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                idx--;
                if(dir == 1) magnets.get(idx).clockWise(idx);
                else magnets.get(idx).unClockWise(idx);

//                print();
            }


            System.out.println("#" + tc + " " + getValue());
        }

    }
    public static void init(){
        for(int i=0; i<4; i++){
            visited[i] = false;
        }
    }

    public static void print(){
        System.out.print("화살표 면 : ");
        for(int i=0; i<4; i++){
            if(magnets.get(i).getValue() == 0) System.out.print("N ");
            else System.out.print("S ");
        }
        System.out.println();

        System.out.print("겹치는 면 : ");
        for(int i=0; i<4; i++){
            if(magnets.get(i).getLeft() == 0) System.out.print("N ");
            else System.out.print("S ");

            if(magnets.get(i).getRight() == 0) System.out.print("N ");
            else System.out.print("S ");
        }
        System.out.println();

        for(int i=0; i<4; i++){
            System.out.print(i+"번 자석: ");
            for(Integer v : magnets.get(i).list){
                if(v == 0) System.out.print("N ");
                else System.out.print("S ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    private static int getValue() {
        int sum = 0;
        for(int i=0; i<4; i++){
            sum += (magnets.get(i).getValue() << i);
        }
        return sum;
    }



    public static class Magnet{
        List<Integer> list = new ArrayList<>();

        public void clockWise(int idx){
            Integer prevRight = this.getRight();
            Integer prevLeft = this.getLeft();
            Integer remove = list.remove(N - 1);
            list.add(0, remove);
            visited[idx] = true;

            if(idx != 3 && !visited[idx + 1]) {
                Integer left = magnets.get(idx + 1).getLeft();
                if(prevRight != left) magnets.get(idx+1).unClockWise(idx+1);
            }
            if(idx != 0 && !visited[idx - 1]){
                Integer right = magnets.get(idx - 1).getRight();
                if(prevLeft != right) magnets.get(idx-1).unClockWise(idx-1);
            }
        }

        public void unClockWise(int idx){
            Integer prevRight = this.getRight();
            Integer prevLeft = this.getLeft();
            Integer remove = list.remove(0);
            list.add(remove);
            visited[idx] = true;

            if(idx != 3 && !visited[idx + 1]) {
                Integer left = magnets.get(idx + 1).getLeft();
                if(prevRight != left) magnets.get(idx+1).clockWise(idx+1);
            }
            if(idx != 0 && !visited[idx - 1]){
                Integer right = magnets.get(idx - 1).getRight();
                if(prevLeft != right) magnets.get(idx-1).clockWise(idx-1);
            }

        }
        public Integer getLeft(){
            return list.get(N-2);
        }
        public Integer getRight(){
            return list.get(2);
        }

        public Integer getValue(){
            return list.get(0);
        }

        public void add(Integer val){
            this.list.add(val);
        }
    }
}
