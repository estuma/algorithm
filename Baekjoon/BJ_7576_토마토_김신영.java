import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_7576_토마토_김신영 
 * https://www.acmicpc.net/problem/7576
 * 
 * 102548KB	544ms
 * 
 * 익지 않은 토마토 개수 세기 -> 토마토가 익을때마다 1씩 감소시켜 남은 값으로 토마토가 전부 익었는지 익지 않았는지 판단
 * 익은 토마토 위치 찾기 -> 큐에 넣고 하나씩 꺼내며 사방검사, 0(익지 않은 토마토)을 만나면 그 토마토를 익히고 0이 있던 위치를 큐에 다시 넣어준다 -> 반복
 * </pre>
 * @author 김신영
 *
 */

public class BJ_7576_토마토_김신영 {

	static int N, M;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // column
		N = Integer.parseInt(st.nextToken()); // row

		arr = new int[N][M];

		Queue<int[]> queue = new ArrayDeque<>();
		int remain = 0;

		// 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					queue.add(new int[] { i, j });
				} else if (arr[i][j] == 0) {
					remain++;
				}
			}
		}

		// 토마토 익히기
		int answer = 0;
		boolean[][] isVisited = new boolean[N][M];
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		while (!queue.isEmpty()) {
			int qsize = queue.size();

			for (int s = 0; s < qsize; s++) {
				int[] curr = queue.poll();

				// 토마토 익히기 -> 4방향 검사
				for (int i = 0; i < 4; i++) {
					int newR = curr[0] + dr[i];
					int newC = curr[1] + dc[i];

					if (newR < 0 || newR >= N || newC < 0 || newC >= M) {
						continue;
					}

					if (arr[newR][newC] == 0 && !isVisited[newR][newC]) {
						queue.add(new int[] { newR, newC });
						isVisited[newR][newC] = true;
						remain--;
					}
				}
			}

			// 더이상 남은게 없으면 날짜 더하지 않고 종료
			if(queue.isEmpty()) {
				break;
			}
			
			answer++;
		}

		// 결과 출력
		if (remain == 0) {
			System.out.println(answer);
		} else {
			System.out.println(-1);
		}
	}

}
