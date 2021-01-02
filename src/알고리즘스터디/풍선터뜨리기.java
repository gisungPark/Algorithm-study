package 알고리즘스터디;

public class 풍선터뜨리기 {

	public static int solution(int[] a) {
		
		int left=0, right=a.length-1;
		int ans = 0, cnt = 0;
		for(int i=0; i<a.length; i++) {
			cnt = 0;
			left = leftMin(a, i, left);
			right = rightMin(a, i, right);
			
			if(a[i] > a[left]) cnt++; 
			if(a[i] > a[right]) cnt++;
			if(cnt != 2) ans++;
		}
		
		return ans;
	}

	private static int rightMin(int[] a, int idx, int right) {
		for(int i=right; i>idx; i--) {
			if(a[i] < a[idx]) return i;
		}
		return a.length-1;
	}

	private static int leftMin(int[] a, int idx, int left) {
		for(int i=left; i<idx; i++) {
			if(a[i] < a[idx]) return i;
		}
		return 0;
	}

	public static void main(String[] args) {
		int ans = solution(new int[] {-16,27,65,-2,58,-92,-71,-68,-61,-33});
		System.out.println(ans);
	}

}
