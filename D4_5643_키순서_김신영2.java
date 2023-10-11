import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * D4_5643_키순서
 * ---------- ----------
 * 자기 자신 기준으로 큰 사람과 작은사람 찾기
 * 자기보다 큰 사람과 작은 사람에 포함되지 않는 사람이 있으면 자신의 키순서 알 수 없는 경우임
 * -> 인접리스트를 이용한 BFS로 큰사람, 작은사람 찾음
 * </pre>
 * @author 김신영
 *
 */

public class D4_5643_키순서_김신영2 {

	static int N, M, answer, gtCnt, ltCnt;
	static boolean[][] adj;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;

			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			adj = new boolean[N][N];

			// 인접리스트 만들기
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				
				adj[a][b] = true;
			}

			// 검사
			for (int i = 0; i < N; i++) {
				gtCnt = ltCnt = 0;
				
				gtDfs(i, new boolean[N]);
				ltDfs(i, new boolean[N]);
				
				if(gtCnt + ltCnt == N-1) {
					answer++;
				}
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

	// dfs로 방문할 수 있는 노드 검사
	public static void gtDfs(int cur, boolean[] isVisited) {
		isVisited[cur] = true;
		for(int i = 0; i < N; i++) {
			if(adj[cur][i] && !isVisited[i]) {
				gtCnt++;
				gtDfs(i, isVisited);
			}
		}
	}
	
	public static void ltDfs(int cur, boolean[] isVisited) {
		isVisited[cur] = true;
		for(int i = 0; i < N; i++) {
			if(adj[i][cur] && !isVisited[i]) {
				ltCnt++;
				ltDfs(i, isVisited);
			}
		}
	}
}
