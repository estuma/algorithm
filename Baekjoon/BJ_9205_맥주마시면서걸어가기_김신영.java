import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_9205_맥주마시면서걸어가기_김신영 {

	static final int INF = 9999999;
	static int N;
	static double[][] arr, dp;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 입력 --
			// n: 편의점 개수 (0 <= 100)
			N = Integer.parseInt(br.readLine());

			int[][] arr = new int[N + 2][2];
			dp = new double[N + 2][N + 2];
			for (int i = 0; i < arr.length; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
				
			}

			// 인접행렬만들기 -> 1000이하인 경우만 갈 수 있음
			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < N + 2; j++) {
					if (i != j) {
						double dr = Math.pow(arr[i][0] - arr[j][0], 2);
						double dc = Math.pow(arr[i][1] - arr[j][1], 2);
						double tmp = Math.sqrt(dr + dc);
						dp[i][j] = (tmp <= 1000) ? tmp : INF;
					}
				}
			}
			
			for(int i = 0; i < N+2; i++) {
				System.out.println(Arrays.toString(dp[i]));
			}

			// Floyd Warshell 알고리즘 적용
			for (int k = 0; k < N + 2; k++) {
				for (int i = 0; i < N + 2; i++) {
					if (i == k) {
						continue;
					}
					for (int j = 0; j < N + 2; j++) {
						if (j == i || j == k) {
							continue;
						}

						if(dp[i][k] == INF || dp[k][j] == INF) {
							continue;
						}
						
						dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
					}
				}
			}
			
			for(int i = 0; i < N+2; i++) {
				System.out.println(Arrays.toString(dp[i]));
			}

			// 1000미터로 갈 수 있는지?
			if(dp[0][N+1] != INF) {
				sb.append("happy");
			} else {
				sb.append("sad");
			}
			
			sb.append("\n");
		}

		System.out.println(sb);
	}

}
