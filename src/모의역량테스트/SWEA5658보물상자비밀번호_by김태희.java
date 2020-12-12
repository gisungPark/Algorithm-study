package 모의역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA5658보물상자비밀번호_by김태희 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int C = N/4;	//한변의 길이: 2-7 => 회전횟수 결정
			int K = Integer.parseInt(st.nextToken());
			char[] nums = in.readLine().toCharArray();
			
			TreeSet<String> set = new TreeSet<String>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o2.compareTo(o1); // o2-o1
				}
			});
			
			int c = 0;
			while(++c<=C){ //회전 관련 처리 c
				// 현 상태에서 각 변의 길이만큼 문자열 비밀번호 추출하여 set에 넣기
				for(int i=0; i<nums.length; i+=C) {
					String s = "";
					for(int j=0; j<C; j++) {
						s += nums[i+j];
					}
					// 'F12'가  '12F'보다 아스키코드값이 크다
					// 그러므로 정수변환해서 넣을 필요 없다. 
					set.add(s);
				}
				
				// 시계방향으로 shift
				char tmp = nums[N-1];
				for(int i=N-1; i>0; i--) {
					nums[i] = nums[i-1];
				}
				nums[0] = tmp;
				
			}
			
			int k = 0, ans = 0;
			for(String s : set) {
				if(++k == K) {
					// 16진수를 10진수로 변환해주는 메소드가 있네ㅠㅠ
					ans = Integer.parseInt(s, 16);
				}
			}
			System.out.println("#"+tc+" " +ans);
		}
	}
	
}
