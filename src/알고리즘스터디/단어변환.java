package 알고리즘스터디;

public class 단어변환 {
	
	static int ans;
	static char[] res;
	static String[] set;
	static boolean[] selected;
	public static int solution(String begin, String target, String[] words) {
		
		// 1. target이 words 목록에 존재하는지 체크
		boolean isOk = false;
		for(String com : words) {
			if(com.equals(target)) isOk = true;
		}
		if(!isOk) return 0;

		ans = 0;
		res = target.toCharArray();
		set = words;
		selected = new boolean[words.length];
		// 2. 변환작업을 진행한다.
		changeAlpa(begin, 0, words.length);
		
		return ans;
    }
	 
	private static void changeAlpa(String begin, int cnt, int len) {
		if(cnt == len) return;
		
		// 3. 문자열 비교를 위해 String을 char[]로 변환 후 비교
		char[] cur = begin.toCharArray();
		if(compareStr(cur, res) == 0) {
			ans = cnt;
			return;
		}else {
			for(int i=0; i<set.length; i++) {
				// 4. words 배열을 순회하며 이미 변환된 단어인지 체크
				if(selected[i]) continue;
				// 5. 두 단어사이에 다른 문자의 갯수 비교
				if(compareStr(cur, set[i].toCharArray()) == 1) {
					selected[i] = true;
					changeAlpa(set[i], cnt+1, len);
					selected[i] = false;
				}
			}
		}
	}

	// 두 char 배열을 비교하여 다른 문자가 몇개 있는지 반환하는 함수. 
	private static int compareStr(char[] cur, char[] tar) {
		int ans = 0;
		for(int i=0; i<cur.length; i++) {
			if(cur[i] != tar[i]) ans++;
		}
		return ans;
	}

	public static void main(String[] args) {
		int ans = solution("hit", "cog", new String[] {
				"hot", "dot","dog", "lot", "log", "cog"
		});
		System.out.println(ans);
	}
}
