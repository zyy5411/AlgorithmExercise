package writenexam.baidu;

public class SameContinueCharCount {

	void printSameCount(char[] s){
		System.out.println(getCountRecursion(s, 0, s.length-1));
	}

	//ËÑË÷[d,u]ÇøÓò
	int getCountRecursion(char[] s, int d, int u){
		if(u <= d)
			return 1;
		int mid = (d+u)/2;
		int cLeft = getCountRecursion(s, d, mid-1);
		int cRight = getCountRecursion(s, mid+1, u);
		int cMid = 1,i = mid;
		//ËÑË÷cMidÁ½±ßµÄ×Ö·û
		while(--i>=d && s[mid] == s[i] )
			cMid++;
		i = mid;
		while(++i<=u && s[mid] == s[i] )
			cMid++;
		
		return max(max(cLeft,cRight),cMid);
	}
	
	int max(int i,int j){
		return i > j ? i : j;
	}
	
	public static void main(String[] args) {
		new SameContinueCharCount().printSameCount("aaabAAAAbc".toCharArray());
	}

}
