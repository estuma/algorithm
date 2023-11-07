import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_4485_녹색옷을입은애가젤다지?
 * 23,496KB	236ms
 * ---------- ----------
 * 링크는 도둑루피만 가득한 N * N 크기 동굴의 0,0에 위치
 * 잃는 금액을 최소로 하여 (N-1, N-1)까지 이동해야함
 * 이동은 상하좌우 1칸씩 이동 가능
 * ---------- ----------
 * BFS: 사방탐색, 메모이제이션: 잃는 루피값 저장
 * -> 현재 위치에서 새로운 위치로 이동할 때의 잃는 루피가 더 적을때 값 갱신하고 큐에 삽임
 * </pre>
 * 
 * @author 김신영
 *
 */

public class BJ_4485_녹색옷입은애가젤다지_김신영 {

	static int N, answer;
	static int[][] map, cntMap;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tc = 1;
		StringBuilder sb = new StringBuilder();
		while (true) {
			answer = 0;
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}

			// 입력
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 잃은 금액 계산할 배열 초기화: 최솟값 찾을것이므로 최댓값으로 설정
			cntMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					cntMap[i][j] = Integer.MAX_VALUE;
				}
			}

			// 계산부
			Queue<int[]> queue = new ArrayDeque<>();
			queue.add(new int[] { 0, 0 });
			cntMap[0][0] = map[0][0];

			while (!queue.isEmpty()) {
				int[] curr = queue.poll();

				for (int d = 0; d < 4; d++) {
					int newR = curr[0] + dr[d];
					int newC = curr[1] + dc[d];

					if (newR < 0 || newC < 0 || newR >= N || newC >= N) {
						continue;
					}

					// curr을 통해 newR, newC로 가는 경우 잃는 금액
					int tmp = cntMap[curr[0]][curr[1]] + map[newR][newC];
					
					// 새로 만들어진 잃은 금액이 기존의 값 이상인 경우 더이상 탐색하지 않음
					if (cntMap[newR][newC] <= tmp) {
						continue;
					}

					// 값 갱신
					cntMap[newR][newC] = tmp;
					queue.add(new int[] { newR, newC });
				}
			}
			
			for(int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(cntMap[i]));
			}

			answer = cntMap[N - 1][N - 1];
			
			// 출력
			sb.append("Problem ").append(tc++).append(": ").append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

}
