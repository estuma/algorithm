import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 42,212KB 288ms
 * 
 * @author 김신영
 *
 */

public class BJ_15657_N과M9_김신영 {

	static int N, M;
	static int[] number, tmp;
	static boolean[] isUsed;
	static StringBuilder sb = new StringBuilder();
	static Set<String> set = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		number = new int[N];
		tmp = new int[M];
		isUsed = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(number);
		cal(0);

		System.out.println(sb.toString());
	}

	public static void cal(int cnt) {
		if (cnt == M) {
			if (set.contains(Arrays.toString(tmp))) {
				return;
			}

			set.add(Arrays.toString(tmp));
			for (int i = 0; i < M; i++) {
				sb.append(tmp[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!isUsed[i]) {
				tmp[cnt] = number[i];
				isUsed[i] = true;
				cal(cnt + 1);
				isUsed[i] = false;
			}
		}
	}
}
