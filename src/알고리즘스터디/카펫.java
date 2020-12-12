package 알고리즘스터디;

public class 카펫 {

	public static int[] solution(int brown, int yellow) {
        
		int[] answer = new int[2];
		boolean isOk = false;
		for(int i=1; i<=yellow; i++) {
			for(int j=yellow; j>=1; j--) {
				if(i*j == yellow && (i*2+j*2+4) == brown) {
					answer[0] = j+2;
					answer[1] = i+2;
					isOk = true;
					break;
				}
			}
			if(isOk) break;
		}
		
		System.out.println(answer[0]+" " + answer[1]);
        return answer;
    }
	public static void main(String[] args) {
		solution(24, 24);
	}
}
