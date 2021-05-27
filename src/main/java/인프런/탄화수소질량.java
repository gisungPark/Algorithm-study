package 인프런;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 탄화수소질량 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char[] ch = str.toCharArray();

        int c = 0;
        int h = 0;
        int pos = 0;
        if(ch[1] == 'H'){
            c = 1;
            pos = 1;
        }else{
            pos = 1;
            for(int i=1; ch[i] !='H'; i++){
                c = c*10 + (ch[i]-'0');
                pos ++;
            }
        }

        if(ch.length == (pos+1)){
            h = 1;
        }else{
            for(int i=pos+1; i<ch.length; i++){
                h = h*10 + (ch[i]-'0');
            }
        }

        System.out.println((c*12 + h));
    }
}
