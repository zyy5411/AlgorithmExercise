package books.jianzhioffer;

public class Chapter2_4_3 {

	int translate(String str) {

		int sum = 0;
		int base = 1;
		for (int i = str.length() - 1; i >= 0; i--) {
			char c = str.charAt(i);
			sum += (c - 'A' + 1) * base;
			base *= 26;
		}
		return sum;
	}

	public static void main(String[] args) {
		Chapter2_4_3 c = new Chapter2_4_3();
		System.out.println(c.translate("B"));
		System.out.println(c.translate("AB"));
		System.out.println(c.translate("AAB"));
	}

}
