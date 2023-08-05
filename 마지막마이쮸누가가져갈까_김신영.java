import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        int sum = 0;
        int answer = -1;
        
        // 마지막마이쮸누가?
        // 1초부터 n초까지
        map.put(1, 1);
        queue.offer(1);
        for(int i = 1; i < n; i++) {
            // 먼저 빼고
            int tmp = queue.poll();
            // 가져간 마이쮸 개수 갱신
            sum += map.get(tmp);    // tmp번호 사람이 가져가야하는 마이쮸개수
            map.replace(tmp, map.get(tmp)+1);
            if(sum >= n) {
                answer = tmp;
                break;
            }
            queue.offer(tmp);
            // i초에 i번 사람 들어온다.
            queue.offer(i+1);
            map.put(i+1, 1);
        }
        
        System.out.println(answer);
    }

}
