import java.util.*;
import java.io.*;

/**
 * 백준 2869 달팽이는 올라가고 싶다
 * @author 김신영
 * 낮에 A미터 올라가고, 밤에 B미터 내려간다. 나무는 V미터
 * (A - B) * N + A >= V
 * (A - B) * N >= V - A
 * N >= (V - A) / (A - B)
 * 나누어 떨어지면 다 올라간 것
 * 그렇지 않으면 하루 더 올라가야한다.
 */

public class Solution {
    
    public static void main(String[] args) throws IOException {
        // 입력받기위함
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 띄어쓰기 단위로 나눔
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // a: 낮동안 올라가는 높이, b: 밤에 미끄러지는 높이, v: 나무의 높이
        // st로 받은 String값을 int값으로 형변환한다.
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        int res;
        
        // a 뺀값이므로 1을 더해준다.
        int tmp = (v-a)/(a-b) + 1;
        
        // 나누어 떨어지면 더 올라가지 않아도 됨
        if((v-a) % (a-b) == 0) {
            res = tmp;
        } else { // 나머지가 있으면 하루 더 올라가야 한다.
            res = tmp + 1;
        }
        
        System.out.println(res);
    }
}