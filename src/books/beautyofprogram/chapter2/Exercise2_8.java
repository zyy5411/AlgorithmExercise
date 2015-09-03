package books.beautyofprogram.chapter2;

public class Exercise2_8 {

	long getZeroOrOne(int n){
		int sum = 0;
		int lastN = n % 10;
		do{
			int lastSum = sum % 10;
			sum += n * getFit(lastSum, lastN);
			sum /= 10;
		}while(!isZeroAndOne(sum));
		
		System.out.println(""+sum);
		return 0;
	}

	int getFit(int weishu, int n){
//		int[] chengshu = {0,1,5,7,5,4,5,3,5,9};
		int result = 1;
		int result1 = -1;
		while(true){
			if((weishu + result * n) % 10 == 1 )
				break;
			else if( result1 == -1 && (weishu + result * n) % 10 == 0)
				result1 = result;
				
			if(++result == 10){
				result = result1;
				break;
			}
			
		}
		System.out.println("weishu:"+weishu+",result:"+result);
		return result;
	}
	
	boolean isZeroAndOne(long n){
		while(n > 0){
			if(1 < n%10)
				return false;
			n /= 10;
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		new Exercise2_8().getZeroOrOne(221);

	}

}