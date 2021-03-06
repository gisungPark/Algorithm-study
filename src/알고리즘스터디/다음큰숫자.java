package 알고리즘스터디;

import java.util.Arrays;

public class 다음큰숫자 {
	public static int[] sel;
	public static String ans;
	public static boolean isOk;
	public static String binary;

	public static int solution(int n) {
		// 1. n의 1의 갯수를 센다
		
	
		binary = convertToBinary(n);
		System.out.println(binary);
		int cnt = 0;
		for (int i = 0; i < binary.length(); i++) {
			if (binary.charAt(i) == '1')
				cnt++;
		}

		// 2. 해당 1의 갯수로 만들수 있는 7자리 이진수 체크
		sel = new int[7];
		ans = "";
		isOk = false;
		DFS(0, (7 - cnt), cnt);

		// 3. 이진수를 다시 정수로 변환
		return convertToInt(ans);

	}

	public static int convertToInt(String str) {
		int len = str.length() - 1;
		int sum = 0;
		for (int i = len, j = 0; i >= 0; i--, j++) {
			sum += (str.charAt(i) - '0') * Math.pow(2, j);
		}
		return sum;
	}

	public static void DFS(int idx, int zeroCnt, int oneCnt) {
		if (!ans.equals("")) return;
		if (idx == 7) {
			if (isOk) {
				// n의 다음수.
				ans = convertToString(sel);
				System.out.println(Arrays.toString(sel));
			}
			String str = convertToString(sel);
			if (binary.equals(str))
				isOk = true;

		} else {
			if (zeroCnt > 0) {
				sel[idx] = 0;
				DFS(idx + 1, zeroCnt - 1, oneCnt);
			}
			if (oneCnt > 0) {
				sel[idx] = 1;
				DFS(idx + 1, zeroCnt, oneCnt - 1);
			}
		}
	}

	public static String convertToString(int[] arr) {

		String str = "";
		for (int i = 0; i < sel.length; i++) {
			str += Integer.valueOf(arr[i]);
		}
		return str;
	}

	public static String convertToBinary(int n) {
		String tmp = "";
		String str = "";
		while (n >= 1) {
			tmp += Integer.valueOf(n % 2);
			n /= 2;
		}
		
		int len = tmp.length();
		if(len < 7) {
			for(int i=len; i<7; i++) {
				tmp+="0";
			}
		}
		
		for(int i=tmp.length()-1; i>=0; i--) {
			str += tmp.charAt(i);
		}
		
		return str;
	}

	public static void main(String[] args) {
		int aa = solution(98);
		System.out.println(aa);
	}
}
