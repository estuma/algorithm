import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ_1516_게임개발 
 * https://www.acmicpc.net/problem/1516 
 * ---------- ---------- 
 * ** N개의 각 건물이 완성되기까지 걸리는 최소 시간을 출력한다. ** 
 * ---------- ---------- 
 * 위상정렬, 그래프
 * - 진입차수가 0인 노트부터 검사한다.
 * - 현재 노드의 이전 노드들 중 완성되는데 걸리는 시간이 가장 긴 시간을 내 최소시간으로 설정한다.
 * - 그 다음 노드의 진입차수를 1 빼준다.
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_1516_게임개발_김신영 {

	static int N;
	static int[] time, indegree;
	static boolean[] isVisited;
	static boolean[][] adj, adjRe;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		time = new int[N];
		indegree = new int[N];
		isVisited = new boolean[N];
		adj = new boolean[N][N]; // -> 단방향 그래프
		adjRe = new boolean[N][N]; // -> 단방향 그래프, 반대방향

		for (int i = 0; i < N; i++) {
			// 시간 , 먼저 지어져야하는 건물 번호, -1이 나오면 끝
			StringTokenizer st = new StringTokenizer(br.readLine());

			time[i] = Integer.parseInt(st.nextToken());
			while (true) {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				if (tmp == -2) {
					break;
				}

				adj[tmp][i] = true;
				adjRe[i][tmp] = true;
				indegree[i]++;
			}
		}

		Queue<Integer> q = new ArrayDeque<>();
		while (true) {
			// 진입차수가 0인 노드 큐에 추가, 방문하지 않은 노드만
			for (int i = 0; i < N; i++) {
				if (indegree[i] == 0 && !isVisited[i]) {
					q.add(i);
					isVisited[i] = true;
				}
			}

			// 종료조건
			if (q.isEmpty()) {
				break;
			}

			// 로직
			int qsize = q.size();
			for (int s = 0; s < qsize; s++) {
				int curr = q.poll();

				// 바로 앞 노드 중 최대시간으로 값 더해주기
				int max = 0;
				for (int i = 0; i < N; i++) {
					if (adjRe[curr][i]) {
						max = Math.max(max, time[i]);
					}
				}
				time[curr] += max;

				// 다음 노드의 진입차수 -1
				for (int i = 0; i < N; i++) {
					if (adj[curr][i] && i != curr) {
						indegree[i]--;
					}
				}
			}
		}

		// ----------- 정답 출력 ------------
		for (int i = 0; i < N; i++) {
			System.out.println(time[i]);
		}
	}

}
