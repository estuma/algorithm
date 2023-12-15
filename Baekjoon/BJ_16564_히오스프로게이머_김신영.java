import java.util.*;
import java.io.*;

/**
 * 백준 16564 히오스 프로게이머 https://www.acmicpc.net/problem/16564
 * 21280KB, 240ms
 * @author 김신영
 * 히오스 프로게이머
 * 첫째 줄에는 캐릭터의 개수 N, 올릴 수 있는 레벨 총합 K가 주어진다. 
 * (1 ≤ N ≤1,000,000, 1 ≤ K ≤ 1,000,000,000)
 * 다음 N개의 줄에는 현재 각 캐릭터의 레벨이 주어진다. (1 ≤ Xi ≤ 1,000,000,000)
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 캐릭터 개수
		int K = Integer.parseInt(st.nextToken());	// 올릴 수 있는 레벨
		// n개 캐릭터의 레벨 입력
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 가능한 레벨의 최대, 최소값 설정
		int low = 0;
		int high = Integer.MAX_VALUE;
		while(low <= high) {
			int mid = (low + high) / 2;
			long sum = 0;
			
			// mid값까지 올리는데 필요한 레벨 확인
			for(int i = 0; i < N; i++) {
				if(arr[i] < mid) {
					sum += mid - arr[i];
				}
			}
			
			// sum이 K보다 작다면 레벨을 더 올릴 수 있으므로 low 갱신
			if(sum <= K) {
				low = mid + 1;
			} else {	// sum이 K보다 더 크다면 mid값까지 레벨을 올릴 수 없으므로 high값 갱신
				high = mid - 1;
			}
		}
		
		// 정답 출력
		System.out.println(high);
		
	}
}
