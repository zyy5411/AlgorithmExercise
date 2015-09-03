package books.jianzhioffer;

public class chapter2_4_1 {

	int findMin(int[] arr) {
		int l = 0, u = arr.length - 1;
		int k = arr[0];
		if (k < arr[u])
			return k;
		while (l + 1 != u) {
			int m = (l + u) / 2;
			if (arr[m] > k) {
				l = m;
			} else if (arr[m] < k) {
				u = m;
			}
		}
		return arr[u];

	}

	public static void main(String[] args) {
		int[] arr = { 3, 4, 5, 1, 2 };
		//		int[] arr = { 1, 2, 3, 4, 5 };
		chapter2_4_1 c = new chapter2_4_1();
		System.out.println(c.findMin(arr));
	}
}
