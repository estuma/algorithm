import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 11,788KB	80ms
 * ---------- ----------
 * 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간
 * . 빈칸
 * * 물
 * X 돌
 * D 비버
 * S 고슴도치
 * ---------- ----------
 * BFS 이용
 * 고슴도치는 물이 이동할 방향으로 이동 불가능 -> 물 먼저 이동시키기
 * 큐 2개 사용하여 이동 (물, 고슴도치)
 * </pre>
 * 
 * @author 김신영
 *
 */

public class BJ_3055_탈출_김신영 {

	static int R, C, cnt;
	static char[][] arr;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static Queue<int[]> hedgehog = new ArrayDeque<>();
	static Queue<int[]> water = new ArrayDeque<>();
	static boolean[][] isVisited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new char[R][C];
		isVisited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (arr[i][j] == 'S') {
					hedgehog.add(new int[] { i, j });
					isVisited[i][j] = true;
					//arr[i][j] = '.';
				} else if (arr[i][j] == '*') {
					water.add(new int[] { i, j });
				}
			}
		}

		if (bfs()) {
			System.out.println(cnt);
		} else {
			System.out.println("KAKTUS");
		}

	}

	/**
	 * @return 목적지에 이동 가능한지
	 */
	public static boolean bfs() {
		cnt = 0;
		while (!hedgehog.isEmpty()) {
			cnt++;
			// 물 먼저 퍼지기
			int size = water.size();
			for (int s = 0; s < size; s++) {
				int[] curr = water.poll();
				for (int i = 0; i < 4; i++) {
					int newR = curr[0] + dr[i];
					int newC = curr[1] + dc[i];

					if (!isValid(newR, newC) || (arr[newR][newC] == 'D')) {
						continue;
					}

					arr[newR][newC] = '*';
					water.add(new int[] { newR, newC });
				}
			}

			// 고슴도치 이동
			size = hedgehog.size();
			for (int s = 0; s < size; s++) {
				int[] curr = hedgehog.poll();
				// arr[curr[0]][curr[1]] = '.';
				for (int i = 0; i < 4; i++) {
					int newR = curr[0] + dr[i];
					int newC = curr[1] + dc[i];
					if (!isValid(newR, newC) || isVisited[newR][newC]) {
						continue;
					}
					
					if (arr[newR][newC] == 'D') {
						return true;
					}
					
					//arr[newR][newC] = 'S';
					isVisited[newR][newC] = true;
					hedgehog.add(new int[] { newR, newC });
				}
			}

		}

		return false;
	}

	/**
	 * r, c가 범위 안이고 
	 * 벽이 아니고, 물이 아니면 이동 가능함
	 */
	public static boolean isValid(int r, int c) {
		if (r < 0 || c < 0 || r >= R || c >= C || arr[r][c] == 'X' || arr[r][c] == '*') {
			return false;
		}

		return true;
	}

}
