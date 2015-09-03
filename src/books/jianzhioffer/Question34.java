package books.jianzhioffer;

import java.util.Arrays;

public class Question34 {

	//空间复杂度为O(N)，时间复杂度为0(N)
	int UglyNumber(int n) {
		int len = n << 2;
		boolean[] arr = new boolean[len];
		int[] bases = { 2, 3, 5 };
		Arrays.fill(arr, false);
		arr[1] = true;
		for (int i = 0; i < bases.length; i++) {
			int m = bases[i], increase = 1;
			while (m < len) {
				//				Tools.print(" " + i);
				arr[m] = true;
				m = bases[i] * (++increase);
			}
		}
		int count = 0;
		for (int i = 1; i < len; i++) {
			if (arr[i]) {
				count++;
				if (count == n)
					return i;
			}
		}
		return -1;
	}

	//空间复杂度为O(1)，时间复杂度为0(N)
	int UglyNumber2(int n) {
		int multyBase[] = { 2, 3, 5 };
		int multyIndex[] = { 1, 1, 1 };
		int count = 1, minIndex = 0, uglyNumber = 1;
		while (count < n) {
			minIndex = multyBase[0] * multyIndex[0] < multyBase[1]
					* multyIndex[1] ? 0 : 1;
			minIndex = multyBase[minIndex] * multyIndex[minIndex] < multyBase[2]
					* multyIndex[2] ? minIndex : 2;
			int newUglyNumber = multyBase[minIndex] * multyIndex[minIndex];
			if (uglyNumber != newUglyNumber) {
				count++;
				uglyNumber = newUglyNumber;
			}
			multyIndex[minIndex]++;
		}

		return multyBase[minIndex] * (multyIndex[minIndex] - 1);
	}

	public static void main(String[] args) {
		Question34 q = new Question34();
		int n = 1500;
		System.out.println(q.UglyNumber(n));
		System.out.println(q.UglyNumber2(n));
	}
}
