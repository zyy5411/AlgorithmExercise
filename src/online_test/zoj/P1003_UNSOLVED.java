package online_test.zoj;

import java.util.Scanner;

public class P1003_UNSOLVED {
	boolean largerTrue = false, lowerTrue = false;

	//Ĭ��a�ǽϴ����
	int getWinner(int a, int b) {
		int larger = Math.max(a, b);
		int lower = Math.min(a, b);

		calculateWinner(larger, lower, 2);
		if (!largerTrue && lowerTrue)
			return lower;
		return larger;
	}

	void calculateWinner(int larger, int lower, int n) {
		//n���ϵ���������a��b����С��n������̽����ˣ�ʣ�µĳ������붼����n�ģ�
		//����ֻ������a/n>n && b/2>n��n���п��ܳɹ�
		if (101 == n || largerTrue)
			return;
		if (larger == 1 && lower == 1) {
			largerTrue = true;
			//			Tools.println("Atrue-" + larger + "," + lower + "," + n);
			return;
		}
		if (lower == 1) {
			//			Tools.println("Btrue-" + larger + "," + lower + "," + n);
			lowerTrue = true;
		}

		//		Tools.println("" + larger + "," + lower + "," + n);
		//b��ȡ������Ϊ����
		if (lower % n == 0) {
			//			Tools.println("b chu-" + larger + "," + lower + "," + n);
			calculateWinner(larger, lower / n, n + 1);
		}
		//a��ȡ������Ϊ����
		if (larger % n == 0) {
			//			Tools.println("a chu-" + larger + "," + lower + "," + n);
			calculateWinner(larger / n, lower, n + 1);
		}
		//a��b����ʹ�ø�����Ϊ����
		calculateWinner(larger, lower, n + 1);
	}

	public static void main(String[] args) {
		P1003_UNSOLVED p = new P1003_UNSOLVED();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			System.out.println(p.getWinner(a, b));
		}
	}
}
