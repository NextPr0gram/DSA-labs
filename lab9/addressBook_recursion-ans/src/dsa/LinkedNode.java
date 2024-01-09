package dsa;

import java.util.NoSuchElementException;
/**
 * A LinkedNode object models a node within a linked structure.
 * 
 * Each LinkedNode object can be seen as the beginning of a linked list.
 * Hence, we define methods to add and remove a node that follows a LinkedNode
 * object.
 * 
 * @author Sylvia Wong
 * @version 09/12/2022
 */
public class LinkedNode<T> {

	private T element;
	private LinkedNode<T> next;
	
	/**
	 * Constructor
	 * @param content
	 */
	public LinkedNode(T content) {
		this.element = content;
		next = null;
	}
	
	/**
	 * Add the given element to the list so that it appears 
	 * after the specified position in the list. 
	 * If the specified position is 0,
	 * that means the given element should appear at the front of the list.
	 * 
	 * @param position	the LinkedNode that the given node should appear 
	 * @param node		the given LinkedNode object
	 * @return			the first node within this linked list
	 */
	public LinkedNode<T> addAt(int position, T element)
	{
		// Calls the private method "addNodeAt" to perform the task.
		return addNodeAt(position, element, null);
	}
	
	/* ++++
	 * Add a specified element to the list of element at a specified location,
	 * i.e. after how many elements from the beginning of this list.
	 * If position = 0, the element will be added at the front of the list.
	 *   
	 * As each element in the list is kept in a LinkedNode object. 
	 * We need to create a new LinkedNode object for keeping the given element.
	 * 
	 * This method uses recursion to find the location where the given element
	 * should be added to the list.
	 *  
	 * @param position	the position where the given element should appear
	 * @param element	the new element that is to be added to the list
	 * @param previous	the node that points at this LinkedNode object
	 */
	private LinkedNode<T> addNodeAt(int position, T element, LinkedNode<T> previous)
					throws NoSuchElementException
	{
		if (position == 0)
		{
			// Add the given node at the front of the list:
			// 1. Create a new LinkedNode for the given element 
			LinkedNode<T> node = new LinkedNode<>(element);
			// 2. The new node should point at this LinkedNode object. 
			node.setNext(this);
			/* 3. Make the new node follow the node that precedes this 
			 *    LinkedNode object.
			 */
			if (previous != null)
				previous.setNext(node);
			
			return node;
		}
		else
			if (next == null)
				if (position > 1)
					/* The specified location is beyond the actual length
					 * of this linked list.
					 */
					throw new NoSuchElementException();
				else
				{
					// Add the given node at the end of the list:
					// 1. Create a new LinkedNode for the given element 
					LinkedNode<T> node = new LinkedNode<>(element);
					// 2. The new node is the "new" last node in the list. 
					this.setNext(node);
					return this;
				}
			else
				/* The given element should be added somewhere 
				 * 	in the rest of the list.
				 * 	(Let's call this list the "remainder list".)
				 * So, add the element to the remainder list.
				 * This element should be added at (position - 1) 
				 * 		in the remainder list. 
				 * This LinkedNode object will therefore become 
				 * 		the previous node of the remainder list.  
				 */
			{
				next.addNodeAt(position-1, element, this);
				return this;
			}
	}
	
	/**
	 * Remove the element that is at a specified position in the list
	 * which starts with this LinkedNode object.
	 *   
	 * @param position	the position which the required node appears
	 * @return	the element that is kept in the deleted node
	 */
	public T removeAt(int position) throws NoSuchElementException
	{
		/* ++++ This method can't be easily implemented using recursion in 
		 * 		Java because Java allows only 1 value to be returned by 
		 * 		a method. To avoid the complication and redundancy of 
		 * 		having to create an addition class for "wrapping" up two
		 * 		values for returning, we simply use the iteration implementation.
		 */
		LinkedNode<T> previous = null;
		LinkedNode<T> current = this;
		while (position > 1) {
			if (current != null) {
				previous = current;
				current = current.getNext();
				position--;
			}
			else
				throw new NoSuchElementException();
		}
		previous.setNext(current.getNext());
		return current.getElement();
	}	
	
