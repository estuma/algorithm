import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1312
 * 11,504KB	72ms
 *
 * 나머지 연산을 이용한 소수점 자릿수 계산
 * double로 나누면 부동소수점 연산..
 */

public class BJ_1312_소수 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int remainder = A % B;

        for (int i = 0; i < N - 1; i++) {
            remainder = (remainder * 10) % B;
        }

        int result = (remainder * 10) / B;

        System.out.println(result);
    }
}
