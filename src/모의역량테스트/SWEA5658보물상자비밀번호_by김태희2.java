package 모의역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

/** ********************
 * 
 * shift 연산을 하지 않는 방법
 * 
 ********************* **/

public class SWEA5658보물상자비밀번호_by김태희2 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int C = N/4;	//한변의 길이: 2-7 => 회전횟수 결정
			int K = Integer.parseInt(st.nextToken());
			String nums = in.readLine();
			nums = nums.concat(nums); // shift 처리 하지하지 않기 위해 자신의 문자열을 한번 더 붙임
			
			TreeSet<String> set = new TreeSet<String>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o2.compareTo(o1); // o2-o1
				}
			});
			
			int c = 0, begin = 0;
			while(++c<=C){ //회전 관련 처리 c
				
				// 현 상태에서 각 변의 길이만큼 문자열 비밀번호 추출하여 set에 넣기
				for(int i=begin, j=0; j<4; i+=C,++j) {
					set.add(nums.substring(i, i+C));
				}
				++begin;
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
