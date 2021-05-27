package 알고리즘스터디;

public class 광고삽입 {

	public static String solution(String play_time, String adv_time, String[] logs) {
		String answer = "";
		return answer;
	}

	public static String calcTime(String start, String end) {
		String[] s = start.split(":");
		String[] e = end.split(":");
		String head = "0";

		String[] ans = new String[3];
		for(int i=2; i>=0; i--) {
			String[] res = calc(s[i], e[i], head);
			ans[i] = res[0];
			head = res[1];
		}

		return "";

	}
	public static String[] calc(String num1, String num2, String head) {
		int aaa = Integer.valueOf(num1);
		int bbb = Integer.valueOf(num2);
		
		// 올림수 덧셈
		if(head.equals("1")) aaa++;
		int gap = 0;
		if(aaa > bbb) {
			gap = 60 - aaa;
			gap += bbb;
			if(gap >= 60) {
				head = "1";
				gap -= 60;
			}
		}else {
			gap = bbb - aaa;
			head = "0";
		}
		
		return new String[] {Integer.toString(gap), head};
	}

	public static void main(String[] args) {

	}
}
