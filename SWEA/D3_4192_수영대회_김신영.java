import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * D3 4192 수영대회 https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AWKaCc-KABgDFAT2
 * ---------- ---------- ----------
 * NxN 안에서 가장 빠른 길 찾기 -> 도착지점까지 몇 조가 걸리는지
 * 1: 지나갈 수 없는 장애물
 * ---------- BFS 활용 ----------
 * Queue를 이용한 BFS
 * 
 * 
 * </pre>
 * 
 * @author 김신영
 *
 */

public class D3_4192_수영대회_김신영 {

	static int T, N, startRow, startCol, endRow, endCol;
	static int[][] arr;
	static boolean[][] isVisited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		int answer;

		for (int test_case = 1; test_case <= T; test_case++) {
			// 수영장의 크기
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			isVisited = new boolean[N][N];

			// 수영장 정보 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 시작 위치
			st = new StringTokenizer(br.readLine());
			startRow = Integer.parseInt(st.nextToken());
			startCol = Integer.parseInt(st.nextToken());

			// 도착 위치
			st = new StringTokenizer(br.readLine());
			endRow = Integer.parseInt(st.nextToken());
			endCol = Integer.parseInt(st.nextToken());

			answer = search();

			System.out.println("#" + test_case + " " + answer);
		}

	}

	public static int search() {
		Queue<int[]> queue = new ArrayDeque<>();
		int[] dirRow = { -1, 0, 1, 0 };
		int[] dirCol = { 0, 1, 0, -1 };
		isVisited[startRow][startCol] = true;
		queue.offer(new int[] { startRow, startCol });

		int seconds = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] tmp = queue.poll();
				
				if (tmp[0] == endRow && tmp[1] == endCol) {
					return seconds;
				}
				
				for (int dir = 0; dir < 4; dir++) {
					int[] next = { tmp[0] + dirRow[dir], tmp[1] + dirCol[dir] };
					if (isValid(next[0], next[1])) {

						isVisited[next[0]][next[1]] = true;
						queue.offer(next);
					}
				}
			}
			
			seconds++;
		}

		return -1;
	}

	/**
	 * 
	 * @param r
	 * @param c
	 * @return
	 */
	public static boolean isValid(int r, int c) {
		if (r >= 0 && r < arr.length && c >= 0 && c < arr.length) {
			if (!isVisited[r][c] && arr[r][c] == 0) {
				return true;
			}
		}
		return false;
	}

}
