import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * �������� 2�� �־��� ���� �������� ���� �ö󰡸� �˻� 
 * �����̳� ���������� ���� ������ ������ ���� ��ȯ�ϰ� �̵� 
 * ����, ���������������ϴ� ���� ������ �ٽ� ���� ����
 * 30,812kb, 142ms
 * </pre>
 * @author ��ſ�
 *
 */
public class D4_1210_SW�����ذ�⺻2����_Ladder1_��ſ� {

	public static void main(String[] args) throws Exception {
		BufferedReader br;
		StringTokenizer st;

		for (int test_case = 1; test_case <= 10; test_case++) {
			br = new BufferedReader(new InputStreamReader(System.in));
			br.readLine(); // �׽�Ʈ���̽� ��ȣ �Է�
			int answer = 0;
			int[][] ladder = new int[100][100];
			int goal = -1;
			
			// ��ٸ� �迭 �����
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
					if (ladder[i][j] == 2) { goal = j; }
				}
			}

			int dir = 1; // ���� ������ ������ ����
			int r = 99, c = goal;	// ���� ��ġ
			// 0�࿡ �����Ҷ����� �ݺ�
			while (r > 0) {
				switch (dir) {
				case 1: // ��
					// ���� �����ϴٰ� ���������� ���� ������
					if (c + 1 < 100 && ladder[r][c + 1] == 1) {
						dir = 3;	// ���� ���������� ����
						c++;		// ��ĭ �̵�
					}
					// ���� �����ϴٰ� �������� ���� ������
					else if (c - 1 >= 0 && ladder[r][c - 1] == 1) {
						dir = 2;	// ���� ��ȯ
						c--;		// ��ĭ �̵�
					} 
					// �¿� ��� �ƴϸ� ���� ��� ����
					else {
						r--;
					}
					break;
				case 2: // ��
					// �������� �����ϴ� ���� ���� ������
					if (r - 1 >= 0 && ladder[r - 1][c] == 1) {
						dir = 1;	// ���� ��ȯ�ϰ�
						r--;		// ��ĭ �̵�
					} 
					// �׷��� ������ �������� ��� �̵�
					else {
						c--;
					}
					break;
				case 3: // ��
					// ���������� �����ϴ� ���� ���� ������
					if (r - 1 >= 0 && ladder[r - 1][c] == 1) {
						dir = 1;	// ���� ��ȯ�ϰ�
						r--;		// ��ĭ �̵�
					} 
					// �׷��� ������ ���������� ��� �̵�
					else {
						c++;
					}
					break;
				}
			}

			// �������� ������ ���� ��
			answer = c;

			System.out.println("#" + test_case + " " + answer);
		}

	}

}
