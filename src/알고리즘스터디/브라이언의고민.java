package 알고리즘스터디;

import java.util.ArrayList;
import java.util.List;

public class 브라이언의고민 {

	public static String solution(String sentence) {
		
		List<Integer> pos;
		List<String> words = new ArrayList<>();
		
	
		char ch = ' ';
		while(sentence.length() > 0) {
			if(sentence.length() < 3) return "invalid";
			
			// 규칙2의 경우
			if(checkLetter(sentence, 0)) {
				ch = sentence.charAt(0);
				
				int eIdx = 0; 
				for(int i=1; i<sentence.length(); i++) {
					if(sentence.charAt(i) == ch) {
						eIdx = i;
						break;
					}
				}
				if(eIdx == 0) return "invalid";
				words.add(sentence.substring(0, eIdx+1));
				sentence = sentence.substring(eIdx+1);
			}else {
				// 규칙 1의 경우
				if(!checkLetter(sentence, 1)) return "invalid";
				ch = sentence.charAt(1);
				
				int eIdx = 0; 
				for (int i = 2; i < sentence.length(); i++) {
					if (checkLetter(sentence, i)) {
						if (sentence.charAt(i) != ch) {
							eIdx = i;
							break;
						}
					}
				}
				
				while(eIdx >= 1) {
					if(ch == sentence.charAt(eIdx)) break;
					eIdx--;
				}
				if(eIdx == 1) return "invalid";
				 
				words.add(sentence.substring(0, eIdx+2));
				sentence = sentence.substring(eIdx+2);
			}
		}

		String ans = "";
		for(String s: words) {
			ans += converToString(s)+" ";
		}
		
		return ans.trim();
	}

	private static String converToString(String s) {
		
		if(checkLetter(s, 0)) s = s.replace(s.charAt(0), ' ');
		if(checkLetter(s, 1)) s = s.replace(s.charAt(1), ' ');
		if(checkLetter(s, 2)) s = s.replace(s.charAt(2), ' ');
		s = s.replace(" ", "");
		
		return null;
	}

	private static boolean checkLetter(String str, int i) {
		if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z') return true;
		return false;
	}

	public static void main(String[] args) {
		System.out.println(solution("AxAxAxAoBoBoB"));
	}

}
