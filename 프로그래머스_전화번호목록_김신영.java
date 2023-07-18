 import java.util.*; 

/**
* 프로그래머스 전화번호 목록 https://school.programmers.co.kr/learn/courses/30/lessons/42577
* 효율성테스트3 ( 279.48ms, 222MB )
* @author 김신영
* 한 전화번호가 다른 전화번호의 접두어가 되는지 검사하기 위해서 다른 전화번호 모두를 검사
* 전화번호 1개를 기준으로 이 전화번호를 길이 0부터 기준전화번호길이-1 까지 substring하여
* 잘린 문자열과 같은 값이 key로 존재한다면 한 번호가 다른 번호의 접두사인 경우
* 중복 전화번호가 존재하지 않으므로 이 경우는 생각 X
* HashMap을 사용하면 검색시간을 줄일 수 있다.
*/ 

class Solution {
    public boolean solution(String[] phone_book) {
        HashMap<String, Integer> map = new HashMap<>();
        
        // phone_book의 전화번호를 모두 해시맵에 넣어준다.
        for(String s : phone_book){
            map.put(s, 0);
        }
        
        // 전화번호의 개수만큼 검사
        for(int i = 0; i < phone_book.length; i++) {
            // 전화번호의 길이만큼 검사
            for(int j = 0; j < phone_book[i].length(); j++) {
                // 만약 현재 전화번호를 substring한 결과가 key값으로 존재하면 그 값이 접두어가 된다
                // substirng(start, end)에서 end는 포함하지 않음
                if(map.containsKey(phone_book[i].substring(0, j))) {
                    // 따라서 false 반환
                    return false;
                }
            }
        }
        
        // 다 검사했는데 접두어가 없었다면 true 반환한다.
        return true;
    }
}