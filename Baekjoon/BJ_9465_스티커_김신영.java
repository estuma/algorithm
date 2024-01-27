import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * https://www.acmicpc.net/problem/9465
 * 125,380KB	992ms
 * ----- ----- -----
 * 점수의 합이 최대가 되도록 스티커 떼기
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_9465_스티커_김신영 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

      // 입력
			int[][] arr = new int[2][N];
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

      // 초기값 설정
			int[][] dp = new int[2][N + 1];
			dp[0][0] = dp[1][0] = 0;
			dp[0][1] = arr[0][0];
			dp[1][1] = arr[1][0];

			for (int col = 2; col < N + 1; col++) {
				dp[0][col] = Math.max(dp[1][col - 1], dp[1][col - 2]) + arr[0][col - 1];
				dp[1][col] = Math.max(dp[0][col - 1], dp[0][col - 2]) + arr[1][col - 1];
			}

			System.out.println(Math.max(dp[0][N], dp[1][N]));
		}
	}

}