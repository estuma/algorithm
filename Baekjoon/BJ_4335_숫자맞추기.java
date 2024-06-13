import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/4335
 * 12,516KB	88ms
 *
 * [문제]
 * 스탠이 거짓말을 한 적이 있다면 Stan is dishonest, 거짓말을 한 적이 없다면 Stan may be honest 를 출력
 *
 * [입력]
 * 1 이상 10 이하인 숫자
 * too high || too low || right on
 * 마지막 줄에는 0이 주어짐
 * 
 * [풀이]
 * low, high값을 대답에 따라 갱신하고
 * low값과 high값이 low < high를 항상 만족하면 honest / 아니면 dishonest임
 *
 * 새로운 문제가 나올때마다 초기화를 제대로 안해줘서 틀림
 * 그리고 high, low 갱신할 때 가장 작은값/가장큰값 으로 갱신해줘야 제대로된 답이 나옴
 *
 * @author 김신영
 */

public class BJ_4335_숫자맞추기 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int low = 0;
        int high = 11;

        StringBuilder sb = new StringBuilder();

        boolean isHonest = true;
        while(true) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                break;
            }

            switch (br.readLine()) {
                case "too high":
                    high = Math.min(high, num); // high값 중 더 작은 값 선택
                    if (high <= low) {
                        isHonest = false;
                    }
                    break;
                case "too low":
                    low = Math.max(low, num); // low값 중 더 작은 값 선택
                    if (low >= high) {
                        isHonest = false;
                    }
                    break;
                case "right on":
                    if (low < num && high > num && isHonest) {
                        sb.append("Stan may be honest\n");
                    } else {
                        sb.append("Stan is dishonest\n");
                    }
                    isHonest = true;
                    low = 0;
                    high = 11;

            }

        }

        System.out.println(sb);
    }

}
