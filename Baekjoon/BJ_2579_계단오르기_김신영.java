import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * <pre>
 * BJ_2579_계단오르기_김신영 | https://www.acmicpc.net/problem/2579
 * 11584KB	80ms
 * ----- ----- -----
 * DP
 * ----- ----- -----
 * 한 번에 한 계단 또는 두 계단씩 오를 수 있음
 * 연속된 세 계단을 밟을 수는 없다.
 * 마지막 계단은 반드시 밟아야 한다.
 * 얻을 수 있는 점수의 최댓값 구하기
 * ----- ----- -----
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_2579_계단오르기_김신영 {

	static int N, max = 0;
	static int[] arr;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N][2];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		if (N == 1) {
			System.out.println(arr[0]);
			return;
		}

		dp[0][0] = arr[0];
		dp[1][0] = arr[1];
		dp[1][1] = arr[0] + arr[1];

		for (int i = 2; i < N; i++) {
			dp[i][0] = Math.max(dp[i][0], Math.max(dp[i - 2][0], dp[i - 2][1]) + arr[i]);
			dp[i][1] = Math.max(dp[i][1], dp[i - 1][0] + arr[i]);
		}

		System.out.println(Math.max(dp[N - 1][0], dp[N - 1][1]));
	}
}
