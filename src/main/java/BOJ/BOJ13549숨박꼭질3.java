package BOJ;
import java.util.*;

public class BOJ13549숨박꼭질3 {

    static int from, to, ans;
    static int[] dis;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        from = sc.nextInt();
        to = sc.nextInt();
        ans = 0;
        dis = new int[100001];

        if(from == to) System.out.println(ans);
        else {
            System.out.println(bfs());
        }
    }

    private static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(from);
        dis[from] = 1;

        while(!q.isEmpty()){
            Integer now = q.poll();

            Integer next = now - 1;
            if(next >= 0 && dis[next] == 0){
                dis[next] = dis[now]+1;
                q.add(next);
                if(next == to) return dis[next]-1;
            }

            next = now + 1;
            if(next <= 100000 && dis[next] == 0){
                dis[next] = dis[now]+1;
                q.add(next);
                if(next == to) return dis[next]-1;
            }

            next = now * 2;
            if(next <= 100000 && dis[next] == 0){
                dis[next] = dis[now];
                q.add(next);
                if(next == to) return dis[next]-1;
            }
        }

        return -1;
    }
}
