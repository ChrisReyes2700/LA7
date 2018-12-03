package edu.wmich.cs1120.LA7;

public class PriorityQueue<E> {

	private Node<E> front;
	private Node<E> rear;

	// Determine if the priority queue is empty.
	public boolean isEmpty() {
		if (front == rear)
			return true;
		else
			return false;
	}

	// Add object received to the priority queue taking into consideration the rules
	// governing priority.
	public void enqueue(E data) {
		Node<E> node = new Node<E>(data);
		rear.setNext(rear);
		rear = node;
		
		
		//TO DO add priority sorting system
		
	}

	// Remove the next object to be processed from the priority queue.
	public E dequeue() {
		
		return front.getData();
	}

	// Print the contents of the queue
	public void Qprint() {
		Node<E> buf = rear;
		while (buf != front) {
			System.out.println(buf.toString());
			buf = buf.getNext();
		}
	}

}
