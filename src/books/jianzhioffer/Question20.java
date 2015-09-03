package books.jianzhioffer;

import util.Tools;

public class Question20 {

	void print(int[][] arr) {
		if (arr.length == 0)
			return;
		printMatrix(arr, 0, 0, arr.length - 1, arr[0].length - 1);
	}

	void printMatrix(int[][] arr, int startX, int startY, int endX, int endY) {
		if (startX > endX || startY > endY)
			return;
		if (startX == endX && startY == endY) {
			System.out.print(arr[startX][startY] + " ");
		}
		for (int i = startY; i < endY; i++) {
			System.out.print(arr[startX][i] + " ");
		}
		for (int i = startX; i < endX; i++) {
			System.out.print(arr[i][endY] + " ");
		}
		for (int i = endY; i > startY; i--) {
			System.out.print(arr[endX][i] + " ");
		}
		for (int i = endX; i > startX; i--) {
			System.out.print(arr[i][startY] + " ");
		}
		printMatrix(arr, startX + 1, startY + 1, endX - 1, endY - 1);
	}

	public static void main(String[] args) {
		int m = 5, n = 4;
		int[][] arr = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = n * i + j + 1;
			}
		}
		Tools.print(arr);
		Question20 q = new Question20();
		q.print(arr);
	}
}
