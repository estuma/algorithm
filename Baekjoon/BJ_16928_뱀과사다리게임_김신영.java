import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_16928_뱀과사다리게임_김신영 | https://www.acmicpc.net/problem/16928
 * 11,800KB	84ms
 * ----- ----- -----
 * BFS
 * ----- ----- -----
 * 최소 몇 번만에 도착점에 도착할 수 있을지
 * 주사위 : 1 ~ 6
 * 보드 : 10 * 10 -> 1 ~ 100 숫자 하나씩 순서대로 적어져있음
 * 사다리 : 위로 올라감
 * 뱀 : 아래로 내려감
 * !목표 : 1번칸에서 시작해 100번 칸에 도착하는 것
 * ----- ----- -----
 * BFS
 * </pre>
 * 
 * @author 김신영
 */

public class BJ_16928_뱀과사다리게임_김신영 {

	static int N, M;
	static int[] board = new int[100];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			board[from] = to;
		}

		System.out.println(search());

	}

	public static int search() {
		boolean[] isVisited = new boolean[100];
		Queue<Integer> queue = new ArrayDeque<>();
		int answer = 0;
		
		queue.add(0);
		isVisited[0] = true;
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				int curr = queue.poll();

				if (curr == 99) {
					return answer;
				}

				for (int i = 1; i <= 6; i++) {
					int next = curr + i;
					if (next >= 100 || isVisited[next]) {
						continue;
					}

					if (board[next] != 0) {
						if (!isVisited[board[next]]) {
							queue.add(board[next]);
							isVisited[board[next]] = true;
						}
					} else {
						queue.add(next);
						isVisited[next] = true;
					}

				}
			}

			answer++;
		}

		return answer;
	}

}
