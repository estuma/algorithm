import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_1149_RGB거리_김신영 | https://www.acmicpc.net/problem/1149
 * 12116KB	96ms
 * ----- ----- -----
 * DP 
 * ----- ----- -----
 * 뒤에서부터 최선의 선택 저장
 * </pre> 
 * @author 김신영
 *
 */

public class BJ_1149_RGB거리_김신영 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = N - 2; i >= 0; i--) {
			for(int j = 0; j < 3; j++) { // i번째 인덱스
				
				// 작은 값 찾기
				int min = Integer.MAX_VALUE;
				for(int k = 0; k < 3; k++) { // i+1번째 인덱스
					if(j != k) { // 색이 다른 경우에만 선택할 수 있음
						if(min > arr[i+1][k]) {
							min = arr[i+1][k];
						}
					}
				}
				
				arr[i][j] = arr[i][j] + min;
			}
			
		}
		
		int answer = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++) {
			answer = Math.min(answer, arr[0][i]);
		}
		
		System.out.println(answer);
	}

}
