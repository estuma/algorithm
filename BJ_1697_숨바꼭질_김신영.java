import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_1697_숨바꼭질_김신영
 * 11548KB	76ms
 * ---------- ----- ----------
 * 수빈이 0 <= N <= 100,000에 있고
 * 동생은 0 <= K <= 100,000에 있다
 * 수빈이는 위치가 x일 때 걸으면 1초 후에 x-1 또는 x+1로 이동하게 됨
 * 걷기: -1, +1
 * 순간이동: 2*x 위치로 이동
 * 동생을 찾을 수 있는 가장 빠른 시간 출력
 * ---------- ----- ----------
 * 재귀 활용
 * 순간이동은 현재 위치의 2배까지 가능하므로 목적지의 절반까지 이동하면 순간이동으로 빨리 갈 수 있음
 * -> 절반의 절반을 계속 구해 가장 가까운 값부터 계산하기
 * -> half / 2 > N 절반이 N보다 작아지기 전까지 계속 재귀 호출
 * -> -> 현재 half값이 홀수인 경우 -> 가질 수 있는 경우가 half/2와 half/2+1값으로 재귀 호출 // cnt + 2 이유: 홀수지점으로가려면 순간이동 후 이동 한 번 더 해야함
 * -> -> 현재 half값이 짝수인 경우 -> half/2 재귀 호출 // cnt는 + 1 이유: 순간이동하면 1이니까
 * +) 동생보다 수빈이가 뒤에 있는 경우는 순간이동 사용할 수 없는 경우이므로 동생과의 거리 차이만큼이 답이다.
 * </pre>
 * 
 * @author 김신영
 *
 */

public class BJ_1697_숨바꼭질_김신영 {

	static int N, K, min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 수빈이가 동생보다 앞에 있는 경우 -> 그냥 뒤로 한칸씩 가는게 제일 빠르다
		if (N >= K) {
			min = N - K;
		} else {
			recur(K, 0);
		}

		// 정답 출력
		System.out.println(min);
	}

	// half: K를 2로 n번 나눈 값, cnt: 걸리는 시간
	public static void recur(int half, int cnt) {
		if (half / 2 > N) {
			// 홀수면 cnt+2 하고, half/2, half/2+1
			if (half % 2 == 1) {
				recur(half / 2 + 1, cnt + 2);
				recur(half / 2, cnt + 2);
			} 
			// 짝수면 cnt+1하고, half/2
			else {
				recur(half / 2, cnt + 1);
			}
		} else {
			// 절반과 같으면
			// N보다 작은 수
			int tmp = half / 2;
			
			// 3인경우 -> half/2==1이되므로 조건 추가
			if(half == 3) {
				min = Math.min(min, cnt + 2);
				return;
			}
			
			// 다음 위치까지 순간이동
			if (tmp == N) {
				min = Math.min(min, cnt + 1);
				return;

			}

			// half/2까지 가는데 걸리는 시간이 half까지 가는데 걸리는 시간보다 짧은지
			// N에서 half/2까지 가는 거리가 half까지 가는 거리보다 짧으면 half/2까지 뒤로가기
			if (N - tmp < half - N) {
				min = Math.min(min, cnt + (N - tmp) + 1);
			} 
			// half까지의 거리가 더 짧으면 half까지 걷기
			else {
				min = Math.min(min, cnt + half - N);
			}
		}
	}
}
