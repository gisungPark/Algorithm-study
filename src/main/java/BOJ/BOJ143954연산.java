package BOJ;

import java.util.*;

public class BOJ143954연산 {

    static int from, to;
    static String ans;
    static Set<Long> set;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        from = sc.nextInt();
        to = sc.nextInt();

        if(from == to) System.out.println(0);
        else {
            ans = "";
            set = new HashSet<>();
            BFS();
            if (ans.equals("")) System.out.println(-1);
            else System.out.println(ans);
        }
    }

    private static void BFS() {
        Queue<Operation> q = new LinkedList<>();
        q.add(new Operation(from, ""));
        set.add((long)from);


        while (!q.isEmpty()){
            Operation cur = q.poll();

            for (int i = 0; i < 4; i++) {
                Operation next = calcNext(cur, i);
                if(isVisit(next)) continue;
                if(next.num == (long)to) {
                    ans = next.op;
                    return;
                }
                set.add(next.num);
                q.add(next);
            }
        }
    }

    private static boolean isVisit(Operation next) {
        return set.contains(next.num);
    }

    private static Operation calcNext(Operation cur, int flag) {
        long num = cur.num;
        if (flag == 0) return new Operation(num*num, cur.op + "*");
        if (flag == 1) return new Operation(num+num, cur.op + "+");
        if (flag == 2) return new Operation(num-num, cur.op + "-");
        return num == 0 ? cur : new Operation(1, cur.op + "/");
    }

    static class Operation{
        long num;
        String op;

        public Operation(long num, String op){
            this.num = num;
            this.op = op;
        }
    }
}
