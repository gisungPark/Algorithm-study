package 코테준비;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class 능숙함체크 {

    public static void main(String[] args) {

        String str ="ASADADAS";
        char[] ch = str.toCharArray();
        int[] buf = new int[200];

        int max = 0;
        char maxCh = 'a';
        for (int i = 0; i < ch.length; i++) {
            buf[ch[i]]++;
            if(buf[ch[i]] > max){
                max = buf[ch[i]];
                maxCh = ch[i];
            }
        }

        System.out.println(maxCh);
    }
}
