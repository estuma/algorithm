import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * 등산로 / 완전탐색
 * 가장 높은 봉우리를 찾아서 봉우리 수만큼 등산로 길이를 찾아준다.
 * 사방을 탐색하며, 만약 자신보다 낮은 봉우리라면 그 방향으로 이동한다.
 * 만약에 자신보다 높은 봉우리이지만, 현재 공사를 한 적이 없고, 공사를 통해 높이를 나보다 낮출 수 있다면 
 * 다음 봉우리의 높이를 현재 내 위치의 높이보다 1 작게 깎아준다.
 * 이 과정 계속 반복.
 * </pre>
 * 
 * @author 김신영
 *
 */

public class SW_1949_등산로_김신영 {

	static int N, K, maxLength;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			maxLength = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			// 산 입력
			arr = new int[N][N];
			int maxHight = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					maxHight = Math.max(maxHight, arr[i][j]);
				}
			}

			// 가장 높은 봉우리 찾기
			List<int[]> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == maxHight) {
						list.add(new int[] { i, j });
					}
				}
			}

			// 가장 높은 봉우리 차례로 검사
			for (int i = 0; i < list.size(); i++) {
				boolean[][] isVisited = new boolean[N][N];
				isVisited[list.get(i)[0]][list.get(i)[1]] = true;
				dfs(1, list.get(i)[0], list.get(i)[1], isVisited, false);
			}

			System.out.println("#" + tc + " " + maxLength);
		}
	}

	public static void dfs(int length, int r, int c, boolean[][] isVisited, boolean isDig) {
		// 높이 갱신
		maxLength = Math.max(maxLength, length);
		
		int[] dirRow = { -1, 0, 1, 0 };
		int[] dirCol = { 0, 1, 0, -1 };

		for (int dir = 0; dir < 4; dir++) {
			int newRow = r + dirRow[dir];
			int newCol = c + dirCol[dir];
			// 가능한경우 검사
			if (isValid(newRow, newCol, arr, isVisited)) {
				// 높이가 낮으면 이동할 수 있음
				if (arr[newRow][newCol] < arr[r][c]) {
					isVisited[newRow][newCol] = true;
					dfs(length + 1, newRow, newCol, isVisited, isDig);
					isVisited[newRow][newCol] = false;

				} else {
					if (!isDig) { // 땅 판적 없는 경로이면
						// curr보다 작아지면 됨!
						// 다음 위치(nextRow, nextCol)파보기 / 최대 K만큼 파서 이동할 수 있는지
						if (arr[r][c] + K > arr[newRow][newCol]) {
							// 현재 위치보다 1만 작은 값으로 갱신
							// 다시 복구시켜줘야하므로 값 저장
							int tmp = arr[newRow][newCol];
							// 공사 후 방문처리
							arr[newRow][newCol] = arr[r][c] - 1;
							isVisited[newRow][newCol] = true;
							dfs(length + 1, newRow, newCol, isVisited, true);
							// 다시 원복
							isVisited[newRow][newCol] = false;
							arr[newRow][newCol] = tmp;
						}
					}
				}
			}
		}
	}

	/**
	 * 배열범위 안이고, 방문한적 없는지 검사
	 * 
	 * @param r         row
	 * @param c         column
	 * @param arr       부지높이저장배열
	 * @param isVisited 방문여부저장배열
	 * @return r, c로 이동 가능한지 여부
	 */
	public static boolean isValid(int r, int c, int[][] arr, boolean[][] isVisited) {
		if (r >= 0 && r < arr.length && c >= 0 && c < arr[0].length) {
			if (!isVisited[r][c]) {
				return true;
			}
		}
		return false;
	}

}
