import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <pre>
 * BJ_10026_적록색약 https://www.acmicpc.net/problem/10026
 * 12604KB	88ms
 * ---------- ----------
 * 재귀 -> 같은 색인 경우 계속 재귀실행해준다.
 * 끝나면 계속 다음 요소 검사하면서 재귀 실실행
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_10026_적록색약_김신영 {

	static int N;
	static char[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		int answer1 = 0, answer2 = 0;
		
		boolean[][] isVisited = new boolean[N][N];
		boolean[][] isVisited2 = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 적록색약 아닌경우
				if (!isVisited[i][j]) {
					answer1++;
					isVisited[i][j] = true;
					dfs(i, j, isVisited);
				}
				// 적록색약인 경우
				if (!isVisited2[i][j]) {
					answer2++;
					isVisited2[i][j] = true;
					dfs2(i, j, isVisited2);
				}
			}
		}

		System.out.println(answer1 + " " + answer2);
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	// 색약 아닌 경우
	public static void dfs(int r, int c, boolean[][] isVisited) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			// 다음 요소가 같은 색이면 재귀 실행
			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (!isVisited[nr][nc] && arr[r][c] == arr[nr][nc]) {
					isVisited[nr][nc] = true;
					dfs(nr, nc, isVisited);
				}
			}
		}
	}

	// 적록색약의 경우
	public static void dfs2(int r, int c, boolean[][] isVisited) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (!isVisited[nr][nc]) {
					// R, G가 인접한 경우 동일하게 취급
					switch(arr[r][c]) {
					case 'R':
					case 'G':
						switch(arr[nr][nc]) {
						case 'R':
						case 'G':
							isVisited[nr][nc] = true;
							dfs2(nr, nc, isVisited);
							break;
						}
						break;
					default:
						// 나머지 경우는 같은 경우만 같은 구역으로
						if (arr[r][c] == arr[nr][nc]) {
							isVisited[nr][nc] = true;
							dfs(nr, nc, isVisited);
						} 
						break;
					}
				}
			}
		}
	}
}
