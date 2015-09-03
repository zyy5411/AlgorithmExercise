package books.jianzhioffer;

public class Chapter3_3_11 {

	double power(double base, int exponent) {
		int n = 1;
		double result = 1, tmp = base;
		while (n <= exponent) {
			if (n * 2 <= exponent) {
				n *= 2;
				tmp *= tmp;
			} else {
				exponent -= n;
				result *= tmp;
				tmp = base;
				n = 1;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Chapter3_3_11 c = new Chapter3_3_11();
		System.out.println(c.power(2, 16));
	}

}
