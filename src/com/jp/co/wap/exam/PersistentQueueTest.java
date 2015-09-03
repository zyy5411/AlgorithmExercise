package com.jp.co.wap.exam;

import org.junit.Test;

import util.Timer;

public class PersistentQueueTest {

	@Test
	public void testQueueEffect() {
		PersistentQueue<Integer> persistentQueue;
		PersistentQueueSample<Integer> persistentQueueSample;
		int n = 1000;
		Timer timer = Timer.getInstance();

		//================PersistentQueueSample=====================
		timer.start();
		persistentQueueSample = new PersistentQueueSample<Integer>();
		for (int i = 0; i < n; i++) {
			persistentQueueSample = persistentQueueSample.enqueue(i);
		}
		timer.clickAndPrintInterval("PersistentQueueSample:enqueue");
		for (int i = 0; i < n; i++) {
			persistentQueueSample.dequeue();
		}
		timer.clickAndPrintInterval("PersistentQueueSample:dequeue");
		for (int i = 0; i < n; i++) {
			persistentQueueSample.peek();
		}
		timer.clickAndPrintInterval("PersistentQueueSample:peek");
		timer.clickAndPrintTotalTimeCost("PersistentQueueSample:total ");

		//	==================PersistentQueue===================
		timer.start();
		persistentQueue = new PersistentQueue<Integer>();
		//		PersistentQueue<Integer> persistentQueue1 = persistentQueue.enqueue(1);
		//		PersistentQueue<Integer> persistentQueue2 = persistentQueue1.enqueue(2);
		//		PersistentQueue<Integer> persistentQueue3 = persistentQueue2.enqueue(3);
		//		Tools.println("peek:" + persistentQueue3.peek() + ","
		//				+ persistentQueue3.size());
		//		//		persistentQueue3.peek();
		//		PersistentQueue<Integer> persistentQueue4 = persistentQueue3.enqueue(4);
		//		Tools.println("peek:" + persistentQueue2.peek() + ","
		//				+ persistentQueue2.size());
		//		Tools.println("peek:" + persistentQueue4.peek() + ","
		//				+ persistentQueue4.size());
		//		PersistentQueue<Integer> persistentQueue5 = persistentQueue4.dequeue();
		//		PersistentQueue<Integer> persistentQueue6 = persistentQueue5.enqueue(6);
		//		Tools.println("peek:" + persistentQueue5.peek() + ","
		//				+ persistentQueue5.size());
		//		Tools.println("peek:" + persistentQueue6.peek() + ","
		//				+ persistentQueue6.size());
		//		//		persistentQueue6.printQueue();
		//		Tools.println("");
		//		//		persistentQueue5.printQueue();
		//		persistentQueue5 = persistentQueue5.dequeue();
		//		persistentQueue5 = persistentQueue5.dequeue();
		//		//		persistentQueue5.peek();
		//		Tools.println("peek:" + persistentQueue5.peek() + ","
		//				+ persistentQueue5.size());
		//		persistentQueue5 = persistentQueue5.dequeue();
		//		PersistentQueue<Integer> persistentQueue61 = persistentQueue5
		//				.enqueue(7);
		//		//		Tools.println("peek:" + persistentQueue61.peek() + ","
		//		//				+ persistentQueue61.size());
		//		persistentQueue61 = persistentQueue61.enqueue(8);
		//		Tools.println("peek:" + persistentQueue61.peek() + ","
		//				+ persistentQueue61.size());

		for (int i = 0; i < n; i++) {
			persistentQueue = persistentQueue.enqueue(i);
		}
		timer.clickAndPrintInterval("persistentQueue:enqueue");
		for (int i = 0; i < n; i++) {
			persistentQueue.peek();
			persistentQueue = persistentQueue.dequeue();
			//			persistentQueue.printQueue();
			//			Tools.println("" + persistentQueue.peek() + ","
			//					+ persistentQueue.size());
		}
		timer.clickAndPrintInterval("persistentQueue:dequeue");
		//		for (int i = 0; i < n; i++) {
		//			persistentQueue.peek();
		//		}
		//		timer.clickAndPrintInterval("persistentQueue:peek ");
		timer.clickAndPrintTotalTimeCost("persistentQueue:dequeue:total ");

		//		//	=================persistentQueueWithClass====================
		//		PersistentQueueStatic<Integer> persistentQueueWithClass;
		//		timer.start();
		//		persistentQueueWithClass = new PersistentQueueStatic<Integer>();
		//		for (int i = 0; i < n; i++) {
		//			persistentQueueWithClass = persistentQueueWithClass.enqueue(i);
		//		}
		//		timer.clickAndPrintInterval("PersistentQueueStatic:enqueue");
		//		for (int i = 0; i < n; i++) {
		//			persistentQueueWithClass.peek();
		//			persistentQueueWithClass = persistentQueueWithClass.dequeue();
		//			//			persistentQueue.printQueue();
		//			//			Tools.println("" + persistentQueue.peek() + ","
		//			//					+ persistentQueue.size());
		//		}
		//		timer.clickAndPrintInterval("PersistentQueueStatic:dequeue");
		//		//		for (int i = 0; i < n; i++) {
		//		//			persistentQueue.peek();
		//		//		}
		//		//		timer.clickAndPrintInterval("persistentQueue:peek ");
		//		timer.clickAndPrintTotalTimeCost("PersistentQueueStatic:total ");

	}
	//	@Test
	//	public void testPersistentQueue() {
	//		PersistentQueue<Integer> persistentQueue;
	//		Tools.println("testPersistentQueue");
	//		int n = 10;
	//		persistentQueue = new PersistentQueue<Integer>();
	//		for (int i = 0; i < n; i++) {
	//			Tools.print("\neeeeenqueue:");
	//			persistentQueue = persistentQueue.enqueue(i);
	//			Tools.print("size():" + persistentQueue.size() + ",");
	//			persistentQueue.printQueue();
	//			Tools.print("\ndddddequeue:");
	//			PersistentQueue<Integer> deq;
	//			deq = persistentQueue.dequeue();
	//			if (deq.size() > 1)
	//				deq = deq.dequeue();
	//			Tools.print("size():" + deq.size() + ",");
	//			persistentQueue.enqueue(11);
	//			persistentQueue.printQueue();
	//			Tools.print("--");
	//			deq.printQueue();
	//		}
	//	}
}
