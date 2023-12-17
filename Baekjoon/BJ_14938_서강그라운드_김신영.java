import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_14938_서강그라운드_김신영 | https://www.acmicpc.net/problem/14938
 * 12,288KB	108ms
 * ----- ----- -----
 * 플로이드워셜
 * ----- ----- -----
 * 1 <= L <= 15 지역간 거리
 * 1 <= M <= 15 수색 범위
 * 얻을 수 있는 최대 아이템 개수 구하기
 * ----- ----- -----
 * 1. 노드 -> 노드 모든 거리 구하기
 * 2. 시작노드에서 범위 M 안에 있는 아이템 모두 더하기
 * 3. Max값 갱신 
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_14938_서강그라운드_김신영 {

	static int N, M, R, max;
	static int[] item;
	static int[][] arr;
	static boolean[] isVisited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		item = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}

		arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i != j) {
					arr[i][j] = 999_999_999;
				}
			}
		}
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int length = Integer.parseInt(st.nextToken());

			arr[from][to] = arr[to][from] = length;
		}
		
		// 플루이드워셜
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
		
		// i에서 거리가 M이내로 갈 수 있는 곳의 item개수만 더하기
		int answer = 0;
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < N; j++) {
				if(arr[i][j] <= M) {
					sum += item[j];
				}
			}
			answer = Math.max(answer, sum);
		}
		
		System.out.println(answer);
	}

}
