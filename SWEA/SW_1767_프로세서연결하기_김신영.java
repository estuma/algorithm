import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * SW_1767_프로세서연결하기
 * 91,356KB		446ms
 * ----- ----- -----
 * 코어가 N개 있으면 N개 연결하는 경우부터 실행
 * -> N개 중 N개 뽑는 조합, N개 중 N-1개뽑는 조합 순서대로 생성
 * -> 코어마다 연결 가능한 방향을 저장하여 이를 통해 순열 생성
 * -> 시뮬레이션 돌리기
 * </pre>
 * @author 김신영
 *
 */

public class SW_1767_프로세서연결하기_김신영 {

	static int N, max, min;
	static char[][] arr;
	static List<int[]> core;
	static int[] selectDir;
	static boolean[] isSelected;
	static Integer[][] validDir;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			arr = new char[N][N];
			for (int i = 0; i < N; i++) {
				arr[i] = br.readLine().replace(" ", "").toCharArray();
			}

			// 코어 위치 저장
			core = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == '1') {
						// 가장자리가 아니면
						if (i != 0 && j != 0 && i != N - 1 && j != N - 1) {
							core.add(new int[] { i, j });
						}
					}

				}
			}

			max = 0;
			min = Integer.MAX_VALUE;
			selectDir = new int[core.size()];
			isSelected = new boolean[core.size()];

			findDir(); // 가능한 방향 찾기

			// 5개부터 차례로 뽑기 -> min값 찾으면 종료
			for (int i = core.size(); i >= 0; i--) {
				if (min != Integer.MAX_VALUE)
					break;

				combination(0, 0, i);
			}

			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}

		System.out.println(sb);
	}

	public static void findDir() {
		List<Integer> list;
		validDir = new Integer[core.size()][];

		// 코어마다 전선 연결
		for (int i = 0; i < core.size(); i++) {
			list = new ArrayList<>();

			// 선택한 방향으로 끝까지 전선 연결
			for (int dir = 1; dir <= 4; dir++) {
				int newR = core.get(i)[0];
				int newC = core.get(i)[1];
				while (true) {
					newR += dr[dir];
					newC += dc[dir];

					// 범위 넘어가면 연결 가능한 방향
					if (newR < 0 || newR >= N || newC < 0 || newC >= N) {
						list.add(dir);
						break;
					}

					// 0이 아니면 연결 불가능한 방향
					if (arr[newR][newC] != '0') {
						break;
					}
				}
			}

			int size = list.size();
			validDir[i] = list.toArray(new Integer[size]);
		}
	}

	// r개 뽑는 조합 생성
	public static void combination(int cnt, int start, int r) {
		if (cnt == r) {
			permutation(0);
		}

		for (int i = start; i < core.size(); i++) {
			isSelected[i] = true;
			combination(cnt + 1, i + 1, r);
			isSelected[i] = false;
		}
	}

	// 연결 가능한 방향 가지고 순열 생성
	public static void permutation(int cnt) {
		if (cnt == core.size()) {
			// ----- 배열 복사
			char[][] newArr = new char[N][N];
			for (int i = 0; i < N; i++) {
				newArr[i] = arr[i].clone();
			}

			simulation(newArr);

			return;
		}

		// 갈 수 있는 방향만큼 중복순열 생성

		// 길이가 0이면 갈 수 있는 방향 X -> 0 넣고 다음 순열 생성
		if (validDir[cnt].length == 0) {
			selectDir[cnt] = 0;
			permutation(cnt + 1);
		}

		// 연결 가능한 방향으로 생성 -> 현재 선택된 코어가 아니면 0
		for (int i = 0; i < validDir[cnt].length; i++) {
			if (isSelected[cnt]) {
				selectDir[cnt] = validDir[cnt][i];
				permutation(cnt + 1);
			} else {
				selectDir[cnt] = 0;
				permutation(cnt + 1);
			}
		}
	}

	static int[] dr = { 0, -1, 0, 1, 0 }, dc = { 0, 0, 1, 0, -1 };

	public static void simulation(char[][] arr) {
		int cnt = 0; // 전선 개수

		// 코어마다 전선 연결
		for (int i = 0; i < core.size(); i++) {

			// 선택한 방향으로 끝까지 전선 연결
			int newR = core.get(i)[0];
			int newC = core.get(i)[1];
			if (selectDir[i] != 0) {
				for (int length = 0; length < N; length++) {
					newR += dr[selectDir[i]];
					newC += dc[selectDir[i]];

					// 범위 넘어가면 다음으로
					if (newR < 0 || newR >= N || newC < 0 || newC >= N) {
						break;
					}

					// 갈 수 있는 길이 아닌 경우 현재 경우는 유망하지 않음
					if (arr[newR][newC] != '0') {
						return;
					}

					// 전선 연결 표시
					arr[newR][newC] = '-';
					cnt++;
				}
			}
		}

		// 전선 개수 적은 값으로 갱신
		min = Math.min(min, cnt);

	}

}
