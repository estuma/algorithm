import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * D3_3307_최장증가부분수열
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBOKg-a6l0DFAWr
 * 21,104KB	124ms
 * ---------- ----------
 * 이분탐색 활용한 LIS 풀이
 * 주어진 배열을 순차적으로 검사하여
 * 1. 현재까지 나온 원소 보다 큰값이면 리스트의 마지막에 값 추가해주고
 * 2. 그렇지 않으면 Arrays.binarySearch()를 통해 들어갈 수 있는 위치 찾아서 숫자 대체
 * </pre>
 * @author 김신영
 *
 */

public class D3_3307_최장증가부분수열_김신영 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			int N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// 이진탐색을 위한 배열
			int[] lis = new int[N];
			for (int i = 0; i < N; i++) {
				lis[i] = Integer.MAX_VALUE;
			}

			
			int idx = 1;
			lis[0] = arr[0];
			// 배열 앞에서부터 순차 탐색
			for (int i = 1; i < N; i++) {
				// 1. 현재 원소가 최장부분증가수열의 마지막 원소보다 크다면
				if (arr[i] > lis[idx - 1]) {
					// => 마지막 값에 현재 원소를 추가해준다.
					lis[idx++] = arr[i];
				} 
				// 2. 현재 원소가 최장부분증가수열의 마지막 원소보다 작다면
				else {
					// => 들어갈 위치 찾아서 현재 원소로 값을 바꿔준다.
					int tmp = Arrays.binarySearch(lis, arr[i]);
					// 같은 값이 있는 경우는 양수가 나오므로 이 경우 제외해준다.
					if (tmp < 0) {
						lis[-tmp - 1] = arr[i];
					}

				}
			}

			// 배열 길이가 최장부분증가수열의 길이
			answer = idx;
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

}
