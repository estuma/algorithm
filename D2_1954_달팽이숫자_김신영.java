import java.util.Scanner;

/**
 * <pre>
 * SWEA	D2 1954 달팽이숫자
 * N*N 배열을 1부터 달팽이 모양으로 채우기
 * 방향 전환 하는 조건: 배열의 끝에 도달하거나, 이미 채워진 칸을 만나면 방향 전환을 한다.
 * 진행 순서: 우 -> 하 -> 좌 -> 상 -> 우..
 * 조건에   맞는지 검사하며 N*N번 반복하면 된다.
 * 20,692kb, 146ms
 * </pre>
 * @author 김신영
 *
 */

public class D2_1954_달팽이숫자 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int n = sc.nextInt();
			int[][] arr = new int[n][n]; // int 배열 초기값 0
			
			// 방향이 바뀌는 기준 : 끝을 만나거나, 다름 칸이 채워져 있는 경우(방문한경우)
			// 진행 방향 우 -> 하 -> 좌 -> 상
			int[] xdir = { 1, 0, -1, 0 };
			int[] ydir = { 0, 1, 0, -1 };
			int dir = 0;		// 방향은 오른쪽부터 시작
			int r = 0, c = 0; 	// row, column

			arr[0][0] = 1;	// 시작 숫자 1
			for (int i = 2; i <= n * n; i++) { // 채울 숫자 2부터 n*n까지
				int r_ = r + ydir[dir];		// 새로운 행 값
				int c_ = c + xdir[dir];		// 새로운 열 값
				// 행과 열의 새 인덱스값이 유효한 인덱스인지 검사
				// 및 방문한 적 없는지 검사
				if (r_ >= 0 && r_ < n && c_ >= 0 && c_ < n && arr[r_][c_] == 0) {
					arr[r_][c_] = i;	// 맞으면 값 채우기
					r = r_;		// 현재 행 값 갱신
					c = c_;		// 현재 열 값 갱신
				} else {		// 유효하지 않거나 다음 인덱스의 배열 값이 0이 아니면 현재 진행방향으로 더 진행할 수 없음
					dir = (dir + 1) % 4;	// 다음 방향으로 바꾸기
					r_ = r + ydir[dir];		// 방향전환 후 행 값 갱신
                    c_ = c + xdir[dir];		// 방향전환 후 열 값 갱신
                    arr[r_][c_] = i;
					r = r_;		// 현재 행 값 갱신
					c = c_;		// 현재 열 값 갱신
				}
			}
			
			// 정답 배열 출력
			System.out.println("#" + test_case);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