	/**
	 * Remove the element that is at a specified follower node in the
	 * sequence of linked nodes which starts with this LinkedNode object.
	 * This method uses recursion to find the required follower node
	 * for removal.
	 *   
	 * @param position	the position which the required node appears
	 * @param previous	the node that points at this LinkedNode object
	 * @return	the element that is kept in the deleted node
	 * @throws	NoSuchElementException	
	 * 			This exception is thrown when there is no follower node.
	 */
	public T removeFollowerAt(int position, LinkedNode<T> previous) 
			throws NoSuchElementException 
	{
		/* !!!! Base case 1: When position is 1, 
		 *                   we need to look no further, simply remove this LinkedNode object
		 *                   and returns its element.  
		 */
		if (position == 1) {
			previous.next = this.next;
			return this.element;
		}
		else {
			/* !!!! Base case 2: This node does not have a follower node. */
			if (this.next == null) {
				throw new NoSuchElementException();
			}
			/* !!!! Recursive case: When position > 1, 
			 *                      we need to continue with the node traversal 
			 *                      to find the required node for removal.
			 *                      
			 *                      Don't forget to update 'position' and 'previous'.
			 */
			else {
				return this.next.removeFollowerAt(position-1, this);
			}
		}
	}

	/**
	 * Return the element that is kept at a specified node that
	 * follows this LinkedNode.

	 * Otherwise, the link is traversed in order to get to the required node.
	 * 
	 * @param position
	 * @return the element in a specified node
	 */
	public T getElementAt(int position) throws NoSuchElementException
	{
		/* !!!! Use recursion to implement this method.
		 * 		The base case is when this node is the node that the required
		 * 			element is kept (i.e. when position = 1).
		 * 		The recursive step is when the required element is in a 
		 * 			following node further down the list 
		 * 			(i.e. when position > 1).
		 * 
		 * 		What should be returned in each case?
		 * 
		 * 		Throw a NoSuchElementException if the specified position is
		 * 			beyond the size of the list headed by this LinkedNode object
		 * 			(i.e. when the processing has come to the end of the list, but
		 * 					position is still > 1).
		 */
		if (position == 1)
			 /* The required element is kept in this node. 
			  * Return its element.
			  */
			return this.getElement();
		else
			if (next == null)
				throw new NoSuchElementException();
			else
				/* Return the element at (position - 1) in the 
				 * remainder of this list.
				 */
				return next.getElementAt(position - 1);
	}
	
	/**
	 * Return the number of nodes that are linked together
	 * (i.e. the number of nodes that follows this LinkedNode object plus one).
	 * @return the size of the link
	 */
	public int size()
	{
		/* !!!! Use recursion to implement this method.
		 * 		The base case is when this node has no follower.
		 * 		The recursive step is when this node has a follower.
		 * 
		 * 		What should be returned in each case?
		 */
		if (next == null)
			/* No node is following this LinkedNode object. 
			 * Hence, the size of this link is 1.
			 */
			return 1;
		else
			/* There should be at least one node following.
			 * Hence, the size of this link is 1 plus the size
			 * of the following link. 
			 */
			return 1 + next.size();
	}

	/** 
	 * Return a string representation of the elements in the nodes that
	 * are linked with this LinkedNode object (this LinkedNode object inclusive)
	 * @return string 
	 */
	@Override
	public String toString()
	{
		/* !!!! Use recursion to implement this method.
		 * 		The base case is when this node has no follower.
		 * 		The recursive step is when this node has a follower.
		 * 
		 * 		What does the returning String look like in each case?
		 */
		if (this.getNext() != null)
		{
			 /* There is at least one node following this LinkedNode object.
			  * Concatenate the string version of the element 
			  * that is kept in this node with the rest of the elements. 
			  * Return the resulting string.
			  */
			return element + "\n" + next.toString();
		}
		else
			/* There is no node following this LinkedNode object. 
			 * Simply return a string version of the element kept in this node. 
			 */			
			return element.toString();
	}
	
	/**
	 * Set the given LinkedNode object to be the node that 
	 * is following this LinkedNode object.
	 * @param the LinkedNode that follows this LinkedNode object
	 */
	public void setNext(LinkedNode<T> node) {
		this.next = node;
	}
	
	/**
	 * Return the LinkedNode object that follows this LinkedNode object.
	 * @return the LinkedNode that follows this LinkedNode object
	 */
	public LinkedNode<T> getNext() {
		return next;
	}
	
	/**
	 * Return kept in this LinkedNode object.
	 * @return the element kept in this LinkedNode object
	 */
	public T getElement() {
		return element;
	}
}
