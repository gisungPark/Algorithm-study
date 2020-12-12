package 알고리즘스터디;

import java.util.Arrays;

public class 단체사진찍기 {

	static int ans;
	static int[] sel;
	static char[] person;
	static boolean[] visited;
	public static int solution(int N, String[] data) {
		
		String str = "ACFJMNRT";
		person = str.toCharArray();
		sel = new int[8];
		visited = new boolean[8];
		
		ans = 0;
		perm(0, data);
		System.out.println(ans);
		return -1;
	}

	private static void perm(int k, String[] data) {
		if(k == sel.length) {
			//System.out.println(Arrays.toString(sel));
			boolean isOk = true;  
			for(int i=0; i<data.length; i++) {
				int s = 0, e = 0;
				
				for(int j=0; j<sel.length; j++) {
					if(person[sel[j]] == data[i].charAt(0)) s = j;
					else if(person[sel[j]] == data[i].charAt(2)) e = j;
				}
				int dis = Math.abs(s-e);
				char op = data[i].charAt(3);
				int com = data[i].charAt(4)-'0';
				if(op == '<' && dis < com+1) {
					
				}else if(op == '>' && dis > com+1 ) {
					
				}else if(op == '=' && dis == com+1) {
					
				}else {
					isOk = false;
					break;
				}
			}
			if(isOk) ans++;
			
		}else {
			for(int i=0; i<sel.length; i++) {
				if(visited[i]) continue;
				sel[k] = i;
				visited[i] = true;
				perm(k+1, data);
				visited[i] = false;
			}
		}
		
	}

	public static void main(String[] args) {
		solution(2, new String[] {"N~F=0", "R~T>2"});
	}

}
