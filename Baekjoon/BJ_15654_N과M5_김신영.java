import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_15654_N과M5_김신영 {

	static int N, M;
	static int[] order, number;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // N개 중
		M = Integer.parseInt(st.nextToken()); // M개

		order = new int[M];
		number = new int[N];
		visited = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(number);

		cal(0);
	}

	public static void cal(int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(order[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				order[cnt] = number[i];
				visited[i] = true;
				cal(cnt + 1);
				visited[i] = false;
			}
		}
	}
}
