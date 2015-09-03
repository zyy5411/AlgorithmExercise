package books.jianzhioffer;

import java.util.Arrays;

import util.Tools;

public class Question42 {

	int MAX_NUM = 6;

	void printGailv(int n) {
		int[] probability;
		probability = new int[MAX_NUM * n + 1];
		Arrays.fill(probability, 0);
		calculateProbability(probability, n, 0, 1);
		Tools.print(probability);
		int total = 1, tmp = n;
		while (tmp-- > 0)
			total *= MAX_NUM;
		for (int i = n; i <= MAX_NUM * n; i++) {
			System.out.printf("%d : %f\n", i, (float) probability[i] / total);
		}
	}

	void calculateProbability(int[] proArr, int n, int sum, int count) {
		if (count > n) {
			proArr[sum]++;
			return;
		}
		for (int i = 1; i <= MAX_NUM; i++) {
			calculateProbability(proArr, n, sum + i, count + 1);
		}
	}

	public static void main(String[] args) {
		Question42 q = new Question42();
		//		int[] arr = { 1, 2, 4, 5, 7, 11, 15 };
		//		Tools.print(q.getPair(arr, 16));
		int n = 2;
		q.printGailv(n);
	}

}
