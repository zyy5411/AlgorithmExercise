package books.jianzhioffer;

public class Chapter2_3_1 {

	//¼ÆÊý
	int count = 0;

	boolean find(int[][] arr, int k) {
		int endColoum, startRow, col = arr[0].length, row = arr.length;
		endColoum = col - 1;
		startRow = 0;
		while (startRow < row && endColoum >= 0) {
			count++;
			if (arr[startRow][endColoum] == k) {
				return true;
			} else if (arr[startRow][endColoum] > k) {
				endColoum--;
			} else if (arr[startRow][endColoum] < k) {
				startRow++;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		int[][] arr = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 },
				{ 6, 8, 11, 15 } };
		Chapter2_3_1 c = new Chapter2_3_1();
		System.out.println(c.find(arr, 1));
		System.out.println(c.count);
	}
}
