package 알고리즘스터디;

public class 스킬트리 {

	public static int solution(String skill, String[] skill_trees) {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] alpa = str.toCharArray();
		boolean[] isOk = new boolean[26];


		for(int i=0; i<skill.length(); i++) {
			for(int j=0; j<alpa.length; j++) {
				if(skill.charAt(i) == alpa[j]) {
					isOk[j] = true;
					break;
				}
			}
		}
		
		
		String tmp = "";
		int ans = 0;
		for(String s: skill_trees) {
			tmp = String.valueOf(s);
			for(int i=0; i<alpa.length; i++) {
				if(!isOk[i]) {
					tmp = tmp.replace(alpa[i], ' ');
				}
			}
			tmp = tmp.replace(" ", "");
			if(tmp.length() == 0 ) {
				ans++;
				continue;
			}
			
			int len = tmp.length();
			String compare = skill.substring(0, len);
			if(compare.equals(tmp)) ans++;
		}
		
		return ans;
	}
	
	public static void main(String[] args) {
		int ans = solution("CBDK", new String[] {
				"CB",
				"CXYB",
				"BD",
				"AECD",
				"ABC",
				"AEX",
				"CDB",
				"CBKD",
				"IJCB",
				"LMDK"
		});
		System.out.println(ans);
	}
}
