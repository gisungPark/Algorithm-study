package 알고리즘스터디;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class 위장 {

	public static int solution(String[][] clothes) {

		Map<String, Integer> map = new HashMap<>();
		for(String[] str: clothes) {
			String key = str[1];
			if(map.containsKey(key)) {
				map.put(key, map.get(key)+1);
			}else {
				map.put(key, 1);
			}
		}
		
		int ans = 1;
		Iterator<String> keys = map.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			ans*=map.get(key)+1;
		}
	
		return --ans;
	}

	public static void main(String[] args) {
		
//		int ans = solution(new String[][]{ new String[]{"crow_mask", "headgear"},
//			new String[]{"blue_sunglasses", "eyewear"},
//			new String[]{"smoky_makeup", "headgear"}});
		
		int ans = solution(new String[][]{ new String[]{"crow_mask", "face"},
			new String[]{"blue_sunglasses", "face"},
			new String[]{"smoky_makeup", "face"}});
		
		System.out.println(ans);
	}
}
