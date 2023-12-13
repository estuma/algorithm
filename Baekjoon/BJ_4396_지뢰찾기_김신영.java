import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * <pre>
 * BJ_4396_지뢰찾기_김신영
 * 11480KB	80ms
 * https://www.acmicpc.net/problem/4396
 * ---------- ----------
 * 1. 열린 땅의 주변 지뢰 개수 작성
 * 2. 지뢰가 있는 칸이 열렸다면 -> 지뢰가 있는 모든 칸이 별표 표시
 * 3. 정답 출력
 * </pre>
 * 
 * @author 김신영
 *
 */

public class BJ_4396_지뢰찾기_김신영 {

	static int N;
	static char[][] answer;
	static char[][] map1, map2;

	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map1 = new char[N][N];
		map2 = new char[N][N];
		answer = new char[N][N];
		


		// 지뢰 위치 입력
		for (int i = 0; i < N; i++) {
			map1[i] = br.readLine().toCharArray();
		}

		// 열린 칸 입력
		for (int i = 0; i < N; i++) {
			map2[i] = br.readLine().toCharArray();
		}

		// 열기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 0;

				if (map2[i][j] == '.') {
					answer[i][j] = '.';
					continue;
				}

				// 8방 검사
				for (int d = 0; d < 8; d++) {
					int newR = i + dr[d];
					int newC = j + dc[d];
					
					if (newR < 0 || newR >= N || newC < 0 || newC >= N) {
						continue;
					}

					// 지뢰이면
					if (map1[newR][newC] == '*') {
						cnt++;
					}
				}

				answer[i][j] = (char) (cnt + '0');
			}
		}

		// 지뢰가 있는 칸이 열렸으면 남은 지뢰 열기
		if (isOpen()) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map1[i][j] == '*') {
						answer[i][j] = '*';
					}
				}
			}
		}

		// 정답 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sb.append(answer[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());

	}

	public static boolean isOpen() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map1[i][j] == '*' && map2[i][j] == 'x') {
					return true;
				}
			}
		}

		return false;
	}

}

