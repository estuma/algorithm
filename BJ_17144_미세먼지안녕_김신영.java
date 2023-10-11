import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 42068KB	508ms
 * ---------- ---------
 * 1. 미세먼지 4방향으로 확산
 * 2. 배열돌리기 가장 바깥쪽 1줄만 돌리면 됨
 * </pre>
 * @author 김신영
 *
 */

public class BJ_17144_미세먼지안녕_김신영 {

	static int R, C, T;
	static int[][] arr;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		arr = new int[R][C];
		int air = -1;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == -1) {
					air = i;
				}
			}
		}

		for (int t = 0; t < T; t++) {
			// 1. 미세먼지 확산 (인접한 네 방향으로 / 공기청정기가 있거나, 칸이 없으면 확산되지 않음)
			// 확산되는 양: 1/5, 소수점은 버림
			int[][] tmp = new int[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					// 먼지 없으면 넘어가기
					if (arr[i][j] == 0) {
						continue;
					}

					// 네방향으로 확산
					int spread = arr[i][j] / 5;
					int cnt = 0;
					for (int d = 0; d < 4; d++) {
						int newR = i + dr[d];
						int newC = j + dc[d];

						if (newR < 0 || newR >= R || newC < 0 || newC >= C) {
							continue;
						}

						// 공기청정기인 경우
						if (arr[newR][newC] == -1) {
							continue;
						}

						tmp[newR][newC] += spread;
						cnt++;
					}
					
					// 확산된만큼 빼주기
					tmp[i][j] += arr[i][j] - spread * cnt;
				}
			}
			

			// 계산 끝나면 arr에 반영
			for (int i = 0; i < R; i++) {
				arr[i] = tmp[i].clone();
			}
			
			// 2. 공기청정기 작동
			// 위쪽은 반시계방향, 아래쪽은 시계방항으로 순환 (1칸씩) -> 공기청정기로 들어간 미세먼지는 모두 정화됨
			// 2-1. 위쪽 공기청정기 : 반시계방향 작동
			arr[air-1][1] = 0;
			for (int i = C - 1; i > 0; i--) { // 위
				arr[0][i - 1] = tmp[0][i];
			}
			for (int i = 0; i < air - 2; i++) { // 왼쪽
				arr[i + 1][0] = tmp[i][0];
			}
			for (int i = 1; i < C - 1; i++) { // 아래
				arr[air - 1][i + 1] = tmp[air - 1][i];
			}
			for (int i = 0; i < air - 1; i++) { // 오른쪽
				arr[i][C - 1] = tmp[i + 1][C - 1];
			}

			// 2-2. 아래쪽 공기청정기 : 시계방향 작동
			arr[air][1] = 0;
			for (int i = C - 1; i > 1; i--) { // 위
				arr[air][i] = tmp[air][i-1];
			}
			for (int i = air; i < R-1; i++) { // 오른쪽
				arr[i + 1][C - 1] = tmp[i][C - 1];
			}
			for (int i = 0; i < C - 1; i++) { // 아래
				arr[R-1][i] = tmp[R-1][i+1];
			}
			for (int i = air+1; i < R - 1; i++) { // 왼쪽
				arr[i][0] = tmp[i + 1][0];
			}
		}

		// 미세먼지 양 세기
		int answer = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] > 0) {
					answer += arr[i][j];
				}
			}
		}

		System.out.println(answer);
	}

}


//7 8 1
//0 0 0 0 0 0 0 9
//0 0 0 0 3 0 0 8
//-1 0 5 0 0 0 22 0
//-1 8 0 0 0 0 0 0
//0 0 0 0 0 10 43 0
//0 0 5 0 15 0 0 0
//0 0 40 0 0 0 20 0
