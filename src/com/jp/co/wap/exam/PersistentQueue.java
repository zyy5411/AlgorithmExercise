package com.jp.co.wap.exam;

import java.util.NoSuchElementException;

public class PersistentQueue<E> {
	private final int size;

	private final E data;

	//the first queue whose data is equal peek's return value.
	private final FirstElement<E> firstElement;

	//the queue before this queue
	private final PersistentQueue<E> preQueue;

	public PersistentQueue() {
		size = 0;
		data = null;
		preQueue = null;
		firstElement = null;
	}

	private PersistentQueue(FirstElement<E> firstElement,
			PersistentQueue<E> preQueue, E data, int size) {
		this.firstElement = firstElement;
		this.preQueue = preQueue;
		this.data = data;
		this.size = size;
	}

	public PersistentQueue<E> enqueue(E e) {
		if (null == e)
			throw new IllegalArgumentException();

		return new PersistentQueue<E>(
				null == firstElement ? new FirstElement<E>() : firstElement,
				this, e, size + 1);
	}

	public PersistentQueue<E> dequeue() {
		if (0 == size)
			throw new NoSuchElementException();

		return new PersistentQueue<E>(new FirstElement<E>(), preQueue, data,
				size - 1);

	}

	public E peek() {
		if (0 == size)
			throw new NoSuchElementException();

		if (null == firstElement.data) {
			int n = size;
			PersistentQueue<E> firstQueue = this;
			while (n-- > 1) {
				firstQueue = firstQueue.preQueue;
			}
			firstElement.data = firstQueue.data;
		}
		return firstElement.data;
	}

	/**
	 * use to store the FirstElement's data.
	 * @author zyy
	 * @param <T>
	 */
	private static class FirstElement<T> {
		T data;
	}

	public int size() {
		return size;
	}

}
