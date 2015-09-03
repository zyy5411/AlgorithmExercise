package books.jianzhioffer;

public class Question31 {

	int max = Integer.MIN_VALUE;

	int subMax(int[] arr) {
		int currentValue = 0;
		for (int i = 0; i < arr.length; i++) {
			currentValue += arr[i];
			if (currentValue > max)
				max = currentValue;
			currentValue = currentValue < 0 ? 0 : currentValue;
		}
		return max;
	}

	int subMaxDynamic(int[] arr) {
		max = Integer.MIN_VALUE;
		maxAhead(arr, arr.length - 1);
		return max;
	}

	int maxAhead(int[] arr, int index) {
		if (index == 0)
			return arr[0];
		int m = maxAhead(arr, index - 1);
		m = m < 0 ? 0 : m;
		if (arr[index] + m > max)
			max = arr[index] + m;
		return arr[index] + m;
	}

	public static void main(String[] args) {
		Question31 q = new Question31();
		int[] arr = { 1, -2, 3, 10, -4, 7, 2, -5 };
		System.out.println(q.subMax(arr));
		System.out.println(q.subMaxDynamic(arr));

	}
}
