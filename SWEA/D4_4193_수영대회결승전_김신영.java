import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * D4 4193 수영대회 결승전 https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AWKaG6_6AGQDFARV
 * 18,432 kb, 113 ms
 * ---------- ---------- ----------
 * NxN 안에서 가장 빠른 길 찾기, 몇 초 만에 골인할 수 있는지
 * 1: 지나갈 수 없는 장애물
 * 2: 2, 5, 8, ... 초 간격으로 지나갈 수 있는 장애물
 * 2 위로 한 번 통과한 경우 계속 머물러있을 수 있음
 * ---------- BFS 활용 ----------
 * 큐를 활용하여 검사한다.
 * 시작 위치를 기준으로 위부터 시계방향으로 탐색해준다.
 * 같은 길을 계속 지나가는것을 막기 위해 방문했는지 확인한다.
 * -- 가장 짧은 시간을 찾는 것.
 * 다음 초에서 탐색할 수 있는 모든 경우를 큐에 넣고 탐색 -> 이 로직을 계속 반복
 * ++
 * 탐색할 수 있는 경우:
 * 1. 방문한 적 없는 길이면서 
 * 2. 경기장 배열의 값이 0(길) 또는 2(소용돌이)인 경우
 * ++
 * 지나갈 수 있는 장애물이 있기 때문에 현재 시간에 통과할 수 있는지 검사 후
 * 1. 소용돌이가 있으면(통과할 수 없으면) 다시 큐에 넣고 다음 초에 다시 검사
 * 2. 소용돌이가 없다면(통과할 수 있으면) 계속 탐색한다.
 * </pre>
 * 
 * @author 김신영
 *
 */

public class D4_4193_수영대회결승전_김신영 {

	static int T, N, startRow, startCol, endRow, endCol;
	static int[][] arr;
	static boolean[][] isVisited;
	static int seconds;

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

		seconds = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			// 현재 큐에 들어있는 수 만큼 검사 -> n초일 때 경우를 한번에 검사할 수 있음!
			for (int i = 0; i < size; i++) {
				int[] tmp = queue.poll();

				// 목적지에 도착하면 현재 초를 반환한다.
				if (tmp[0] == endRow && tmp[1] == endCol) {
					return seconds;
				}

				for (int dir = 0; dir < 4; dir++) {
					// 다음 방문 위치 생성
					int[] next = { tmp[0] + dirRow[dir], tmp[1] + dirCol[dir] };
					// 가능한 위치인지 검사
					if (isValid(next[0], next[1])) {
						// 0: 길 -> 큐에 넣고 계속 검사
						if (arr[next[0]][next[1]] == 0) {
							isVisited[next[0]][next[1]] = true;
							queue.offer(next);
						} 
						// 2: 소용돌이 -> 현재초 % 3 의 값이 2인 경우에만 지나갈 수 있다.  (2, 5, 8, 11초 ...)
						else if (arr[next[0]][next[1]] == 2) {
							// 지나갈 수 있을때까지 기다리기 -> 현재 위치를 다시 큐에 넣음
							if (seconds % 3 != 2) {
								queue.offer(tmp);
							} else {
								// 지나갈 수 있는 시간이 된다면 큐에 넣고 탐색 계속
								isVisited[next[0]][next[1]] = true;
								queue.offer(next);
							}
						}
					}
				}
			}

			seconds++;
		}

		return -1;
	}

	// row, col 값이 배열 내이고, 해당 위치를 방문한 적 없는지 확인하는 함수
	public static boolean isValid(int r, int c) {
		// 배열 범위 내인지!
		if (r >= 0 && r < arr.length && c >= 0 && c < arr.length) {
			// 방문한 적 없는지!
			if (!isVisited[r][c]) {
				return true;
			}
		}
		return false;
	}

}
