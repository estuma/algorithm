import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_11265_끝나지않는파티
 * 25,460KB	368ms
 * ---------- ----------
 * A에서 B로가는 최단경로가 필요 -> 플로이드 워셜 알고리즘 사용 O(500^3)
 * </pre>
 * @author 김신영
 *
 */

public class BJ_11265_끝나지않는파티_김신영 {
	
	static int N, M;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// i에서 j로 가는 최단거리 구하기
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
		
		// 서비스 요청
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()) - 1;	// 손님 위치
			int B = Integer.parseInt(st.nextToken()) - 1;	// 파티장 위치
			int C = Integer.parseInt(st.nextToken());	// 파티가 열리는데 걸리는 시간
			
			if(arr[A][B] <= C) {
				sb.append("Enjoy other party\n");
			} else {
				sb.append("Stay here\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
