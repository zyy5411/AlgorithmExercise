package online_test.zoj;

import java.util.Scanner;

public class P1003_UNSOLVED {
	boolean largerTrue = false, lowerTrue = false;

	//默认a是较大的数
	int getWinner(int a, int b) {
		int larger = Math.max(a, b);
		int lower = Math.min(a, b);

		calculateWinner(larger, lower, 2);
		if (!largerTrue && lowerTrue)
			return lower;
		return larger;
	}

	void calculateWinner(int larger, int lower, int n) {
		//n不断递增，由于a和b对于小于n的数都探测过了，剩下的乘数必须都大于n的，
		//所以只有满足a/n>n && b/2>n的n才有可能成功
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
		//b获取该数作为乘数
		if (lower % n == 0) {
			//			Tools.println("b chu-" + larger + "," + lower + "," + n);
			calculateWinner(larger, lower / n, n + 1);
		}
		//a获取该数作为乘数
		if (larger % n == 0) {
			//			Tools.println("a chu-" + larger + "," + lower + "," + n);
			calculateWinner(larger / n, lower, n + 1);
		}
		//a、b都不使用该数作为乘数
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
