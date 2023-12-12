import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <pre>
 * 11504kb	80ms
 * 설탕을 정확하게 N킬로그램 배달해야 한다.
 * 봉지는 3kg, 5kg가 있음
 * 최대한 적은 봉지를 들고갈 때의 수
 * N을 5로 나눠서 나온 나머지가 3으로 나누어 떨어지면 가능!
 * -> 5의 배수 중 가장 큰 수부터 나누기
 * </pre>
 * 
 * @author 김신영
 *
 */

public class BJ_2839_설탕배달_김신영 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = 0;

		// N을 5로 나눠서(나눌 수 있는 가장 큰 수부터)나온 나머지가 3으로 나누어 떨어지면 가능
		for (int i = N / 5; i >= 0; i--) {
			if ((N - i * 5) % 3 == 0) {
				answer = i + (N - i * 5) / 3;
			}
		}

		answer = (answer == 0) ? -1 : answer;
		
		System.out.println(answer);
	}

}
