package 알고리즘스터디;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
3
12:00 ~ 23:59
11:00 ~ 18:00
14:00 ~ 20:00


 */
public class 대여시간을추천드립니다 {

	public static class time implements Comparable<time>{
		public int hour;
		public int minute;
		
		public time(int hour, int minute) {
			this.hour = hour;
			this.minute = minute;
		}

		@Override
		public int compareTo(time o) {
			if(this.hour == o.hour) return this.minute - o.minute;
			return this.hour - o.hour;
		}
	}
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		
		List<time> startTime = new ArrayList<>();
		List<time> endTime = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			String t = sc.nextLine();
			String[] subTime = t.split("~");
			
			time first = convert(subTime[0]); 
			time second = convert(subTime[1]); 

			startTime.add(first);
			endTime.add(second);
		}
		
		Collections.sort(startTime); 
		Collections.sort(endTime);
		
		time first = startTime.get(startTime.size()-1);
		time last = endTime.get(0);

		StringBuilder sb = new StringBuilder();
		sb.append(first.hour).append(":").append(first.minute == 0 ? "00" : first.minute)
		.append(" ~ ").append(last.hour).append(":").append(last.minute==0?"00":last.minute);
		
		System.out.println(sb.toString());
	}
	private static time convert(String string) {
		string = string.trim();
		String[] splits = string.split(":");
		
		int hh = Integer.parseInt(splits[0]);
		int mm = Integer.parseInt(splits[1]);
		
		return new time(hh, mm);
	}
}
