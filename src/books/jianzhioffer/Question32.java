package books.jianzhioffer;

public class Question32 {

	int calculateOne(int n) {
		int count = 0;
		int tmp = n;
		int base = 1;
		while (tmp / 10 > 0) {
			tmp /= 10;
			base *= 10;
		}
		//1234¿ªÊ¼Ê±£¬base=1000
		while (base > 0) {
			int currentNum = (n / base) % 10;
			if (1 == currentNum) {
				int backNum = n % base;
				count += backNum + 1;
				System.out.println("1:" + count);
			} else if (currentNum == 0) {
				int preNum = (n / base) / 10;
				count += (preNum) * base;
				System.out.println("0:" + count);
			} else if (1 < currentNum) {
				int preNum = (n / base) / 10;
				count += (preNum + 1) * base;
				System.out.println(">1:" + count);
			}
			base /= 10;
		}
		return count;
	}

	int calculateOne2(int n) {
		int count = 0;
		for (int i = 1; i <= n; i++) {
			count += getOneCount(i);
		}
		return count;
	}

	int getOneCount(int n) {
		int c = 0;
		while (n > 0) {
			if (n % 10 == 1)
				c++;
			n /= 10;
		}
		return c;
	}

	public static void main(String[] args) {
		Question32 q = new Question32();
		System.out.println(q.calculateOne(1000));
		System.out.println(q.calculateOne2(1000));
	}

}
