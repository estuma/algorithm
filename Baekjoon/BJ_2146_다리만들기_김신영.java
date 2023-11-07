import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;


/**
 * <pre>
 * BJ_2146_다리만들기
 * 138,232KB	276ms
 * ---------- ----------
 * BFS로 섬 찾기 및 섬 연결
 * </pre>
 * @author 김신영
 *
 */
public class BJ_2146_다리만들기_김신영 {

	static int N, answer;
	static char[][] arr;
	static int[][] arrCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().replace(" ", "").toCharArray();
		}

		answer = Integer.MAX_VALUE;
		// 가장 짧은 다리의 길이 찾기
		// 1. 섬 찾기
		arrCnt = new int[N][N];
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == '1' && arrCnt[i][j] == 0) {
					bfs(i, j, cnt++);
				}
			}
		}

		// 2. 다리 연결해보기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arrCnt[i][j] != 0) {
					bridge(i, j, arrCnt[i][j]);
				}
			}
		}

		System.out.println(answer);
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	// 섬 찾기
	public static void bfs(int r, int c, int cnt) {
		boolean[][] isVisited = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { r, c });
		isVisited[r][c] = true;
		arrCnt[r][c] = cnt;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();

			for (int d = 0; d < 4; d++) {
				int newR = curr[0] + dr[d];
				int newC = curr[1] + dc[d];

				if (newR < 0 || newC < 0 || newR >= N || newC >= N || isVisited[newR][newC] || arr[newR][newC] == '0') {
					continue;
				}

				arrCnt[newR][newC] = cnt;
				isVisited[newR][newC] = true;
				queue.add(new int[] { newR, newC });
			}
		}

	}

	// 다리연결하기
	public static void bridge(int r, int c, int cnt) {
		boolean[][] isVisited = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { r, c });
		isVisited[r][c] = true;

		int day = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int s = 0; s < size; s++) {
				int[] curr = queue.poll();

				for (int d = 0; d < 4; d++) {
					int newR = curr[0] + dr[d];
					int newC = curr[1] + dc[d];

					// 범위 확인
					if (newR < 0 || newC < 0 || newR >= N || newC >= N) {
						continue;
					}

					// 방문한적 있고, 같은 섬이면
					if (isVisited[newR][newC] || arrCnt[newR][newC] == cnt) {
						continue;
					}

					// 다른 섬에 도달하면
					if (arrCnt[newR][newC] > 0) {
						answer = Math.min(answer, day);
						return;
					}

					isVisited[newR][newC] = true;
					queue.add(new int[] { newR, newC });
				}
			}

			day++;
		}

	}

}
