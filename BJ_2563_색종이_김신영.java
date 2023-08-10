import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 11588KB	84ms
 * 100 x 100 배열 만들고
 * 색종이 위치 입력받아서 좌표 기준으로 10칸식 색칠 (true) 한다.
 * 모든 입력 검사한 뒤 색칠된 값 카운트하여 출력
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_2563_색종이_김신영 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 색종이 수
		int N = Integer.parseInt(br.readLine());
		// 도화지 배열
		boolean[][] arr = new boolean[100][100];
		
		// 색종이 좌표 입력
		for (int idx = 0; idx < N; idx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 입력받은 x, y 좌표 기준으로 10x10 칸 색칠
			for (int i = y; i < y + 10; i++) {
				for (int j = x; j < x + 10; j++) {
					arr[i][j] = true;
				}
			}
		}
		
		// 배열 순회하며 true인 개수 세기
		int answer = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(arr[i][j]) {
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}

}
