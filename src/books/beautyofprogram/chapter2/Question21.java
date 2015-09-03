package books.beautyofprogram.chapter2;

public class Question21 {

	/**
	 * 找出一个数，所有可能的连续自然数之和的算式(所有的，有点困难咧）
	 * 9=2+3+4，3=1+2
	 * */
	void printAllContinueNumbers(int n){
		int maxN = (int)(-1+Math.sqrt(1+8*n))/2;
//		System.out.println(maxN);
		for(int i = 2; i <= maxN; i++){
			int m = n/i;
			if(i % 2 == 0){
				if((m+m+1) * (i/2) == n)
					printContinueNumbers(m-i/2+1,i);
			}
			else if(n == (n/i)*i){
				printContinueNumbers(n/i-i/2, i);		
			}
		}
	}
	void printContinueNumbers(int n,int count){
		while(0 != count--)
			System.out.print(" "+n++);
		System.out.println();
	}
	
	public static void main(String[] args) {
		Question21 q = new Question21();
		q.printAllContinueNumbers(10);
		q.printAllContinueNumbers(6);
		q.printAllContinueNumbers(18);
		q.printAllContinueNumbers(101);
		q.printAllContinueNumbers(10223);
	}

}
