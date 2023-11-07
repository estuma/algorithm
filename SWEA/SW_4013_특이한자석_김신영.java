import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * SW_4013_특이한자석
 * 18,360kb  109ms 2,057
 * ---------- ----------
 * 배열을 이용하여 구해줌
 * 배열의 시작 포인터를 움직여서 구해준다.
 * 톱니를 8개 가지고 있으므로 i번째를 구하기 위해서는 
 * : (현재 포인터 시작 위치 + i) % 8 하면 됨. 인덱스는 0부터 시작.
 * 왼쪽 영향, 오른쪽 영향 체크하며 돌려야하는 배열 한번에 확인 후 하나씩 돌려준다.
 * </pre>
 * @author 김신영
 *
 */

public class SW_4013_특이한자석_김신영 {

	static int K;
	static int[] p;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine());

			// 자석 입력
			arr = new int[4][8];
			p = new int[4];

			for (int i = 0; i < 4; i++) {
				p[i] = 0;
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 돌리기 명령 실행
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				isSpin(num - 1, dir);
			}

			// S인것만 찾아서 정답 구하기
			int answer = 0;
			for (int i = 0; i < 4; i++) {
				if (arr[i][p[i]] == 1) {
					answer += Math.pow(2, i);
				}
			}

			System.out.println("#" + tc + " " + answer);

		}

	}

	// num: 0~3
	public static void isSpin(int num, int dir) {
		boolean[] spinArr = new boolean[4];

		// 오른쪽 영향 체크
		for (int i = num; i < 3; i++) {
			// 상극이면
			if (arr[i][(p[i] + 2) % 8] != arr[i + 1][(p[i + 1] + 6) % 8]) {
				spinArr[i] = true;
				spinArr[i + 1] = true;
			} else {
				break;
			}
		}
		
		// 왼쪽 영향 체크
		for (int i = num; i > 0; i--) {
			// 상극이면
			if (arr[i][(p[i] + 6) % 8] != arr[i - 1][(p[i - 1] + 2) % 8]) {
				spinArr[i] = true;
				spinArr[i - 1] = true;
			} else {
				// 상극이 아니면 왼쪽으로 더 이상 영향 미치지 X
				break;
			}
		}

		// 자기 자신 무조건 돌리기!!
		spinArr[num] = true;
		
		for (int i = 0; i < 4; i++) {
			if (spinArr[i]) {
				// 0, 2: 1, -1, 1, -1 -> dir이 1인경우. -1이면 반대됨
				if (num % 2 == 0) {
					spin(i, dir * (int) Math.pow(-1, i));
				}
				// 1, 3: -1, 1, -1, 1
				else {
					spin(i, dir * (int) Math.pow(-1, i + 1));
				}
			}
		}
	}

	// num번째 자석을 dir방향으로 1: 시계 -1:반시계
	public static void spin(int num, int dir) {
		if (dir == 1) {
			p[num] = (p[num] + 7) % 8;
		} else {
			p[num] = (p[num] + 1) % 8;
		}
	}

}
