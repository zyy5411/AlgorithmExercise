package com.jp.co.wap.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PersistentQueueSample<E> {

	private List<E> queue;

	public PersistentQueueSample() {
		queue = new ArrayList<E>();
	}

	private PersistentQueueSample(List<E> queue) {
		this.queue = queue;
	}

	public PersistentQueueSample<E> enqueue(E e) {
		if (null == e)
			throw new IllegalArgumentException();
		List<E> clone = new ArrayList<E>(queue);
		clone.add(e);
		return new PersistentQueueSample<E>(clone);
	}

	public PersistentQueueSample<E> dequeue() {
		if (queue.isEmpty())
			throw new NoSuchElementException();
		List<E> clone = new ArrayList<E>(queue);
		clone.remove(0);
		return new PersistentQueueSample<E>(clone);
	}

	public E peek() {
		if (queue.isEmpty())
			throw new NoSuchElementException();
		return queue.get(0);
	}

	public int size() {
		return queue.size();
	}

	//	public void printQueue() {
	//		print(size);
	//	}
	//
	//	private void print(int size) {
	//		if (size < 1)
	//			return;
	//		preQueue.print(size - 1);
	//		System.out.print(" " + data);
	//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
