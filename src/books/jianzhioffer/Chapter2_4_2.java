package books.jianzhioffer;

public class Chapter2_4_2 {
	int fibonacci(int n) {
		if (n == 0)
			return 0;
		if (1 == n)
			return 1;
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	int fibonacci2(int n) {
		int sum[] = { 0, 1 };
		int index = 0;
		while (--n >= 0) {
			int nextIndex = (index + 1) % 2;
			sum[index] += sum[nextIndex];
			if (n <= 1)
				break;
			index = nextIndex;
		}
		return sum[index];
	}

	public static void main(String[] args) {
		Chapter2_4_2 c = new Chapter2_4_2();
		//		System.out.println(c.fibonacci(10));
		for (int i = 0; i < 20; i++)
			System.out.println(c.fibonacci(i) == c.fibonacci2(i));
		//		System.out.println(c.fibonacci2(10));
	}
}
