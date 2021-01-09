package 알고리즘스터디;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class 베스트앨범 {

	public static class Song implements Comparable<Song> {
		int seq;	// 고유번호
		int plays;	// 플레이 횟수

		public Song(int seq, int plays) {
			this.seq = seq;
			this.plays = plays;
		}

		@Override
		public int compareTo(Song o) {
			if(this.plays == o.plays) return this.seq - o.seq;
			else return o.plays - this.plays;
		}
	}

	public static int[] solution(String[] genres, int[] plays) {
       
        Map<String, Integer> playByGenre = new HashMap<>();		// <장르, 장르별 플레이 횟수>
        Map<String, List<Song>> map = new HashMap<>();			// <장르, 해당 장르별 곡 정보(List)>
        List<Song> list = null;
        
        
        // 1. 장르별로 플레이 횟수를 계산
        for(int i=0; i<genres.length; i++){
     
        	// 해당 장르가 map에 없다면,
            if(!playByGenre.containsKey(genres[i])){
                playByGenre.put(genres[i], plays[i]);    // 장르별 플레이 횟수
                
                // 2. 해당장르별 곡 정보를 List를 이용해 관리
                list = new ArrayList<>();
                list.add(new Song(i, plays[i]));
                map.put(genres[i], list);
            }else{
                int cnt = playByGenre.get(genres[i]);
                playByGenre.put(genres[i], cnt+plays[i]);
                
                list = map.get(genres[i]);
                list.add(new Song(i, plays[i]));
                
            }
        }
        
        // Map에 저장된 장르별 플레이횟수를 
        // 내림차순 정렬하기 위해 TreeSet 자료구조 선언
        TreeSet<Integer> set = new TreeSet<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
        
        for(Integer i: playByGenre.values()) {
        	set.add(i);
        }
        
        // 베스트 앨범 정보를 저장한 ans List
        List<Integer> ans = new ArrayList<>();
        
        for(Integer i: set) {
        	// 3. TreeSet에서 내림차순으로 뽑힌 장르의
    		// 곡정보를 찾는다.
        	for(String genre : playByGenre.keySet()) {
        		if(playByGenre.get(genre) == i) {
        			list = map.get(genre);
        			Collections.sort(list);
        			
        			// 4. 해당 장르 곡정보가 2개 미만이라면, 하나만 베스트 앨범에 담는다.
        			if(list.size()>1) {
        				ans.add(list.get(0).seq);
        				ans.add(list.get(1).seq);
        			}else {
        				ans.add(list.get(0).seq);
        			}
        			
        		}
        	}
        }
        
        int[] answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++) {
        	answer[i] = ans.get(i);
        }
        return answer;
    }

	public static void main(String[] args) {
		int[] ans = solution(
				new String[] {"classic", "pop", "classic", "classic", "pop"},
				new int[] {500,600,150,800,2500}
		);
		
		for(int i :  ans) {
			System.out.println(i);
		}
	}
}
