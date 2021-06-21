package BOJ;

import javax.swing.plaf.FontUIResource;
import java.util.*;

public class BOJ12886돌 {

    static Set<String> set;
    static Queue<String> q;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String[] nums = input.split(" ");
        int first = Integer.parseInt(nums[0]);     // 큰 수
        int second = Integer.parseInt(nums[1]); // 작은 수
        int third = Integer.parseInt(nums[2]);

        if(first == second && second == third){
            System.out.println(1);
        }
        else{
            if(process(input)) System.out.println(1);
            else System.out.println(0);
        }


    }

    private static boolean process(String input) {

        set = new HashSet<>();
        q = new LinkedList<>();
        q.add(input);
        set.add(input);

        while(!q.isEmpty()){
            String cur = q.poll();
            String[] nums = cur.split(" ");
            
            // 1. 첫번째 두번째
            if(game(nums, 0, 1, 2)) return true;
            // 2. 두번째 세번째
            if (game(nums, 1, 2, 0)) return true;
            // 3. 첫번째 세번째
            if (game(nums, 0, 2, 1)) return true;

        }


        return false;
    }

    private static boolean game(String[] nums, int obj, int target, int remain) {
        int first = Integer.parseInt(nums[obj]);     // 큰 수
        int second = Integer.parseInt(nums[target]); // 작은 수
        int third = Integer.parseInt(nums[remain]);

        if(second == first) return false;
        if(second > first){
            int swap = second;
            second = first;
            first = swap;
        }

        first -= second;
        second *= 2;

        if(first == second && second == third) return true;
        else{
            String newString = first +" " +second +" " +third;
            if(!set.contains(newString)) {
                set.add(newString);
                q.add(newString);
            }
        }

        return false;
    }
}
