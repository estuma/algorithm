import java.util.*;
import java.io.*;
import java.math.*;

/**
 * 백준 1914 하노이탑
 * https://www.acmicpc.net/problem/1914
 * @author 김신영
 * "재귀로 구현"
 * 세 개의 장대 중 첫 번째 장대에 반경이 서로 다른 n개의 원판이 있을 때
 * 세 번째 장대로 그대로 옮기기
 * n을 옮기기 위해서는 
 * 1. n-1개를 2번 장대로 옮긴다.
 * 2. n번째 원판을 3번 장대로 옮긴다.
 * 3. 2번장대에 있는 n-1개의 원판을 3번 장대로 옮긴다.
 * ! 원판이 하나일 때는 3번 원판으로 옮기면 된다. !
 * 출력 형식 ( A -> B로 옮긴다는 뜻 )
 * - 옮긴 횟수
 * - A B
 * - A B
 */

public class Main {
	// 원판 움직이는 순서 <A, B> 저장
	static ArrayList<int[]> step= new ArrayList<>();
	
	public static void hanoi(int n, int from, int to, int via) {
		// 종료조건, 원판이 하나 남은 경우
		if(n <= 1) {
			step.add(new int[] {from, to});
		} else {
			// n-1개를 1번 장대에서 2번 장대로 옮김
			hanoi(n - 1, from, via, to);
			// n번째 원판을 을 1번 장대에서 3번 장대로 옮김
			step.add(new int[] {from, to});
			// n-1개를 2번 장대에서 3번 장대로 옮김
			hanoi(n - 1, via, to, from);
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 사용자입력받기위해 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
		// n 입력
		int n = Integer.parseInt(br.readLine());
		// n일때 옮겨야 하는 수는 2^n - 1
		BigInteger res = new BigInteger("2");
		System.out.println(res.pow(n).subtract(new BigInteger("1")));
		
		// 20 이하일때만 과정 출력
		if(n <= 20) {
			hanoi(n, 1, 3, 2);
			
			// 결과 출력
			for(int i = 0; i < step.size(); i++) {
				int[] tmp = step.get(i);
				System.out.println(tmp[0] + " " + tmp[1]);
			}
		}
	}
}