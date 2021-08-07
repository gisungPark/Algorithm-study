package 모의역량테스트;

import java.util.*;
import java.io.*;

public class SWEA5658비밀번호 {

    static int T, N, K;
    static Set<Long> set;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc<= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            String nums = br.readLine();
            Array arr = new Array();
            set = new TreeSet<>(Comparator.reverseOrder());
            arr.init(nums);

            for(int i=0; i<N/4; i++){
                List<Long> value = arr.getValue();
                for(Long n : value){
                    set.add(n);
                }
                arr.rotation();
            }

            Iterator<Long> iterator = set.iterator();
            int i = 0;
            while (iterator.hasNext()){
                if(i == K-1) System.out.println("#"+tc +" " + iterator.next());
                else iterator.next();
                i++;
            }


        }
    }

    public static class Array{
        LinkedList<Character> list = new LinkedList<>();

        public Array(){

        }
        public void init(String str){
            for(int i=0; i<str.length(); i++){
                list.add(str.charAt(i));
            }
        }

        public void rotation(){
            Character remove = list.remove(N-1);
            list.add(0, remove);
        }

        public List<Long> getValue(){
            List<Long> res = new ArrayList<>();
            for(int i=0; i <N; i+=N/4){
                String str = "";
                for(int j=0; j<N/4; j++){
                    str += list.get(i+j);
                }
                res.add(Long.parseLong(str, 16));
            }
            return res;
        }
    }
}
