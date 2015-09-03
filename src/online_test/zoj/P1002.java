package online_test.zoj;

import java.util.Scanner;

public class P1002 {

	char[][] arr;
	int max;
	int n;
	static final char EMPTY = '.';
	static final char WALL = 'X';
	static final char BLOCKHOUSE = 'o';
	static final char NOALLOWED = '-';

	void init(int n, Scanner scanner) {
		this.n = n;
		this.max = 0;
		arr = new char[n][n];
		for (int i = 0; i < n; i++) {
			String l = scanner.next();
			for (int j = 0; j < n; j++) {
				arr[i][j] = l.charAt(j);
			}
		}
	}

	/**
	 * 
	 * @param index ���еݹ������
	 * @param count �ﱤ��
	 * @return max
	 */
	int findMax(int index, int count) {
		if (index >= n * n) {
			max = Math.max(count, max);
			return max;
		}
		int i = index / n;
		int j = index % n;
		if (EMPTY == arr[i][j] && available(i, j)) {
			arr[i][j] = BLOCKHOUSE;
			findMax(index + 1, count + 1);
			arr[i][j] = EMPTY;
		}
		//��λ�ò��ŵﱤ
		findMax(index + 1, count);

		return max;
	}

	//ֻ��Ҫ������ϽǼ���
	boolean available(int row, int col) {
		//������
		for (int i = col - 1; i >= 0; i--) {
			if (BLOCKHOUSE == arr[row][i])
				return false;
			if (WALL == arr[row][i])
				break;
		}
		//����ϱ�
		for (int i = row - 1; i >= 0; i--) {
			if (BLOCKHOUSE == arr[i][col])
				return false;
			if (WALL == arr[i][col])
				break;
		}

		return true;
	}

	public static void main(String[] args) {
		P1002 p = new P1002();
		Scanner scanner = new Scanner(System.in);
		int n;
		while (scanner.hasNext() && (0 != (n = scanner.nextInt()))) {
			p.init(n, scanner);
			System.out.println(p.findMax(0, 0));
		}
	}

}
