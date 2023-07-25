import java.util.*;
import java.io.*;

/**
 * 백준 2805 나무 자르기 https://www.acmicpc.net/problem/2805
 * 168460KB, 500ms
 * @author 김신영
 * 나무 자르기
 * 절단기 높이 H로 지정하면 나무를 H미터 남기고 윗부분을 모두 자른다.
 * M미터의 나무를 집에 가져가기 위한 H의 최댓값 구하기
 * 이진탐색을 사용하여 범위를 좁힐 수 있다.
 * 나무의 높이는 최소 0, 최대 1,000,000,000까지 이므로 범위를 이렇게 설정한다.
 * 이 값의 중간값으로 나무를 자르고, 
 * 1. 나무를 자른 값의 합이 M 이상이라면 -> 덜 잘라야 하므로 low값을 갱신
 * 2. 나무를 자른 값의 합이 M 미만이라면 -> 더 잘라야 하므로 high값 갱신 
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		// 사용자 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 공백 기준으로 스트링 분리
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 나무 수
		int m = Integer.parseInt(st.nextToken()); // 필요한 나무 미터
		int arr[] = new int[n];
		// 전체 나무 높이 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 나무가 가질 수 있는 최소, 최대값으로 설정
		int low = 0;
		int high = 1000000000;
		
		
		// m 이상이 나와야 하므로 Lower Bound 사용
		// 이진탐색 low가 high보다 커지면 종료
		while(low < high) {
			// 이진탐색 low가 high보다 커지면 종료
			int mid = (low + high) / 2;

			// 이진탐색 low가 high보다 커지면 종료
			long sum = 0;
			
			// 전체 나무를 mid값으로 잘라 나온 합
			for(int a : arr) {
				if(a > mid) {
					sum += (a - mid);
				}
			}
			
			// 합이 필요한 높이 이상이면 최소값이 mid보다 큼 -> low 갱신
			if(sum >= m) {
				low = mid + 1;
			} else { // 합이 더 작으면 나무를 더 잘라야하므로 -> high를 mid값으로 설정
				high = mid - 1;
			}
		}
		
		// 값 출력
		System.out.println(high);
	}
}