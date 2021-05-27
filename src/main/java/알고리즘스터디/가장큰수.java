package 알고리즘스터디;

import java.util.Comparator;
import java.util.TreeSet;

public class 가장큰수 {
	
	static TreeSet<String> set;
	static boolean[] isSelected;
	static int[] sel;
	public static String solution(int[] numbers) {

		int n = numbers.length;
		sel = new int[n];
		isSelected = new boolean[n];
		set = new TreeSet<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		
		perm(numbers, 0, n);
		
		String answer = set.first();
		return answer;
	}

	private static void perm(int[] numbers, int cnt, int n) {
		if(cnt == n) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<n; i++) {
				sb.append(String.valueOf(numbers[sel[i]]));
			}
			set.add(sb.toString());
		}else {
			for(int i=0; i<n; i++) {
				if(isSelected[i]) continue;
				isSelected[i] = true;
				sel[cnt] = i;
				perm(numbers, cnt+1, n);
				isSelected[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		String sb = solution(new int[] {6,10,2});
		System.out.println(sb);
	}
}
