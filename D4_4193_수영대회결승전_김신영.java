import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * D4 4193 ������ȸ ����� https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AWKaG6_6AGQDFARV
 * 18,432 kb, 113 ms
 * ---------- ---------- ----------
 * NxN �ȿ��� ���� ���� �� ã��, �� �� ���� ������ �� �ִ���
 * 1: ������ �� ���� ��ֹ�
 * 2: 2, 5, 8, ... �� �������� ������ �� �ִ� ��ֹ�
 * 2 ���� �� �� ����� ��� ��� �ӹ������� �� ����
 * ---------- BFS Ȱ�� ----------
 * ť�� Ȱ���Ͽ� �˻��Ѵ�.
 * ���� ��ġ�� �������� ������ �ð�������� Ž�����ش�.
 * ���� ���� ��� �������°��� ���� ���� �湮�ߴ��� Ȯ���Ѵ�.
 * -- ���� ª�� �ð��� ã�� ��.
 * ���� �ʿ��� Ž���� �� �ִ� ��� ��츦 ť�� �ְ� Ž�� -> �� ������ ��� �ݺ�
 * ++
 * Ž���� �� �ִ� ���:
 * 1. �湮�� �� ���� ���̸鼭 
 * 2. ����� �迭�� ���� 0(��) �Ǵ� 2(�ҿ뵹��)�� ���
 * ++
 * ������ �� �ִ� ��ֹ��� �ֱ� ������ ���� �ð��� ����� �� �ִ��� �˻� ��
 * 1. �ҿ뵹�̰� ������(����� �� ������) �ٽ� ť�� �ְ� ���� �ʿ� �ٽ� �˻�
 * 2. �ҿ뵹�̰� ���ٸ�(����� �� ������) ��� Ž���Ѵ�.
 * </pre>
 * 
 * @author ��ſ�
 *
 */

public class D4_4193_������ȸ�����_��ſ� {

	static int T, N, startRow, startCol, endRow, endCol;
	static int[][] arr;
	static boolean[][] isVisited;
	static int seconds;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		int answer;

		for (int test_case = 1; test_case <= T; test_case++) {
			// �������� ũ��
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			isVisited = new boolean[N][N];

			// ������ ���� �Է�
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// ���� ��ġ
			st = new StringTokenizer(br.readLine());
			startRow = Integer.parseInt(st.nextToken());
			startCol = Integer.parseInt(st.nextToken());

			// ���� ��ġ
			st = new StringTokenizer(br.readLine());
			endRow = Integer.parseInt(st.nextToken());
			endCol = Integer.parseInt(st.nextToken());

			answer = search();

			System.out.println("#" + test_case + " " + answer);
		}
	}

	public static int search() {
		Queue<int[]> queue = new ArrayDeque<>();
		int[] dirRow = { -1, 0, 1, 0 };
		int[] dirCol = { 0, 1, 0, -1 };
		isVisited[startRow][startCol] = true;
		queue.offer(new int[] { startRow, startCol });

		seconds = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			// ���� ť�� ����ִ� �� ��ŭ �˻� -> n���� �� ��츦 �ѹ��� �˻��� �� ����!
			for (int i = 0; i < size; i++) {
				int[] tmp = queue.poll();

				// �������� �����ϸ� ���� �ʸ� ��ȯ�Ѵ�.
				if (tmp[0] == endRow && tmp[1] == endCol) {
					return seconds;
				}

				for (int dir = 0; dir < 4; dir++) {
					// ���� �湮 ��ġ ����
					int[] next = { tmp[0] + dirRow[dir], tmp[1] + dirCol[dir] };
					// ������ ��ġ���� �˻�
					if (isValid(next[0], next[1])) {
						// 0: �� -> ť�� �ְ� ��� �˻�
						if (arr[next[0]][next[1]] == 0) {
							isVisited[next[0]][next[1]] = true;
							queue.offer(next);
						} 
						// 2: �ҿ뵹�� -> ������ % 3 �� ���� 2�� ��쿡�� ������ �� �ִ�.  (2, 5, 8, 11�� ...)
						else if (arr[next[0]][next[1]] == 2) {
							// ������ �� ���������� ��ٸ��� -> ���� ��ġ�� �ٽ� ť�� ����
							if (seconds % 3 != 2) {
								queue.offer(tmp);
							} else {
								// ������ �� �ִ� �ð��� �ȴٸ� ť�� �ְ� Ž�� ���
								isVisited[next[0]][next[1]] = true;
								queue.offer(next);
							}
						}
					}
				}
			}

			seconds++;
		}

		return -1;
	}

	// row, col ���� �迭 ���̰�, �ش� ��ġ�� �湮�� �� ������ Ȯ���ϴ� �Լ�
	public static boolean isValid(int r, int c) {
		// �迭 ���� ������!
		if (r >= 0 && r < arr.length && c >= 0 && c < arr.length) {
			// �湮�� �� ������!
			if (!isVisited[r][c]) {
				return true;
			}
		}
		return false;
	}

}
