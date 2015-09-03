package books.beautyofprogram.chapter3;

public class Exercise3_11 {

	int[] arr = { 1, 2, 3, 4, 4, 5, 5, 5, 7, 8, 9, 9 };

	int getIndex(int v) {
		int start = 0, end = arr.length - 1, mid;
		while (start < end-1) {
			mid = start + (end - start) / 2;
			if (arr[mid] == v)
				return mid;
			else if (arr[mid] < v)
				start = mid;
			else if (arr[mid] > v)
				end = mid;
		}
		if(arr[end] == v)
			return end;
		if(arr[start] == v)
			return start;
		return -1;
	}

	int getMinIndex(int v){
		int start = 0, end = arr.length - 1, mid;
		while (start < end-1) {
			mid = start + (end - start) / 2;
			if (arr[mid] < v)
				start = mid;
			else if (arr[mid] >= v)
				end = mid;
		}
		if(arr[end] == v)
			return end;
		if(arr[start] == v)
			return start;
		return -1;
	}
	
	int getMaxIndex(int v){
		int start = 0, end = arr.length - 1, mid;
		while (start < end-1) {
			mid = start + (end - start) / 2;
			if (arr[mid] <= v)
				start = mid;
			else if (arr[mid] > v)
				end = mid;
		}
		if(arr[end] == v)
			return end;
		if(arr[start] == v)
			return start;
		return -1;
	}

	int getMaxLowerThanV(int v){
		int start = 0, end = arr.length - 1, mid;
		while (start < end-1) {
			mid = start + (end - start) / 2;
			if (arr[mid] < v)
				start = mid;
			else if (arr[mid] >= v)
				end = mid;
		}
		if(arr[end] < v)
			return end;
		if(arr[start] < v)
			return start;
		return -1;
	}

	int getMinLargerThanV(int v){
		int start = 0, end = arr.length - 1, mid;
		while (start < end-1) {
			mid = start + (end - start) / 2;
			if (arr[mid] <= v)
				start = mid;
			else if (arr[mid] > v)
				end = mid;
		}
		if(arr[end] > v)
			return end;
		if(arr[start] > v)
			return start;
		return -1;
	}
	
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 4, 5, 5, 5, 7, 8, 9, 9 };
		int[] testV = {0,10,1,9,4,5};
		Exercise3_11 e = new Exercise3_11();
		for(int i:testV){
			System.out.println(e.getIndex(i));
			System.out.println(e.getMinIndex(i));
			System.out.println(e.getMaxIndex(i));
			System.out.println(e.getMaxLowerThanV(i));
			System.out.println(e.getMinLargerThanV(i));
		}
		
	}

}
