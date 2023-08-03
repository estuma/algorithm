import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 18,428 kb, 108 ms
 * 파리퇴치
 * 파리를 가장 많이 잡을 수 있는 경우를 찾아야 하므로
 * m * m 영역으로 잡았을 때 잡을 수 있는 모든 경우 탐색하여 그 중 가장 큰 값이 정답
 * </pre>
 * @author 김신영
 *
 */

public class D2_2001_파리퇴치 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		int tnum = 1;
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int answer = 0;
			int[][] arr = new int[n][n];

			// 파리배열 입력
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 검사 n*n 배열 검사, 영역 길이가 m이므로 m 뺀값만큼 검사한다.
			for (int r = 0; r <= n - m; r++) {
				for (int c = 0; c <= n - m; c++) {
					// 구역 하나, m * m 검사
					int tmp = 0;
					for (int i = r; i < r + m; i++) {
						for (int j = c; j < c + m; j++) {
							tmp += arr[i][j];
						}
					}
					// 만일 현재 구역의 합이 더 크면 정답 갱신한다.
					answer = (answer < tmp) ? tmp : answer;
				}
			}
			System.out.println("#" + tnum++ + " " + answer);
		}
	}
}
