import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <pre>
 * 파핑파핑 지뢰찾기
 * 39,300 kb 213 ms
 * 최소로 클릭하여 열기 위해서는 0인 공간을 먼저 열어야 한다.
 * 사방에 지뢰가 없는 곳을 찾아 먼저 열고 이곳을 클릭하여 열릴 수 있는 곳을 보두 연쇄적으로 열어준다.
 * 이 작업을 다 한 뒤 남은 공간은 클릭하여 1칸만 열리는 곳이므로 배열 순회하며 열리지 않은곳을 다 열어준다.
 * </pre>
 * @author 김신영
 *
 */

public class D4_1868_파핑파핑지뢰찾기_김신영 {

	static int N, cnt;
	static char[][] arr;
	static boolean[][] isOpen, isVisited;
	static int[] dirRow = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dirCol = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new char[N][N];
			for (int i = 0; i < N; i++) {
				arr[i] = br.readLine().toCharArray();
			}

			// 8방검사하여 지뢰 없는곳 먼저 열기
			cnt = 0;
			isOpen = new boolean[N][N];
			isVisited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// [i][j]에서 검사 주변에 지뢰 있는지 검사
					if (arr[i][j] == '.' && !isVisited[i][j] && !isMine(i, j)) {
						open(i, j);
						
						cnt++;
					}
				}
			}

			// 남은곳 한칸씩 열기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!isOpen[i][j] && arr[i][j] == '.') {
						cnt++;
					}
				}
			}

			System.out.println("#" + tc + " " + cnt);
		}
	}

	public static void open(int r, int c) {
		// r, c 기준으로 주변에 지뢰 없는곳 다 열기
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { r, c });
		isVisited[r][c] = true;
		isOpen[r][c] = true;
		zeroOpen(r, c);
		while (!queue.isEmpty()) {
			int currRow = queue.peek()[0];
			int currCol = queue.poll()[1];

			for (int dir = 0; dir < 8; dir++) {
				int newRow = currRow + dirRow[dir];
				int newCol = currCol + dirCol[dir];
				if (isValid(newRow, newCol) && !isVisited[newRow][newCol]) {
					isVisited[newRow][newCol] = true;
					if (!isMine(newRow, newCol)) {
						queue.add(new int[] { newRow, newCol });
						zeroOpen(newRow, newCol);
					}
				}
			}
		}
	}

	public static boolean isValid(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N) {
			return true;
		}
		return false;
	}

	// 주변에 지뢰 있는지 체크하는 함수
	// true: 지뢰 있음, false: 지뢰 없음
	public static boolean isMine(int r, int c) {
		for (int dir = 0; dir < 8; dir++) {
			int newRow = r + dirRow[dir];
			int newCol = c + dirCol[dir];
			if (isValid(newRow, newCol)) {
				if (arr[newRow][newCol] == '*') {
					return true;
				}
			}
		}
		return false;
	}

	public static void zeroOpen(int r, int c) {
		for (int dir = 0; dir < 8; dir++) {
			int newRow = r + dirRow[dir];
			int newCol = c + dirCol[dir];
			if (isValid(newRow, newCol)) {
				isOpen[newRow][newCol] = true;
			}
		}
		isOpen[r][c] = true;
	}

}
