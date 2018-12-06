package edu.wmich.cs1120.LA7;

public class Node<E> implements INode<E>{

	private Node<E> next; 
	private E data;
	/**
	 * create a new node 
	 * @param dataValue data to store inside the node
	 */
	public Node(E dataValue) {
		data = dataValue;
		next = null;
	}				
	/**
	 * insert a new node before a specified existing node
	 * @param dataValue	value to store inside the node
	 * @param nextNode node thats after the new node
	 */
	public Node(E dataValue, Node<E> nextNode) {
		data = dataValue;
		next = nextNode;
	}

	@Override
	public E getData() {
		return data;
	}

	@Override
	public Node<E> getNext() {
		return next;
	}

	@Override
	public void setNext(Node<E> next) {
		this.next = next;
	}


}
