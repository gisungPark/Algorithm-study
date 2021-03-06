package 알고리즘스터디;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 메뉴리뉴얼 {

	public static class Menu implements Comparable<Menu>{
		String name;
		int cnt;
		
		public Menu(String name, int cnt) {
			this.name = name;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "Menu [name=" + name + ", cnt=" + cnt + "]";
		}


		@Override
		public int compareTo(Menu o) {
			if(this.name.length() == o.name.length()) return o.cnt - this.cnt;
			else return this.name.length() - o.name.length();
		}
	}
	
	public static boolean[] selected;
	public static Map<String, Integer> map = new HashMap<>();
	public static String[] solution(String[] orders, int[] course) {
		
		for(int i=0; i<orders.length; i++) {
			selected = new boolean[orders.length];
			perm(orders[i], orders[i].length(), 0);
		}
		
		List<Menu> list = new ArrayList<>();
		Menu menu = null;
		for(String key: map.keySet()) {
//			System.out.println(key+" : "+ map.get(key));
			if(map.get(key) < 2) continue;
			menu = new Menu(key, map.get(key));
			list.add(menu);
		}
		
		Collections.sort(list);
		List<String> ans = new ArrayList<>();
		
		int idx = 0, prev = 0;
		for(int i=0; i<list.size(); i++) {
			String name = list.get(i).name;
			int cnt = list.get(i).cnt;
			
			if(name.length() == course[idx] && prev <= cnt) {
				ans.add(name);
				prev = cnt;
			}else {
				if(name.length() > course[idx]) {
					idx++;
					prev = 0;
					if(name.length() == course[idx] && prev <= cnt) {
						ans.add(name);
						prev = cnt;
					}
				}
			}
			
			if(idx == course.length) break;
		}
		
		Collections.sort(ans);
		String[] answer = new String[ans.size()];
		for(int i=0; i<ans.size(); i++) {
			answer[i] = ans.get(i);
		}
		
		return answer;
	}
	
	public static void perm(String orders, int length, int idx) {
		if(idx == length) {
			
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<selected.length; i++) {
				if(!selected[i]) continue;
				sb.append(orders.charAt(i));
			}
			
			String mm = sb.toString();
			char[] alpa = mm.toCharArray();
			Arrays.sort(alpa);
			
			mm = new String(alpa);
			
			if(mm.length() > 1) {
				map.put(mm,map.get(sb.toString()) != null ? map.get(mm)+1: 1);
			}
			
		}else {
			selected[idx] = true;
			perm(orders, length, idx+1);
			selected[idx] = false;
			perm(orders, length, idx+1);
		}
	}

	public static void main(String[] args) {
		String[] ans = solution(
				new String[] {
						"ABCFG", "AC", "CDE",
						"ACDE", "BCFG", "ACDEH"
					},
				new int[] {2,3,4}
				);
		
		for(int i=0; i<ans.length; i++) {
			System.out.println(ans[i]);
		}
		
	}

}
