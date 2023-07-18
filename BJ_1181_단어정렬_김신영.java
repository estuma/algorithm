package hyundai;

import java.util.*;
import java.io.*;

/**
 * 백준 1181 단어 정렬 https://www.acmicpc.net/problem/1181
 * 
 * @author 김신영
 * 
 * 길이가 짧은것부터 정렬하고, 길이가 같으면 사전순으로 정렬
 * priorityQueue를 이용해서 길이순 / 사전순으로 우선순위 부여
 * 이전에 나온 값과 비교하며 중복을 제거한다.
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 우선순위큐 사용하여 정렬, compare함수를 오버라이드하여 길이순, 사전순으로 나올 수 있게 한다.
		PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length() == o2.length()) {
					// 인덱스 0부터 검사해서 사전순으로 우선순위 부여
					for(int i = 0; i < o1.length(); i++) {
						if(o1.charAt(i) > o2.charAt(i)) {
							return 1;
						} else if(o1.charAt(i) < o2.charAt(i)) {
							return -1;
						} 
					}
					return 0;					
				} else {
					// 길이순서
					return o1.length() - o2.length();
				}				
			}
		});

		// 단어 n개 읽기
		for (int i = 0; i < n; i++) {
			pq.add(br.readLine());
		}
		
		// 중복 제거하며 출력
		String pre = "";
		for(int i = 0; i < n; i++) {
			String curr = pq.poll();
			// 현재 값이 이전값과 같으면 출력하지 않음
			if(!pre.equals(curr)) {
				System.out.println(curr);
			}
			pre = curr;
		}
	}
}