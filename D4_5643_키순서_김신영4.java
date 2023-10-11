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

 * </pre>
 * @author 김신영
 *
 */

public class D4_5643_키순서_김신영4 {

	static int N, M, answer, cnt;
	static int[][] adj;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			adj = new int[N+1][N+1];
			
			// 모든 행의 0열, 자신보다 큰 학생수 메모함
			for(int i = 0; i <= N; i++) {
				adj[i][0] = -1;
			}

			// 인접리스트 만들기
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				
				adj[a][b] = 1;
			}

			// 검사
			answer = 0;
			for (int k = 0; k < N; k++) {
				if(adj[k][0] == -1) {
					dfs(k);	// 탐색되지 않은 학생만 탐색
				}
			}
			
			// 메모된ㄴ 인접행렬의 상탱를 이용하여 자신보다 작은 학생수 카운팅
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					adj[0][j] += adj[i][j];
				}
			}
			
			for(int i = 1; i <= N; i++) {
				if(adj[i][0] + adj[0][i] == N-1) {
					answer++;
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

	// dfs로 방문할 수 있는 노드 검사
	public static void dfs(int k) {
		// 자신의 인접행렬 처리
		for(int i = 1; i <= N; i++) {
			if(adj[k][i] == 1) { 	// 나보다 크고
				if(adj[i][0] == -1) {	// 미탐색 상태
					// 탐색하러 ㄱㄱㄱ
					dfs(i);
				}
				
				// 탐색완료된 상태 || 이미 탐색한 대상인 경우
				// i보다 큰 대상이 1명이상이면 k보다 큰 간접대상이 존재할 수 있는 상황
				// --> 새롭게 알게된 간접관계를 나와의 직접 관계로 인접행렬에 반영
				if(adj[i][0] > 0) {
					for(int j = 1; j <= N; j++) {
						if(adj[i][j] == 1) {
							adj[k][j] = 1;
						}
					}
				}
			}
		}
		
		// 자신의 인접행렬을 모두 처리 --> 직/간접 관계가 인접행렬에 모두 반영됨
		// 자신보다 큰 학생수를 카운팅해서 메모하고 리턴하기
		int cnt = 0;
		for(int i = 1; i <= N; i++) {
			cnt += adj[k][i];
		}
		
		adj[k][0] = cnt;
	}
	
}
