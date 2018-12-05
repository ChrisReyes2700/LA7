package edu.wmich.cs1120.LA7;

public class PriorityQueue<E extends Comparable<E>> {

	private Node<E> front;
	private Node<E> rear;

	// Determine if the priority queue is empty.
	public boolean isEmpty() {
		if (front == null)
			return true;
		else
			return false;
	}

	// Add object received to the priority queue taking into consideration the rules
	// governing priority.
	public void enqueue(E data) {
		Node<E> ref = front;
		Node<E> insert = new Node<E>(data);
		
		//insert is the first node
		if(isEmpty()) {
			front = insert;
			rear = front;
		}
		//ref is the only node
		else if(ref.getNext() == null) {
			if(insert.getData().compareTo(ref.getData()) == 1) {
				insert.setNext(ref);
				rear = ref;
				front = insert;
			}
			//insert is same or lower priority as next ref node
			else {
				ref.setNext(insert);
				rear = insert;
			}
		}
		//queue has multiple nodes
		//insert is higher priority than front
		else if(insert.getData().compareTo(ref.getData()) == 1) {
			insert.setNext(ref);
			front = insert;
		}
		//insert is same priority as front
		else if(insert.getData().compareTo(ref.getData()) == 0) {
			insert.setNext(ref.getNext());
			ref.setNext(insert);
		}
		//insert is lower priority than front
		else if(insert.getData().compareTo(ref.getData()) == -1) {
			
			boolean sorted = false;
			//loop until rear is next node
			while(!sorted && ref.getNext() != rear) {
				//insert is higher priority than next ref node
				if(insert.getData().compareTo(ref.getNext().getData()) == 1) {
					insert.setNext(ref.getNext());
					ref.setNext(insert);
					sorted = true;
				}
				//insert is same priority as next ref node
				else if(insert.getData().compareTo(ref.getNext().getData()) == 0) {
					insert.setNext(ref.getNext().getNext());
					ref.getNext().setNext(insert);
					sorted = true;
				}
				//insert is lower priority than next ref node
				else if(insert.getData().compareTo(ref.getNext().getData()) == -1) {
					ref = ref.getNext();	//move to next node
				}
			}
			
			//if while loop runs and still not sorted
			if(!sorted && ref.getNext() == rear) {
				//insert is higher priority than rear
				if(insert.getData().compareTo(ref.getNext().getData()) == 1) {
					insert.setNext(ref.getNext());
					ref.setNext(insert);
					sorted = true;
				}
				//insert is same or lower priority as next ref node
				else {
					insert.setNext(ref.getNext().getNext());
					ref.getNext().setNext(insert);
					rear = insert;
					sorted = true;
				}
			}
			else {
				//error, no other cases
			}
		}
	}

	// Remove the next object to be processed from the priority queue.
	public E dequeue() {
		Node<E> rearCopy = rear;
		Node<E> frontCopy = front;
		
		front = front.getNext();
		//in case of one node, sets front to null and rear to front
		if(frontCopy == rearCopy) {
			rear = front;
		}
		
		return frontCopy.getData();
	}

	// Print the contents of the queue
	public void Qprint() {
		Node<E> buf = front;
		if(buf == null) {
			System.out.println("Queue is Empty");
		}
		else {
			while (buf != rear) {
				System.out.println(((Request)buf.getData()).getCourseNumber() + ((Request)buf.getData()).getCourseDept() + ((Request)buf.getData()).getStudentDept() + ((Request)buf.getData()).getStudentName());
				buf = buf.getNext();
			}
			//final line print
			System.out.println(((Request)buf.getData()).getCourseNumber() + ((Request)buf.getData()).getCourseDept() + ((Request)buf.getData()).getStudentDept() + ((Request)buf.getData()).getStudentName());
		}
	}

}
