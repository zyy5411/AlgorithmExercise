package writenexam.baidu;

import java.util.Arrays;

import util.Tools;

public class ASort {

	void sort(int arr[]){
		int max = Integer.MIN_VALUE,min = Integer.MAX_VALUE;
		//1、找出最大值与最小值
		for(int i = 0;i<arr.length;i++){
			if(arr[i] > max)
				max = arr[i];
			if(arr[i] < min)
				min = arr[i];
		}
		int span = max-min+1;
		//建立maps数组，初始化为0
		int maps[] = new int[span];
		for(int i = 0;i<span;i++){
			maps[i] = 0;
		}
		//将所有的数，都hash进maps数组
		//hash(n) = n-min
		for(int i = 0;i<arr.length;i++){
			maps[arr[i]-min]++;
		}
		int t = 0;
		for(int i = 0;i<span;i++){
			while(maps[i] > 0){
				arr[t++] = i+min;
				maps[i]--;
			}
		}
//		Tools.print(arr);
	}
	
	public static void main(String[] args) {
		int arr[]={52,53,52,67,64,63,66,67,60,52,53,52,67,64,63,66,67,60,69,68,64,62,51,62,49,62,55,59,55,51,62,49,62,55,59,51,62,49,62,55,59,51,62,49,62,55,59,51,62,49,62,55,59,51,62,49,62,55,59};
		int arr1[]={52,53,52,67,64,63,66,67,60,52,53,52,67,64,63,66,67,60,69,68,64,62,51,62,49,62,55,59,55,51,62,49,62,55,59,51,62,49,62,55,59,51,62,49,62,55,59,51,62,49,62,55,59,51,62,49,62,55,59};
		Tools.println("len:"+arr.length);
		long t = Tools.getMilionTime();
		new ASort().sort(arr);
		Tools.println(""+(Tools.getMilionTime()-t));
		
		t = Tools.getMilionTime();
		Arrays.sort(arr1);
//		Tools.print(arr1);
		Tools.println(""+(Tools.getMilionTime()-t));
		
	}
	
	

}
