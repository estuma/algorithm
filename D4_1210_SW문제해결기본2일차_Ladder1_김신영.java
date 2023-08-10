import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 도착지점 2로 주어짐 도착 지점에서 위로 올라가며 검사 
 * 왼쪽이나 오른쪽으로 가는 지점이 있으면 방향 전환하고 이동 
 * 왼쪽, 오른쪽으로진행하다 길이 끝나면 다시 위로 진행
 * 30,812kb, 142ms
 * </pre>
 * @author 김신영
 *
 */
public class D4_1210_SW문제해결기본2일차_Ladder1_김신영 {

	public static void main(String[] args) throws Exception {
		BufferedReader br;
		StringTokenizer st;

		for (int test_case = 1; test_case <= 10; test_case++) {
			br = new BufferedReader(new InputStreamReader(System.in));
			br.readLine(); // 테스트케이스 번호 입력
			int answer = 0;
			int[][] ladder = new int[100][100];
			int goal = -1;
			
			// 사다리 배열 만들기
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
					if (ladder[i][j] == 2) { goal = j; }
				}
			}

			int dir = 1; // 진행 방향을 저장할 변수
			int r = 99, c = goal;	// 시작 위치
			// 0행에 도달할때까지 반복
			while (r > 0) {
				switch (dir) {
				case 1: // 상
					// 위로 진행하다가 오른쪽으로 길이 있으면
					if (c + 1 < 100 && ladder[r][c + 1] == 1) {
						dir = 3;	// 방향 오른쪽으로 변경
						c++;		// 한칸 이동
					}
					// 위로 진행하다가 왼쪽으로 길이 있으면
					else if (c - 1 >= 0 && ladder[r][c - 1] == 1) {
						dir = 2;	// 방향 전환
						c--;		// 한칸 이동
					} 
					// 좌우 모두 아니면 위로 계속 진행
					else {
						r--;
					}
					break;
				case 2: // 좌
					// 왼쪽으로 진행하다 위로 길이 있으면
					if (r - 1 >= 0 && ladder[r - 1][c] == 1) {
						dir = 1;	// 방향 전환하고
						r--;		// 한칸 이동
					} 
					// 그렇지 않으면 왼쪽으로 계속 이동
					else {
						c--;
					}
					break;
				case 3: // 우
					// 오른쪽으로 진행하다 위로 길이 있으면
					if (r - 1 >= 0 && ladder[r - 1][c] == 1) {
						dir = 1;	// 방향 전환하고
						r--;		// 한칸 이동
					} 
					// 그렇지 않으면 오른쪽으로 계속 이동
					else {
						c++;
					}
					break;
				}
			}

			// 마지막에 도달한 행이 답
			answer = c;

			System.out.println("#" + test_case + " " + answer);
		}

	}

}
