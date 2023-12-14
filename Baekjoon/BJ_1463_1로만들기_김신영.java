import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <pre>
 * BJ_1463_1로만들기_김신영 | https://www.acmicpc.net/problem/1463
 * 12544KB	80ms
 * ----- ----- -----
 * 그래프 + BFS 
 * ----- ----- -----
 * 최단거리
 * 분기가 3개인 그래프에서 가장 빨리 1에 도달하는 거리를 찾으면 됨
 * visited 배열 사용해서 같은 숫자 여러번 검사하지 않도록 함
 * </pre>
 * 
 * @author 김신영
 *
 */

public class BJ_1463_1로만들기_김신영 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		
		if(X == 1) {
			System.out.println(1);
			return;
		}

		int answer = 0;
		boolean[] isVisited = new boolean[X];

		// BFS
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(X);
		while (!queue.isEmpty()) {
			int size = queue.size();

			answer++;
			for (int s = 0; s < size; s++) {
				int curr = queue.poll();

				// 3으로 나누는 경우
				if (curr % 3 == 0 && !isVisited[curr / 3]) {
					queue.add(curr / 3);
					isVisited[curr / 3] = true;
					if (curr / 3 == 1) {
						queue.clear();
						break;
					}
				}

				// 2로 나누는 경우
				if (curr % 2 == 0 && !isVisited[curr / 2]) {
					queue.add(curr / 2);
					isVisited[curr / 2] = true;
					if (curr / 2 == 1) {
						queue.clear();
						break;
					}
				}

				// 1 빼는 경우
				if (!isVisited[curr - 1]) {
					queue.add(curr - 1);
					isVisited[curr - 1] = true;
					if (curr - 1 == 1) {
						queue.clear();
						break;
					}
				}
				
			}

		}

		System.out.println(answer);
	}

	public static void cal(int x) {

	}
}
