package writenexam.baidu;

import java.util.Random;

import util.Tools;

public class SegmentSort {

	Random random = new Random();
	
	//��maxSegmentLenΪ��󳤶ȣ������������
	void disorder(int[] arr, int maxSegmentLen){
		int ranInt = random.nextInt(maxSegmentLen);
		int i = 1;
		while(i+ranInt < arr.length){
			disorderSegment(arr, i, i+ranInt);
			i = i+ranInt+1;
			ranInt = random.nextInt(maxSegmentLen);
		}
	}
	
	//��arr[start,end]�����д���
	void disorderSegment(int[] arr,int start,int end){
		int len = end-start;
		for(int i = 1;i<len;i++){
			int r = random.nextInt(len);
			Tools.swap(arr,start+i,start+r);
		}
	}
	
	void sort(int[] arr, int maxSegmentLen){
//		����Ԥ�ȶ�С�����򣬵�ʱ���ķѸ���
//		for(int i=1;i<arr.length;i += maxSegmentLen)
//			Arrays.sort(arr,i,i+maxSegmentLen);
		
		for(int i=1;i<arr.length;i += maxSegmentLen)
			insertInto(arr, i, maxSegmentLen);
	}
	
	//��arr[sortedEnd,sortedEnd+maxSegmentLen)��
	//����arr[0,sortedEnd)�н��в�������
	void insertInto(int[] arr, int sortedEnd,int maxSegmentLen){
		int insertEnd = Math.min(arr.length, sortedEnd+maxSegmentLen);
		for(int i = sortedEnd;i<insertEnd;i++){
			int t = i;
			while(arr[t] < arr[t-1]){
				Tools.swap(arr, t, t-1);
				t--;
			}
		}
	}
	
	public static void main(String[] args) {
		int LEN = 1000;
		int SEGMENT_LEN = 22;
		int[] arr = new int[LEN+1];
		arr[0] = Integer.MIN_VALUE;	//arr[0]��Ϊ�ڱ�
		//��ʼ��
		for(int i = 1;i<=LEN;i++){
			arr[i] = i;
		}
		SegmentSort segmentSort = new SegmentSort();
		segmentSort.disorder(arr, SEGMENT_LEN);
		
		segmentSort.sort(arr,SEGMENT_LEN);
//		������֤�����Ƿ���ȷ
//		for(int i = 1;i<=LEN;i++)
//			if(arr[i] != i)
//				Tools.println("sort error! "+i); 
	}
}
