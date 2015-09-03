package com.jp.co.wap.exam;

import java.util.NoSuchElementException;

public class PersistentQueueStatic<E> {
	private final int size;

	private final E data;

	//the first queue whose data is equal peek's return value.
	private final FirstElement<E> firstElement;

	//the queue before this queue
	private final PersistentQueueStatic<E> preQueue;

	public PersistentQueueStatic() {
		size = 0;
		data = null;
		preQueue = null;
		firstElement = null;
	}

	private PersistentQueueStatic(FirstElement<E> firstElement,
			PersistentQueueStatic<E> preQueue, E data, int size) {
		this.firstElement = firstElement;
		this.preQueue = preQueue;
		this.data = data;
		this.size = size;
	}

	public PersistentQueueStatic<E> enqueue(E e) {
		if (null == e)
			throw new IllegalArgumentException();

		return new PersistentQueueStatic<E>(
				null == firstElement ? new FirstElement<E>() : firstElement,
				this, e, size + 1);
	}

	public PersistentQueueStatic<E> dequeue() {
		if (0 == size)
			throw new NoSuchElementException();

		return new PersistentQueueStatic<E>(new FirstElement<E>(), preQueue, data,
				size - 1);

	}

	public E peek() {
		if (0 == size)
			throw new NoSuchElementException();

		if (null == firstElement.data) {
			int n = size;
			//			int count = 0;
			PersistentQueueStatic<E> firstQueue = this;
			while (n-- > 1) {
				firstQueue = firstQueue.preQueue;
				//				count++;
			}
			//			Tools.println("count:" + count);
			firstElement.data = firstQueue.data;
		}
		return firstElement.data;
	}

	/**
	 * use to store the FirstElement's data.
	 * @author zyy
	 *
	 * @param <T>
	 */
	private static class FirstElement<T> {
		T data;
	}

	public int size() {
		return size;
	}

}
