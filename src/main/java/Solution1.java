import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Solution1 {

    public static void main(String[] args) {
        String name = "John Doe; Peter Benjamin Parker; Mary Jane Watson-Parker; John Elvis Doe; John Evan Doe; Jane Doe; Peter Brian Parker";
        String company = "example";
        String ans = solution(name, company);
        System.out.println(ans);

    }

    public static String solution(String S, String C){

        StringBuilder sb = new StringBuilder();
        Set<String> set = new HashSet<>();

        String company = C.toLowerCase();
        String[] names = S.split("; ");
        for(int i=0; i< names.length; i++){
            String name = names[i];
            // 1. First, Last 찾기
            String[] splits = splitName(name);

            String tmpName = splits[0] + "." + splits[1];

            // 4. 최종 이름 중복 체크
            String finalName = tmpName;
            int idx = 2;
            while(true){
                if(!set.contains(finalName)) break;
                finalName = tmpName + (idx++);
            }
            set.add(finalName);
            sb.append(name + " <"+finalName+"@"+company+".com>");
            if(i != names.length-1) sb.append("; ");
        }

        return sb.toString();
    }

    private static String[] splitName(String name) {
        String[] s = name.split(" ");
        String first = s[0].toLowerCase();
        String last = "";

        // 2. List '-' 제거 후 8자리 끊기
        // 3. First, Last 소문자로 변환하기
        if(s.length > 2 ) last = s[2].replace("-", "").toLowerCase();
        else last = s[1].replace("-", "").toLowerCase();

        if(last.length() > 8) last = last.substring(0,8);

        return new String[]{first, last};
    }

}
