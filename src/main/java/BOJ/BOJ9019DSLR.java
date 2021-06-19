package BOJ;

import java.util.*;

public class BOJ9019DSLR {

    static int T, obj, target;
    static pos[] visited;
    public static class pos{
        String prev;
        char op;
        public pos(String prev, char op){
            this.prev = prev;
            this.op = op;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for(int tc=0; tc<T; tc++){
            obj = sc.nextInt();
            target = sc.nextInt();

            // 방문 배열 생성
            visited = new pos[10002];
            visited[obj] = new pos(String.valueOf(obj), ' ');
            process();
        }

    }

    // target 까지 찾아가는 경로 찾기
    private static void process() {

    }

    private static int go(int cur, int flag) {
       return cur;
    }

}
