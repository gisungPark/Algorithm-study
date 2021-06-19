package BOJ;

import java.util.*;

public class BOJ9019DSLR {

    static int T;
    static obj[] visited;
    static int A, B;
    static char[] command = {'D', 'S', 'L', 'R'};
    public static class obj{
        int parents;
        char op;

        public obj(int parents, char op) {
            this.parents = parents;
            this.op = op;
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for(int tc=0; tc<T; tc++){
            visited = new obj[10000];
            A = sc.nextInt();
            B = sc.nextInt();

            process(A, B);
        }
    }


    private static void process(int from, int to) {

        Queue<Integer> q = new LinkedList<>();
        q.add(from);
        visited[from] = new obj(from, ' ');

        loop: while(!q.isEmpty()){
            int cur = q.poll();                 // 현재위치
            char op =' ';
            for(int i=0; i<4; i++){             // 다음위치
                int next = go(cur, i);
                if(visited[next] != null) continue;
                // 방문처리!!
                visited[next] = new obj(cur, command[i]);
                q.add(next);

                if(next == to){
                    List<Character> list = new ArrayList<>();
                    int parents = next;
                    // 부모가 출발점이 아닐때까지,
                    while(parents != from){
                        list.add(visited[parents].op);
                        parents = visited[parents].parents;
                    }
                    for (int k = list.size() - 1; k >= 0; k--) {
                        System.out.print(list.get(k));
                    }
                    System.out.println();
                    break loop;
                }

            }

        }
    }

    private static int go(int pos, int flag) {
        switch (flag){
            case 0:
                pos *= 2;
                if(pos > 9999) pos %= 10000;
                break;
            case 1:
                if(pos == 0) pos = 9999;
                else pos--;
                break;
            case 2:
                pos = pushLeft(convertToString(pos));
                break;
            case 3:
                pos = pushRight(convertToString(pos));
                break;
        }
        return pos;
    }

    private static int pushRight(String cur) {
        int d1 = cur.charAt(0) - '0';
        int d2 = cur.charAt(1) - '0';
        int d3 = cur.charAt(2) - '0';
        int d4 = cur.charAt(3) - '0';
        return (((d4 * 10) + d1) * 10 + d2) * 10 + d3;
    }

    private static int pushLeft(String cur) {
        int d1 = cur.charAt(0) - '0';
        int d2 = cur.charAt(1) - '0';
        int d3 = cur.charAt(2) - '0';
        int d4 = cur.charAt(3) - '0';
        return (((d2 * 10) + d3) * 10 + d4) * 10 + d1;
    }

    private static String convertToString(int N){
        if(N>999) return String.valueOf(N);
        else if(N>99) return "0"+N;
        else if(N>9) return "00"+N;
        else return "000"+N;
    }


}
