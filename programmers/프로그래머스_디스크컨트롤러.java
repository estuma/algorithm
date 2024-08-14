import java.util.*;

/*
jobs -> 요청 시간 순으로 정렬
현재 하고 있는 일이 없는 경우 -> 먼저 요청한 작업부터 처리

대기 시간을 줄여야 전체 시간이 줄어든다.
-> 현재 할 수 있는 일 중 cost가 작은 일부터 처리

*/

class Solution {
    
    public class Job {
        int start, cost;
        
        public Job(int s, int c) {
            start = s;
            cost = c;
        }
        
    }
    
    public int solution(int[][] jobs) {
        int answer = 0;
        
        PriorityQueue<Job> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        int cnt = 0, time = 0, end = 0;
        
        while(end < jobs.length) {
            // 현재 처리 가능한 일 큐에 넣기
            for(int i = cnt; i < jobs.length; i++) {
                if(jobs[i][0] <= time) {
                    pq.add(new Job(jobs[i][0], jobs[i][1]));
                    cnt++;
                } else {
                    break;
                }
            }
            
            if(pq.isEmpty()) {
                time = jobs[cnt][0];
                continue;
            }
            
           
            // 하나 처리
            Job curr = pq.poll();
            answer += ((time - curr.start) + curr.cost);
            
            time += curr.cost;
            end++;
            
        }

        return answer / jobs.length;
    }
    

  
}
