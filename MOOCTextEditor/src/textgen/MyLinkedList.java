package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		this.head = new LLNode<E>(null); // create start sentinel node
		this.tail = new LLNode<E>(null); // create end sentinel node
		this.head.next = this.tail;
		this.tail.prev = this.head;
		this.size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		this.add(this.size, element);
/*		LLNode<E> lastNode = this.tail.prev;
		LLNode<E> tailNode = this.tail;
		LLNode<E> newNode  = new LLNode<E>(element);
		
		newNode.next = tailNode;
		newNode.prev = lastNode;
		
		lastNode.next = newNode;
		tailNode.prev = newNode;
		
		this.size ++;
		//System.out.println("size: " + this.size + " element: " + element);
*/		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index < 0 || index > size -1 ) {
			// throw exception
			throw new IndexOutOfBoundsException();
		}
		LLNode <E> node = this.head.next;
		for(int i = 0; i < index; i ++) {
			// find node with req index
			node = node.next;
		}
		return node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(index < 0 || index > this.size) {
			// throw exception
			throw new IndexOutOfBoundsException();
		}
		if(element == null) {
			// null element is not allowed by description
			throw new NullPointerException();
		}
		// find node to insert
		LLNode <E> node = this.head.next;
		for(int i = 0; i < index; i ++) {
			// find node with req index
			node = node.next;
		}
		LLNode<E> nextNode = node;
		LLNode<E> prevNode = node.prev;
		LLNode<E> newNode  = new LLNode<E>(element);
		
		newNode.next = nextNode;
		newNode.prev = prevNode;
		
		prevNode.next = newNode;
		nextNode.prev = newNode;
		
		this.size ++;
		return;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		// check boundaries
		if(index < 0 || index > this.size -1) {
			// throw exception
			throw new IndexOutOfBoundsException();
		}
		// find node to delete
		LLNode <E> node = this.head.next;
		for(int i = 0; i < index; i ++) {
			// find node with req index
			node = node.next;
		}
		LLNode<E> delNode  = node;
		LLNode<E> prevNode = node.prev;
		LLNode<E> nextNode  = node.next;
		
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
		
		this.size --;
			
		return delNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		// check boundaries
		if(index < 0 || index > this.size -1) {
			// throw exception
			throw new IndexOutOfBoundsException();
		}
		if(element == null) {
			// null element is not allowed by description
			throw new NullPointerException();
		}
		// find node to replace
		LLNode <E> node = this.head.next;
		for(int i = 0; i < index; i ++) {
			// find node with req index
			node = node.next;
		}
		E prevData = node.data;
		node.data = element;
		return prevData;
	}

	@Override
	public String toString() {
		String s = new String(":");
		LLNode <E> node = this.head.next;
		for(int i = 0; i < this.size; i ++) {	
			s = s + node.data.toString() + " ";
			node = node.next;
		}
		return "MyLinkedList [head=" + head + ", tail=" + tail + ", size=" + size + s + "]";
	} 
	
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
