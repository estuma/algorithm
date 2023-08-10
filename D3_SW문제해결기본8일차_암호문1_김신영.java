import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * ���� ��ȣ���� �ް�
 * ��ɾ �޾� �ε��� ��ü���� ���ο� ������ �߰����ش�.
 * �߰��� ���� �߰��ؾ��ϹǷ� linkedList ���
 * </pre>
 * @author ��ſ�
 *
 */

public class D3_SW�����ذ�⺻8����_��ȣ��1_��ſ� {

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int test_case = 1; test_case <= 10; test_case++) {            
            // 1. ���� ��ȣ���� ���� N ( 10 �� N �� 20 �� ����)
            int N = Integer.parseInt(br.readLine());
            List<Integer> list = new LinkedList<>();
            // 2. ���� ��ȣ��
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            // 3. ���� ��ȣ���� ���� N ( 10 �� N �� 20 �� ����)
            int M = Integer.parseInt(br.readLine());
            // 4. ��ɾ�
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) {
                st.nextToken();    // I
                int idx = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                for(int j = 0; j < num; j++) {
                	// �ε��� ��ġ�� ���ο� ���� �߰�
                    list.add(idx++, Integer.parseInt(st.nextToken()));
                }
            }
            // ���� �����
            sb.append("#" + test_case + " ");
            // 10��°���� ���
            for(int i = 0; i < 10; i++) {
                sb.append(list.get(i) + " ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
        
    }
}