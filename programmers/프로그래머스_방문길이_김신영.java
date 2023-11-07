import java.util.*;

/* 
    U : 위
    D : 아래
    R : 오른쪽
    L : 왼쪽
    
    (0,0) 시작
    좌표평면 10 x 10
    
    * 게임 캐릭터가 지나간 길 중 캐릭터가 처음 걸어본 길의 길이 *
    
    dirs : 명령어 (길이 <= 500)
*/

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        
        // 캐릭터가 걸어간 길을 표시하는 방법은?
        List<int[]>[] list = new ArrayList[122];
        int idx = 0;
        for(int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
        
        // i: 0~9 -> (0,0) ~ (0,9)
        // i: 10~19 -> (1,0) ~ (1,9)
        // => 계산: 10의자리=r 1의자리=c
        int[] curr = {5, 5};
        for(int i = 0; i < dirs.length(); i++) {
            int r = curr[0];
            int c = curr[1];
            
            switch(dirs.charAt(i)) {
                case 'U': r--; break;
                case 'D': r++; break;
                case 'R': c++; break;
                case 'L': c--; break;
            }
            
            if(isIn(r, c)) {
                // 지금 지나가는 길이 지나간적 있는 길인지
                boolean isOk = true;
                
                for(int[] a : list[curr[0]*10+curr[1]]){
                    if(a[0] ==  r && a[1] == c) {
                        isOk = false;
                        break;
                    }
                    
                }

                if(isOk) {
                    list[curr[0]*10+curr[1]].add(new int[] {r, c});
                    list[r*10+c].add(new int[] {curr[0], curr[1]});
                    
                    answer++;
                }
                
                curr[0] = r;
                curr[1] = c;
            }
        }
        
        return answer;
    }
    
    public boolean isIn(int r, int c) {
        if(r > 10 || r < 0 || c > 10 || c < 0) {
            return false;
        }
        
        return true;
    }
}
