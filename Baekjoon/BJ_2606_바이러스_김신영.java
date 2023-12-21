import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_2606_바이러스_김신영 | https://www.acmicpc.net/problem/2606
 * 11,716KB	80ms
 * ----- ----- ----- 
 * BFS
 * ----- ----- -----
 * 1번 컴퓨터가 바이러스에 걸렸을 때 이 컴퓨터를 통해 감염될 수 있는 컴퓨터의 수 구하기 
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_2606_바이러스_김신영 {

	static int N, M;
	static boolean[] isVisited;
	static boolean[][] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 컴퓨터 수
		M = Integer.parseInt(br.readLine()); // 컴퓨터 번호 쌍

		adj = new boolean[N][N];
		isVisited = new boolean[N];

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;

			adj[from][to] = adj[to][from] = true;
		}

		System.out.println(bfs(0));
	}

	public static int bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		isVisited[start] = true;

		int answer = 0;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			
			for(int i = 0; i < N; i++) {
				if(adj[curr][i] && !isVisited[i]) {
					queue.add(i);
					isVisited[i] = true;
					answer++;
				}
			}
		}

		return answer;
	}
}
