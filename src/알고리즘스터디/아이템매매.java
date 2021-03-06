package 알고리즘스터디;

import java.util.ArrayList;
import java.util.List;

public class 아이템매매 {

	public static int ans;

	public static int solution(String[] sel, String[] buy) {

		List<String> items = new ArrayList<>();
		boolean[] complete = new boolean[sel.length];

		ans = -1;
		DFS(complete, items, sel, buy, 0);

		return ans;
	}

	private static void DFS(boolean[] complete, List<String> items, 
										String[] sel, String[] buy, int money) {
		ans = money > ans ? money : ans;
		for (int i = 0; i < complete.length; i++) {
			
			if (complete[i]) continue;
			
			List<String> copyList = new ArrayList<>(items);
			
			int sellPrice = 0, buyPrice = 0, bit = 0;
			
			try {
				sellPrice = Integer.parseInt(sel[i]);
			} catch (Exception e) {
				bit += 3;
			}
			try {
				buyPrice = Integer.parseInt(buy[i]);
			} catch (Exception e) {
				bit += 2;
			}
			
			if(bit == 5) {
				// 둘다 아이템인 경우 물물교환
				if(!items.contains(buy[i])) continue;
				copyList.remove(buy[i]);
				copyList.add(sel[i]);
				
				complete[i] = true;
				DFS(complete, copyList, sel, buy, money);
				complete[i] = false;
			}else if(bit == 2) {
				// 구매하는 경우
				if(!items.contains(buy[i])) continue;
				
				copyList.remove(buy[i]);
				
				complete[i] = true;
				DFS(complete, copyList, sel, buy, money+sellPrice);
				complete[i] = false;
			}else {
				// 판매하는 경우
				copyList.add(sel[i]);
				complete[i] = true;
				DFS(complete, copyList, sel, buy, money-buyPrice);
				complete[i] = false;
			}
			
		}
	}

	public static void main(String[] args) {
		int ans = solution(new String[] { "A", "B", "2000", "C", "D", "1000", "3000", "B", "500", "2500" },
				new String[] { "1000", "A", "B", "1500", "C", "D", "B", "C", "B", "A" });
		System.out.println(ans);
	}
}
