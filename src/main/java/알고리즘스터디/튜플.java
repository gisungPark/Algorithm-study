package 알고리즘스터디;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class 튜플 {

	public static <T> int[] solution(String s) {
		
		ArrayList<String> list = new ArrayList<>();
		
		//1. 문자열 파싱
		s = s.substring(1, s.length()-1);
		
		int start = 0;
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == '}') {
				list.add(s.substring(start,i+1));
				if(i+2< s.length()) start = i+2;
			}
		}
		// 2. 문자열 정렬
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.length()-o2.length();
			}
		});
		
		int idx = 0;
		int[] ans = new int[list.size()];
		for(int i=0; i<list.size(); i++) {
			String tmp = list.get(i);
			tmp = tmp.replace("{", "").replace("}", "");
			
			String[] split = tmp.split(",");
			for(int j=0; j<split.length; j++) {
				int n = Integer.parseInt(split[j]);
				boolean isOk = true;
				for(int k=0; k<idx; k++) {
					if(ans[k] == n) isOk = false;
				}
				
				if(isOk) {
					ans[idx++] = n;
				}
			}
		}
		
		return ans;
	}

	public static void main(String[] args) {
		int ans[] = solution("{{20,111},{111}}");
		System.out.println(Arrays.toString(ans));
	}
}
