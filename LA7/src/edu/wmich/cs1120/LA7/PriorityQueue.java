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
		boolean sorted = false;
		Node<E> rearcopy = rear;
		rear = new Node<E>(data);
		rear.setNext(rearcopy);

		
		//I think this works, have not tested however
		Node<E> sortingNode = rear;
		while (sorted = false) {
			if (((Request) sortingNode.getData()).isSameDept() < ((Request) sortingNode.getNext().getData())
					.isSameDept()) {
				// If added student does not have course priority over next then
				if (((Request) sortingNode.getData()).getYearsToGraduate() > ((Request) sortingNode.getNext().getData())
						.getYearsToGraduate()) {
					// If added student does not have years to graduate priority over next then
					if (((Request) sortingNode.getData()).getGPA() < ((Request) sortingNode.getNext().getData())
							.getGPA()) {
						// If added student does not have GPA priority over next then
						sorted = true;
					} else {
						//Swaps the data held within the nodes, updated reference to rear if needed
						E temp = sortingNode.getNext().getData();	
						if (rear == sortingNode) {
							rear.setData(temp);
						}
						sortingNode.getNext().setData(sortingNode.getData());
						sortingNode.setData(temp);
						sortingNode = sortingNode.getNext();
					}
				} else {
					E temp = sortingNode.getNext().getData();
					if (rear == sortingNode) {
						rear.setData(temp);
					}
					sortingNode.getNext().setData(sortingNode.getData());
					sortingNode.setData(temp);
					sortingNode = sortingNode.getNext();
				}
			} else {
				E temp = sortingNode.getNext().getData();
				if (rear == sortingNode) {
					rear.setData(temp);
				}
				sortingNode.getNext().setData(sortingNode.getData());
				sortingNode.setData(temp);
				sortingNode = sortingNode.getNext();
			}
		}

	}

	// Remove the next object to be processed from the priority queue.
	public E dequeue() {
		Node<E> rearCopy = rear;
		Node<E> frontCopy = front;

		while (rearCopy.getNext() != front) {
			rearCopy = rearCopy.getNext();
		}

		front = rearCopy;
		front.setNext(null);

		return frontCopy.getData();
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
