package edu.wmich.cs1120.LA7;

public class LinkedList<E> {

	private Node<E> head;
	private Node<E> tail;

	
	/**
	 * Determine if the LinkedList is empty.
	 * @return	true if empty false if not
	 */
	 
	public boolean isEmpty() {
		if (head == null)
			return true;
		else
			return false;
	}

	/**
	 * Add object received to the linked list if he/she has the priority
	 * @param item	the object to be added to the list
	 */
	
	public void add(E item) {
		if (isEmpty()) {
			Node<E> newNode = new Node<>(item);
			head = newNode;
			tail = head;
		} else {
			tail.setNext(new Node<E>(item));
			tail = tail.getNext();
		}
	}

	/**
	 *  get the object in specific position in the LinkedList used to print the
	 *  enrolled student contents inside the Course class and return the name
	 * @param position index of the linked list to return the data
	 * @return	data at the specified position
	 */
	public E get(int position) {
		E obj;
		Node<E> headCopy = head;
		
		//iterates headCopy to given position through linked list
		for(int i = 0; i < position; i++) {
			headCopy = headCopy.getNext();
		}
		
		obj = headCopy.getData();
		return obj;
	}

	/**
	 *  Return number of elements in the list.
	 * @return total count of elements in the linked list
	 */
	public int size() {
		int count = 0;
		Node<E> headCopy = head;

		while (headCopy != null) {
			count++;
			headCopy = headCopy.getNext();
		}

		return count;

	}
}
	