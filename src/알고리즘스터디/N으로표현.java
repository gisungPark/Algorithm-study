package 알고리즘스터디;

import java.util.HashSet;
import java.util.Set;

public class N으로표현 {

	public static int solution(int N, int number) {

		Set<Integer>[] set = new HashSet[10];
		
		int tmp = 0;
		for(int i=1; i<9; i++) {
			set[i] = new HashSet<>();
			
			tmp = tmp*10 + N;
			set[i].add(tmp);
		}

		for(int i=1; i<=8; i++) {
			for(int j=1; j<i; j++) {
				for(Integer a: set[j]) {
					for(Integer b: set[i-j]) {
						set[i].add(a+b);
						set[i].add(a-b);
						set[i].add(a*b);
						if(b != 0) set[i].add(a/b);
					}
				}
			}
			if(set[i].contains(number)) return i;
		}
		
        return -1;
    }
	public static void main(String[] args) {
		int ans = solution(9,9);
		System.out.println(ans);
	}
}