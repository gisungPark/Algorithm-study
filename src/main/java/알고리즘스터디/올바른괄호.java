package 알고리즘스터디;

import java.util.Stack;

public class 올바른괄호 {

	public static boolean solution(String s) {
        
        Stack<Character> st = new Stack();
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                st.push('(');
            }else{
                if(st.size() == 0) return false;
                st.pop();
            }
        }
        if(st.size()!=0) return false;
        
        return true;
    }
	public static void main(String[] args) {
		System.out.println(solution("(()("));
	}
}
