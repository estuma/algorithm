import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_15652_N과M(4)_김신영 | https://www.acmicpc.net/problem/15652
 * 22292KB	572ms
 * ----- ----- -----
 * 
 * </pre>
 * @author 김신영
 *
 */

public class BJ_15652_N과M4_김신영 {

	static int N, M;
	static int[] order;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // N개 중
		M = Integer.parseInt(st.nextToken()); // M개 
		
		order = new int[M];
		
		cal(0, 1);
	}
	
	public static void cal(int cnt, int start) {
		if(cnt == M) {
			for(int i = 0; i < M; i++) {
				System.out.print(order[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = start; i <= N; i++) {
			order[cnt] = i;
			cal(cnt+1, i);
		}
	}

}
