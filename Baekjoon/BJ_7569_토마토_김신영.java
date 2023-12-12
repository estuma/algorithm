import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 97,944KB		584ms
 * ---------- ----------
 * 1: 익은토마토
 * 0: 안익은토마토
 * -1: 토마토 없음
 * ---------- ----------
 * 익어있는 토마토를 큐에 넣고 해당 토마토의 주변 토마토 익히기
 * 익은 토마토가 없으면 끝내기 
 * </pre>
 * 
 * @author 김신영
 *
 */

public class BJ_7569_토마토_김신영 {

	static int M, N, H, answer;
	static int[][][] arr;
	static Queue<int[]> queue = new ArrayDeque<>();
	static int[] dh = { -1, 1, 0, 0, 0, 0 };
	static int[] dr = { 0, 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		arr = new int[H][N][M];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					arr[i][j][k] = Integer.parseInt(st.nextToken());
					if (arr[i][j][k] == 1) {
						queue.add(new int[] { i, j, k, 0 });
					}
				}
			}
		}

		answer = mature();

		System.out.println(answer);

	}

	public static int mature() {
		int currDay = 0;
		while (!queue.isEmpty()) {
			// 주변 토마토 익히기
			int[] curr = queue.poll();
			for (int d = 0; d < 6; d++) {
				int newH = curr[0] + dh[d];
				int newR = curr[1] + dr[d];
				int newC = curr[2] + dc[d];
				currDay = curr[3];

				// 범위 확인
				if (newH < 0 || newH >= H || newR < 0 || newR >= N || newC < 0 || newC >= M) {
					continue;
				}

				// 익힐 수 있는 토마토가 있으면 익히고 큐에 추가
				if (arr[newH][newR][newC] == 0) {
					arr[newH][newR][newC] = 1;
					queue.add(new int[] { newH, newR, newC, curr[3] + 1 });
				}
			}
		}

		// 익은 토마토가 없는데 남은 토마토가 있다면 -1
		if (count() > 0) {
			return -1;
		}

		// 토마토 다 익은 경우
		return currDay;
	}

	// 안익은 토마토 개수 세기
	public static int count() {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (arr[i][j][k] == 0) {
						cnt++;
					}
				}
			}
		}
		return cnt;
	}

}
