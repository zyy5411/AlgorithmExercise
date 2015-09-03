package books.beautyofprogram.chapter3;
//package study.chapter3;
//
//public class Exercise3_7 {
//
//	int MAX_SIZE = 50;
////	int start,end;
//	
//	Data[] dataIndex;
//	
//	Data firstData, lastData;
////	Heap heap;
//	
//	public Exercise3_7(){
//		dataIndex = new Data[MAX_SIZE+1];
////		heap = new Heap(MAX_SIZE);
//		firstData = lastData = new Data(-Integer.MIN_VALUE);
////		start = end = 0;
//		
//	}
////	class Heap{
//		//从1开始计数
//		int maxSize;
////		Data[] heap;
//		int len;
////		public Heap(int size){
////			maxSize = size;
////			len = 0;
////			heap = new Data[size+1];
////		}
//		
//		void add(Integer integer){
//			len++;
//			heap[len] = new Data;
//			siftUp();
//		}
//		
//		void remove(Integer integer){
//			swap(1, len);
//			len--;
//			siftDown();
//		}
//		
//		private void siftUp(){
//			int index = len;
//			while(index>1 && dataIndex[index/2].data < dataIndex[index].data){
//				swap(index/2, index);
//				index /= 2;
//			}
//		}
//		
//		private void siftDown(){
//			int index = 1;
//			while(index*2 <= len){
//				int maxChild = index*2;
//				if(index*2+1 <= len && maxChild < heap[index*2 + 1])
//					maxChild = index*2;
//				if(heap[index] > heap[maxChild])
//					break;
//				swap(index, maxChild);
//				index = maxChild;
//			}
//		}
//		
//		void swap(int i, int j){
//			Data t = dataIndex[i];
//			dataIndex[i] = dataIndex[j];
//			dataIndex[j] = t;
//		}
//		
//		Integer getMax(){
//			return dataIndex[1].data;
//		}
//		
//		
////	}
//
//	class Data{
//		int data;
//		int index;
//		Data next;
//		public Data(int i){
//			data = i;
//			next = null;
//		}
//	}
//	
//	void EnQueue(int i){
//		Data data = new Data(i);
//		dataIndex[++len] = data;
//		lastData.next = data;
//		lastData = data;
//		siftUp();
//	}
//	
//	int DeQueue(){
//		
//		
//		return 0;
//	}
//	
//	int MaxElement(){
//		
//		return 0;
//	}
//	
//	public static void main(String[] args) {
//		Exercise3_7 e = new Exercise3_7();
//	}
//
//}
