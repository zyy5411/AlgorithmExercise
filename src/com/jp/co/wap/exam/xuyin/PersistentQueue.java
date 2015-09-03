/*
 * Copyright 2014, Works Applications
 * 
 * This file is part of the solution to the programming 
 * examination released by Works Application Inc. 
 * 
 * The interfaces are provided by Works Applications and 
 * the methods are implemented by Ying Xu. 
 *
 */

package com.jp.co.wap.exam.xuyin;

import java.util.NoSuchElementException;

/**
 * Created on Sep. 28th, 2013 22:59:07 PM
 * Last modified on Sep. 29th, 2013 15:02:53 PM
 * @author Ying Xu
 */
public class PersistentQueue<E>{
	
	/**
	 * The node structure for the queue
	 * @author Ying Xu
	 * @param <E>
	 */
	public class QueueNode {
		private E elem;
		private QueueNode next;
		
		public QueueNode(E elem, QueueNode parent, QueueNode next){
			this.elem = elem;
			this.next = next;
		}

		public E getElem() {
			return elem;
		}

		public QueueNode getNext() {
			return next;
		}

		public void setNext(QueueNode next) {
			this.next = next;
		}

		
	}

	
	private QueueNode front;    //the front node of the queue
	private QueueNode tail;     //the tail node of the queue
	private int size;           //the size of the queue
	
	/**
	 * Default constructor.
	 * Empty Queue with front and tail initialized as null and size as 0
	 */
	public PersistentQueue(){
		this.tail = this.front = null;
		this.size = 0;
	}
	
	/**
	 * Constructor with an initial item.
	 * With front and tail pointing to the same node and size initialized as 1. 
	 * @param elem
	 */
	public PersistentQueue(E elem){
		QueueNode node = new QueueNode(elem, null, null);
		this.tail = this.front = node;
		this.size = 1;
	}
	
	/**
	 * Copy constructor. 
	 * Shallow copy if the next node of another.tail is null;
	 * Deep copy if the next node of the another.tail is not null.
	 * To ensure O(1) time complexity in most cases.
	 * @param another
	 */
	public PersistentQueue(PersistentQueue<E> another){
		if(another.isEmpty()){
			this.size = 0;
			return;
		}
		if(another.tail.getNext() == null){
			this.front = another.front;
			this.tail = another.tail;
			this.size = another.size;
		}else{
			QueueNode tmpNode = another.front;
			while(tmpNode != another.tail){
				this.appendNode(tmpNode.getElem());
				tmpNode = tmpNode.next;
			}
			this.appendNode(tmpNode.getElem());
		}
	}
	
	/**
	 * Append a new node to the tail of the queue
	 * @param elem
	 */
	private void appendNode(E elem) {
		QueueNode newNode = new QueueNode(elem, this.tail, null);
		if(this.tail != null)
			this.tail.next = newNode;
		this.tail = newNode;
		if(this.front == null)
			this.front = this.tail;
		this.size++;
	}

	/**
	 * Return the queue that adds an item to the tail of this queue without modifying this queue
	 * Time Complexity: O(1) in average
	 * If add an item to this queue for the first time, the time complexity is O(1)
	 * If add an item to the same queue multiple times, except for the first time, the rest have a time complexity of O(n)
	 * @param e
	 * @return
	 */
	public PersistentQueue<E> enqueue(E e){
		if(e == null)
			throw new IllegalArgumentException();
		PersistentQueue<E> newQueue = new PersistentQueue<E>(this);
		QueueNode newNode = new QueueNode(e, newQueue.tail, null);
		if(front == null){
			newQueue.tail = newQueue.front = newNode;
			newQueue.size = 1;
		}else{
			newQueue.tail.setNext(newNode);
			newQueue.tail = newNode;
			newQueue.size++;
		}
		return newQueue;
	}
	
	/**
	 * Return the queue that remove the object at the head of the queue without modifying this queue
	 * Time Complexity: O(1)
	 * @return
	 */
	public PersistentQueue<E> dequeue(){
		if(isEmpty())
			return null;
		PersistentQueue<E> newQueue = new PersistentQueue<E>();
		newQueue.front = this.front.getNext();
		newQueue.tail = this.tail;
		newQueue.size = this.size-1;
		if(newQueue.isEmpty()){
			newQueue.tail = newQueue.front = null;
		}
		return newQueue;
	}
	
	/**
	 * Looks at the object which is the head of this queue without removing it from the queue
	 * Time Complexity: O(1)
	 * @return
	 */
	public E peek(){
		if(this.isEmpty())
			throw new NoSuchElementException();
		return this.tail.getElem();
	}
	
	/**
	 * Returns the number of objects in this queue
	 * @return
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Return whether this queue is empty
	 * @return
	 */
	public boolean isEmpty(){
		return size==0;
	}
	
	/**
	 * Print this queue from the front to tail
	 */
	public void printQueue(){
		System.out.println();
		if(isEmpty())
			return;
		QueueNode tmpNode = this.front;
		while(tmpNode != this.tail){
			System.out.println(tmpNode.getElem());
			tmpNode = tmpNode.getNext();
		}
		System.out.println(this.tail.getElem());
	}


}
