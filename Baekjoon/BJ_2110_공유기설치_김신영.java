import java.util.*;
import java.io.*;

/**
 * 백준 2110 공유기 설치 https://www.acmicpc.net/problem/2110
 * 28364KB, 260ms
 * @author 김신영
 * 공유기 설치
 * 첫째 줄 
 * - 집의 개수 N (2 ≤ N ≤ 200,000)
 * - 공유기의 개수 C (2 ≤ C ≤ N)
 * 이 하나 이상의 빈 칸을 사이에 두고 주어진다. 
 * 둘째 줄부터 N개의 줄에
 * - 집의 좌표 xi (0 ≤ xi ≤ 1,000,000,000)
 * 
 * 이분탐색 활용
 * 가능한 공유기 사이의 간격을 low, high, mid값으로
 * low는 0, high: 가장 멀리 있는 두 공유기 사이의 간격으로 설정한다.
 * mid 으로 공유기 설치하고 공유기 대수가 더 많으면 간격을 늘리고, 적으면 간격을 좁힌다.
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 집 개수
		int C = Integer.parseInt(st.nextToken());	// 공유기 개수
		int[] home = new int[N];					// 집 위치
		for(int i = 0; i < N; i++) {
			home[i] = Integer.parseInt(br.readLine());
		}
		
		// 공유기 간격 확인 위해 정렬
		Arrays.sort(home);
		
		// 최소, 최대값 설정
		int low = 0;						// 최소 간격 0
		int high = home[N-1] - home[0];		// 최대 간격은 첫번째집과 마지막집 거리

		// 이진탐색
		while(low <= high) {
			int mid = (low + high) / 2;
			
			int pre = home[0];	// 이전에 공유기 설치한 위치
			int cnt = 1;		// 공유기 설치 대수 저장 변수
			for(int i = 1; i < N; i++) {
				// mid 간격보다 넒으면 공유기 설치
				if(home[i] - pre >= mid) {
					cnt++;
					pre = home[i];
				}
			}
			
			if(cnt < C) { 	// 설치한 공유기 수가 더 적으면
				high = mid - 1;	// 간격 좁히기
			} else {		// 설치한 공유기 수가 더 많으면
				low = mid + 1;	// 간격 늘리기
			}
		}
		
		// 답 출력
		System.out.println(high);
	}
	
}
