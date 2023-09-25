import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_14502_연구소_김신영
 * 78,412KB	332ms
 * ----- 문제 -----
 * 연구소: N*M 직사각형 (3 <= 8)
 * 빈 칸:0, 벽:1, 바이러스:2 (2~10)
 * 벽을 3개 세워서 얻을 수 있는 안전 영역 크기의 최댓값 
 * 바이러스는 상하좌우 인접한 칸으로 퍼져나갈 수 있음
 * ----- 풀이 -----
 * 바이러스 위치 저장해두기, 세운 벽 위치 배열에 저장
 * 1. 재귀: 벽세우기 (0인 칸에 순서대로 모든 경우 벽 세우기)
 * 2. BFS: 안전구역 찾기 (상하좌우로 이동 가능한 모든 칸으로 바이러스 이동시키기)
 * </pre>
 * 
 * @author 김신영
 *
 */

public class BJ_14502_연구소_김신영 {

	public static int N, M, answer;
	public static char[][] arr;
	public static List<int[]> virus;
	public static int[][] wall;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new char[N][M];
		virus = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().replace(" ", "").toCharArray();
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == '2') {
					virus.add(new int[] { i, j });
				}
			}
		}

		// ---
		answer = 0;
		wall = new int[3][2];
		
		dfs(0, 0, 0);

		System.out.println(answer);

	}

	public static void dfs(int cnt, int r, int c) {
		if (cnt == 3) {
			cal();
			return;
		}

		for (int i = r; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(i == r && j < c) continue;
				
				if (arr[i][j] == '0') {
					wall[cnt][0] = i; wall[cnt][1] = j;

					arr[i][j] = '3';
					if (j == M - 1) {
						dfs(cnt + 1, i + 1, 0);
					} else {
						dfs(cnt + 1, i, j + 1);
					}
					arr[i][j] = '0';
				}
			}
		}

	}

	static int[] dirRow = { -1, 0, 1, 0 };
	static int[] dirCol = { 0, 1, 0, -1 };

	public static void cal() {
		boolean[][] visited = new boolean[N][M];

		for (int[] v : virus) {
			// bfs
			Queue<int[]> queue = new ArrayDeque<>();
			queue.add(v);

			while (!queue.isEmpty()) {
				int[] next = queue.poll();
				for (int i = 0; i < 4; i++) {
					int newR = next[0] + dirRow[i];
					int newC = next[1] + dirCol[i];

					if (newR >= N || newC >= M || newR < 0 || newC < 0)
						continue;
					
					if (arr[newR][newC] != '0' || visited[newR][newC])
						continue;

					visited[newR][newC] = true;
					queue.add(new int[] { newR, newC });
				}
			}
		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == '0' && !visited[i][j]) {
					cnt++;
				}
			}
		}
		
		answer = Math.max(answer, cnt);

	}

}
