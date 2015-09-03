package online_test.zoj;

import java.util.Scanner;

public class P1001 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String[] n = scanner.nextLine().split(" ");
			int a = Integer.parseInt(n[0]);
			int b = Integer.parseInt(n[1]);
			System.out.println(a + b);
		}

	}

}
