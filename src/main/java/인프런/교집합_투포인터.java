package 인프런;

import java.util.*;

/**
 5
 2 7 10 5 3
 5
 3 10 5 17 12
 */
public class 교집합_투포인터 {

    static int A, B;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Integer> arrA = new ArrayList<>();
        List<Integer> arrB = new ArrayList<>();

        //1. input
        A = sc.nextInt();
        for (int i = 0; i < A; i++) {
            arrA.add(sc.nextInt());
        }
        B = sc.nextInt();
        for (int i = 0; i < B; i++) {
            arrB.add(sc.nextInt());
        }

        Collections.sort(arrA);
        Collections.sort(arrB);

        List<Integer> ans = new ArrayList<>();
        int idxA = 0, idxB = 0;
        while(idxA < arrA.size() && idxB < arrB.size()){

            int x = arrA.get(idxA);
            int y = arrB.get(idxB);

            if(x == y) {
                ans.add(x);
                idxA++;
                idxB++;
            }
            else if(x> y) idxB++;
            else idxA++;
        }

        for(Integer i: ans){
            System.out.print(i+" ");
        }

    }
}
