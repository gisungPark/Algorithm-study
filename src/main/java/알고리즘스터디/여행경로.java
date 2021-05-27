package 알고리즘스터디;

import java.util.ArrayList;
import java.util.Collections;

public class 여행경로 {

	static ArrayList<String> routes;
	static boolean[] visited;
	public static String[] solution(String[][] tickets) {

		routes = new ArrayList<>();
		visited = new boolean[tickets.length];
		DFS("ICN", "ICN", 0, tickets);

		Collections.sort(routes);
		
		String[] ans = routes.get(0).split(" ");
		return ans;
	}

	private static void DFS(String obj, String route, int cnt, String[][] tickets) {

		if(cnt == tickets.length) {
			routes.add(route);
			return;
		}
		
		String from = null, to = null;
		for(int i=0; i<tickets.length; i++) {
			from = tickets[i][0];
			to = tickets[i][1];
			
			if(from.equals(obj) && !visited[i]) {
				visited[i] = true;
				DFS(to, route+" "+to, cnt+1, tickets);
				visited[i] = false;
			}
		}
		
	}

	public static void main(String[] args) {
		String[] ans = solution(new String[][] {
			{"ICN", "SFO"},
			{"ICN", "ATL"},
			{"SFO", "ATL"},
			{"ATL", "ICN"},
			{"ATL", "SFO"}
		});
		
		for(String s : ans) {
			System.out.println(s);
		}
		
		
	}
}
