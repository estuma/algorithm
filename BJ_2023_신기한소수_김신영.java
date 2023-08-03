import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <pre>
 * BJ_2023_신기한소수_김신영 https://www.acmicpc.net/problem/2023
 * 11728KB, 780ms
 * ---------- 입력 ----------
 * 1. N (1 <= N <= 8) : 길이
 * => 길이가 N인 소수 중 신기한 소수 찾기
 * ---------- 출력 ----------
 * 신기한 소수 오름차순으로 정렬하여 한 줄에 하나씩 출력
 * ---------- 로직 ----------
 * 신기한 소수: 왼쪽부터 1자리, 2자리, 3자리, 4자리까지 수가 모두 소수인 수
 * - 에라토스테네스의 체 사용 -> 메모리 제한 초과
 * => 소수를 미리 찾아놓지 않고 그 때 그 때 찾는 방법 사용
 * 1. 소수는 1의자리일 때는 2, 3, 5, 7만 가능하고
 * 2. 그 이상일 때는 마지막에 1, 3, 7, 9만 나올 수 있음
 * => 재귀를 이용하여 현재 만든 숫자가 몇자리인지와 만들어진 숫자를 이용하여 판별한다.
 * 3. 현재 만들어진 숫자가 소수이면 -> 다음 재귀 호출하여 숫자 만들기
 * 4. 현재 만들어진 숫자가 소수가 아니면 -> 그냥 넘어간다.
 * 5. 만약 숫자가 N자리까지 만들어졌다면 재귀를 종료하고 만들어진 숫자를 기억한다.
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_2023_신기한소수 {

    static int N;	// 몇자리수인지
    static StringBuilder sb = new StringBuilder();	// 정답 저장할 변수
    static String[] num = { "1", "3", "7", "9" };	// 소수는 뒷자리가 1, 3, 7, 9만 나올 수 있음

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 1의자리 숫자인 2, 3, 5, 7이 신기한소수인지 따로 판단. n+1의자리수와 조건이 다르므로
        String[] first = { "2", "3", "5", "7" };
        for (int i = 0; i < first.length; i++) {
            strangePrime(first[i], 1);
        }

        System.out.println(sb);
    }
    

    public static void strangePrime(String prime, int n) {
        if (n == N) {	// N자리수가 완성되면
        	// 소수이면 정답에 추가하고 아니면 해당 분기 종료한다.
            if (isPrime(Integer.parseInt(prime))) {
                sb.append(prime).append("\n");
            }
            return;
        }

        // 1, 3, 7, 9를 마지막 자리에 붙여가며 검사
        for (int i = 0; i < num.length; i++) {
        	// 새로 만들어지는 숫자가 소수이면 다음 자릿수 검사
            if (isPrime(Integer.parseInt(prime + num[i]))) {
                strangePrime(prime + num[i], n + 1);
            }
        }
    }
    
    // 매개변수로 받은 숫자가 소수인지 판별하는 함수
    public static boolean isPrime(int prime) {
    	// 2는 소수
    	if(prime == 2) {
    		return true;
    	}
    	// 2부터 차례로 나눠 나누어 떨어지는 값이 있다면 소수 아님
    	for(int i = 2; i < prime / 2; i++) {
    		if(prime % i == 0) {
    			return false;
    		}
    	}
    	return true;
    }
}