import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 32572KB	172ms
 * 턴마다 궁수는 적 하나를 공격할 수 있음
 * 궁수는 가장 가까운 적 공격
 * 거리가 동일하면 왼쪽 우선
 * ---------- ----------
 * 1. 조합으로 궁수 셋 배치할 수 있는 경우 찾기
 * 2. 찾은 뒤 BFS를 통해 좌->상->우 순서로 탐색하여 가장 먼저 나오는 적 공격하도록 설정
 * 3. 공격할 적 모두 찾은 뒤 한번에 공격 (공격당한적은 표시하여 두 번 카운트되지 않도록 함)
 * 4. 마지막으로 계산된 값과 이전 max값 비교하여 더 큰값으로 갱신하기
 * ----------
 * 원본 배열 그대로 사용 + boolean배열 사용
 * -> 가까운 적 찾을때도 boolean 체크 해줘야함
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_17135_캐슬디펜스_김신영 {

	static int N, M, D, enemyCnt, max, cnt;
	static char[][] arr;
	static int[] select;
	static List<int[]> attackList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		arr = new char[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().replace(" ", "").toCharArray();
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == '1') {
					enemyCnt++;
				}
			}
		}

		select = new int[3];
		attackList = new ArrayList<>();
		max = 0;
		cnt = 0;

		combination(0, 0);

		System.out.println(max);

	}

	// 조합 만들기
	public static void combination(int cnt, int start) {
		if (cnt == 3) {
			// 조합 다 만듦 -> 계산하기
			simulation();
			return;
		}

		for (int i = start; i < M; i++) {
			select[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}

	public static void simulation() {
		cnt = 0;
		int round = 0;
		boolean[][] killed = new boolean[N][M];
		
		while (round < N) {
			// 1. 가까운 적 찾기
			for (int i = 0; i < 3; i++) {
				closeEnemy(select[i], round, killed);
			}

			// 2. 찾은 적 공격하기
			for (int i = 0; i < attackList.size(); i++) {
				if (!killed[attackList.get(i)[0]][attackList.get(i)[1]]) {
					killed[attackList.get(i)[0]][attackList.get(i)[1]] = true;
					cnt++;
				}
			}
			
			attackList.clear();
			round++;
		}
		
		max = Math.max(max, cnt);

	}

	public static void closeEnemy(int position, int round, boolean[][] killed) {
		// 3개방향, 좌, 상, 우
		int[] dirRow = { 0, -1, 0 };
		int[] dirCol = { -1, 0, 1 };

		int currR = N - 1 - round;
		int currC = position;

		boolean[][] isVisited = new boolean[N][M];
		Queue<int[]> queue = new ArrayDeque<>();

		// 첫번째 칸
		queue.offer(new int[] { currR, currC });
		isVisited[currR][currC] = true;
		
		// 바로 위인 거리1부터 시작
		int distance = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				int[] tmp = queue.poll();
				
				// 적을 찾으면 공격할 리스트에 추가하고 종료
				if (arr[tmp[0]][tmp[1]] == '1' && !killed[tmp[0]][tmp[1]]) {
					attackList.add(new int[] { tmp[0], tmp[1] });
					return;
				}
				
				// 3개 방향 검사
				for (int i = 0; i < 3; i++) {
					int newRow = tmp[0] + dirRow[i],  newCol = tmp[1] + dirCol[i];
					if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < M) {
						if (!isVisited[newRow][newCol]) {
							isVisited[newRow][newCol] = true;
							queue.add(new int[] { newRow, newCol });
						}
					}
				}
			}
			
			distance++;
			
			// 거리가 D이상되면 공격X
			if (distance > D) {
				return;
			}
		}
	}

}