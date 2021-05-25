package 알고리즘스터디;

public class 풍선터뜨리기 {

	public static int solution(int[] a) {
		
		int ans = 0;
		int[][] arr = new int[2][a.length];
		
		arr[0][0] = a[0];
		arr[1][a.length-1] = a[a.length-1];
		for(int i=1; i<a.length; i++) {
			arr[0][i] = Math.min(arr[0][i-1], a[i]); 
		}
		for(int i=a.length-2; i>=0; i--) {
			arr[1][i] = Math.min(arr[1][i+1], a[i]);
		}
		
		int cnt = 0;
		for(int i=0; i<a.length; i++) {
			cnt = 0;
			if(a[i] > arr[0][i]) cnt++;
			if(a[i] > arr[1][i]) cnt++;
			if(cnt <= 1 ) ans++;
		}
		
		
		return ans;
	}

	

	public static void main(String[] args) {
		int ans = solution(new int[] {-16,27,65,-2,58,-92,-71,-68,-61,-33});
		System.out.println(ans);
	}

}
