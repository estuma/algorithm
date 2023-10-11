import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * D4_7465_창용마을무리의개수
 * 25,268KB 132ms
 * ---------- ----------
 * N명의 사람 (1번~N번)
 * 서로를 알고 있을수도, 아닐수도 있음
 * 서로 아는 관계이거나 몇 사람을 걸쳐서 알 수 있는 관계이면 하나의 무리라고한다.
 * 몇 개의 무리가 있는지 계산하는 프로그램 작성
 * ---------- ----------
 * BFS 이용하여 풀이
 * -> 인접행렬 만들고 1번 사람부터 알 수 있는 사람 모두 찾기
 * </pre>
 * 
 * @author 김신영
 *
 */
public class D4_7465_창용마을무리의개수_김신영 {

	static int N, M, answer;
	static boolean[] isVisited;
	static boolean[][] adj;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// 인접행렬 만들기
			adj = new boolean[N][N];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken()) - 1;
				int from = Integer.parseInt(st.nextToken()) - 1;

				adj[to][from] = adj[from][to] = true;
			}

			answer = 0;
			isVisited = new boolean[N];
			for (int i = 0; i < N; i++) {
				if (isVisited[i]) { // 방문한 적 있으면 넘어가기
					continue;
				}

				bfs(i);
				answer++;
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

	public static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		isVisited[start] = true;

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			for (int i = 0; i < N; i++) {
				if (adj[curr][i] && !isVisited[i]) {
					isVisited[i] = true;
					queue.add(i);
				}
			}
		}
	}

}
