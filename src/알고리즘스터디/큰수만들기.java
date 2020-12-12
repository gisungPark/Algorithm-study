package 알고리즘스터디;

public class 큰수만들기 {

	public static String solution(String number, int k) { // 문자열 형식의 숫자,  제거할 문자열 갯수
		
		StringBuilder sb = new StringBuilder(number);
		int resultLen = sb.length() - k;
		
		for(int i=0; i<k; i++) {
			int removeIdx = sb.length();
			for(int j=0; j<sb.length()-1; j++) {
				if(j>resultLen) break;
				if(sb.charAt(j)=='9') continue;

				if(sb.charAt(j) - sb.charAt(j+1) < 0) {
					removeIdx = j;
					break;
				}
			}
			if(removeIdx > resultLen) break;
			else sb.deleteCharAt(removeIdx);
		}
		return sb.substring(0, resultLen).toString();
	}

	public static void main(String[] args) {
		String a = solution("999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999",2);
		System.out.println(a);
		
	}
}
