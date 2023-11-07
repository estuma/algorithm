import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_16236_아기상어
 * ---------- ----------
 * 아기상어 초기 크기 2
 * NxN 크기 공간, 물고기 M 마리 + 아기상어 (2 <= N <= 20)
 * 아기상어는 1초에 1칸씩 이동
 * 아기상어는 자기보다 큰 물고기가 있는 곳으로는 지나갈 수 X
 * 자기보다 작은 물고기는 먹을 수 있음
 * + 이동 결정 방법
 * 1. 더 이상 먹을 수 있는 물고기가 없다면 엄마상어에게 도움 요청
 * 2. 먹을 수 있는 물고기가 있다면 가장 가까운 물고기부터 먹으러 간다.
 * ++ 아기상어는 자기와 같은 크기의 물고기를 먹으면 크기가 1 증가한다.
 * +++ 거리가 동일하다면 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
 * => 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지
 * 입력
 * 0: 빈칸
 * 1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
 * 9: 아기상어의 위치
 * ---------- ----------
 * bfs 이용
 * 큐를 사용하여 초별로 갈 수 있는 위치를 검사한다.
 * 아기상어는 가까운 물고기 중 위에 있는 물고기를 먼저 먹고, 
 * 높이가 같다면 왼쪽에 있는 물고기를 먼저 먹기 때문에 우선순위 큐를 사용하여 우선순위가 높은 곳을 먼저 검사한다.
 * 만약 해당 위치에 먹을 수 있는 물고기가 있다면 아기상어를 그 위치로 이동시킨다.
 * 아기상어는 자신의 크기와 같은 물고기를 먹을때마다 1 자라므로 자랄때마다 먹은 물고기의 수를 세어준다.
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_16236_아기상어_김신영 {

	static int n, fish = 0, sizeCnt = 0;
	static int[][] arr;
	static int[] babyShark = new int[3];
	static int[] fishes = new int[7];
	static boolean[][] isVisited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int answer = 0;
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		isVisited = new boolean[n][n];

		// 공간 상태 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					babyShark[0] = i;
					babyShark[1] = j;
					babyShark[2] = 2;
					arr[i][j] = 0;
				} else if (arr[i][j] != 0) {
					fish++;
					fishes[arr[i][j]]++;
				}
			}
		}

		// 한마리씩 잡아먹으면서 이동
		for (int i = 0; i < fish; i++) {
			isVisited = new boolean[n][n];
			int time = search();
			if (time == 0) {
				break;
			} else {
				answer += time;
			}
			int max = Math.min(babyShark[2], fishes.length);
			int tmp = 0;
			for (int j = 1; j < max; j++) {
				tmp += fishes[j];
			}

			if (tmp == 0) {
				break;
			}
		}

		System.out.println(answer);
	}

	public static int search() {
		Queue<int[]> queue = new ArrayDeque<>();
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {	// 같은 높이에 있을 때 왼쪽이 먼저
					return o1[1] - o2[1];
				} else {				// 높이가 다를 때 위에있는게 먼저
					return o1[0] - o2[0];
				}
			}
		});

		int[] dirRow = { -1, 0, 0, 1 };
		int[] dirCol = { 0, -1, 1, 0 };

		int time = 0;
		queue.offer(babyShark);
		isVisited[babyShark[0]][babyShark[1]] = true;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				pq.offer(queue.poll());
			}
			for (int i = 0; i < size; i++) {
				int[] tmp = pq.poll();
				// 자기보다 작은 물고기이면 잡아먹기
				// 종료조건
				int curr = arr[tmp[0]][tmp[1]];
				if (curr > 0 && curr < babyShark[2]) {
					// 현재 위치의 물고기 크기 개수 -1
					fishes[curr]--;
					// 커지기 위해 필요한 물고기 먹은 개수
					sizeCnt++;
					// 물고기 먹은 개수가 현재 아기상어의 크기와 같으면 아기상어 1 커짐
					if (sizeCnt == babyShark[2]) {
						babyShark[2]++;
						sizeCnt = 0;
					}
					// 먹었으니 0
					arr[tmp[0]][tmp[1]] = 0;
					// 아기 상어 옮기기
					babyShark[0] = tmp[0];
					babyShark[1] = tmp[1];

					return time;
				}

				
				// 사방 검사
				for (int dir = 0; dir < 4; dir++) {
					int newRow = tmp[0] + dirRow[dir];
					int newCol = tmp[1] + dirCol[dir];
					if (isValid(newRow, newCol)) {
						isVisited[newRow][newCol] = true;
						queue.offer(new int[] { newRow, newCol });
					}
				}
			}
			
			// 남은 위치 다시 큐에 넣기
			size = pq.size();
			for(int j = 0; j < size; j++) {
				queue.offer(pq.poll());
			}

			time++;
		}

		return 0;
	}

	// 새로 만들어진 좌표가 배열 내인지 확인하고, 이동할 수 있는 위치인지 확인
	public static boolean isValid(int r, int c) {
		if (r >= 0 && r < arr.length && c >= 0 && c < arr.length) {
			if (arr[r][c] <= babyShark[2] && !isVisited[r][c]) {
				return true;
			}
		}

		return false;
	}
}
