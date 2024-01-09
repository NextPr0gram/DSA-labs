/**
 * 
 */
package dsa;

import java.util.NoSuchElementException;
/**
 * A LinkedList object models a linked list. It keeps track of the first
 * node of the linked list. 
 * 
 * @author Sylvia Wong
 * @version 23/11/2020
 */
public class LinkedList<E> implements List<E> {

	private LinkedNode<E> first;
	
	/**
	 * Constructor for an empty linked list.
	 */
	public LinkedList() {
		first = null;
	}

	/**
	 * Add an element at a specified position.
	 * 
	 * @param position
	 * @param element
	 */
	@Override
	public void addAt(int position, E element) throws NoSuchElementException
	{
		if (first == null)
			/* ++++ The linked list is empty. 
			 * 		Create a linked node for the given element and
			 * 		make this node become the first node in the list.
			 */
			first = new LinkedNode<E>(element);
		else
			/* ++++ The linked list has at least one node in it.
			 * 		Call the "addAt" of the first node in the list to
			 * 		add the given element in the list.
			 */
			first = first.addAt(position, element);
	}
	
	/**
	 * Remove the element at a specified position.
	 * 
	 * @param position	the position of the required node within the linked list
	 * @return	the element that is kept in the removed node.
	 * @throws NoSuchElementException
	 */
	@Override
	public E removeAt(int position) throws NoSuchElementException
	{
		if (first == null || position < 1)
			/* ++++ The linked list is empty. We can't remove anything.
			 * 		Simply notify the client of this problem.
			 */
			throw new NoSuchElementException();
		if (position > 1)
			/* !!!! We aren't deleting the first node.
			 * 		That's good, no need to change the value of the
			 * 		instance variable "first" in this class. 
			 * 
			 * 		Rewrite this to use the LinkedNode method 
			 *        removeFollowerAt(int, LinkedNode<E>)
			 *      to remove the linked node at the specified position
			 *      and return the element referenced by it.
			 *      
			 *      Don't forget that the first node in this linked list
			 *      is at position 1. If the specified position is 3,
			 *      we are to remove the 2nd follower node from the first node, 
			 *      because we handle the removal of the first node separately.
			 *      
			 */
			return first.getNext().removeFollowerAt(--position, first);
		else 
		{
			/* ++++ We need to remove the first node. This means that 
			 * 		the value of the instance variable "first" needs to
			 * 		to be changed. One good thing is that the deleting node
			 *		is at our grasp, so no need to go through the linked list
			 *		to find it.
			 *		Simply get the element kept in the linked node object
			 *		"first" for returning. Don't forget to remove the 
			 *		required node by updating the value kept 
			 *		in the instance variable "first".
			 */
			E result = first.getElement();
			first = first.getNext();
			return result;
		}
	}
	
	/**
	 * Return the element at a specified position 
	 * @param position	the position within the linked list that the required element
	 * 					is kept.
	 * @return the required element
	 */
	@Override
	public E getElementAt(int position) throws NoSuchElementException
	{
		if (first == null)
		{
			/* !!!! The list is empty, so the requested element mustn't exist. */
			throw new NoSuchElementException();
		}
		else
		{
			/* !!!! Each node in this linked list knows how to return the 
			 * 		element that is kept at the Nth succeeding node (N > 1).
			 * 		Hence, simply get the first node in this linked list to
			 * 		use this method for returning the required element.   
			 */
			return first.getElementAt(position);
		}
	}
	
	/**
	 * Return the size of this linked list 
	 * @return	the number of nodes that are in this linked list.
	 */
	@Override
	public int size()
	{
		if (first == null)
		{
			/* ++++ The list is empty */
			return 0;
		}
		else
		{
			/* !!!! Each node in this linked list knows how to count the number of
			 * 		elements that are following it (the count includes itself).
			 * 		Hence, simply get the first node in this linked list to
			 * 		use this method for returning the size of the linked list.   
			 */
			return first.size();
		}
	}
	
	/**
	 * Return a string representation of this list.
	 */
	@Override
	public String toString()
	{
		String result = "";
		
		if (first != null)
		{
			result += first.toString();
		}
		
		return result;
	}

	/**
	 * The Main: for quick testing...
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("Creating an empty linked list...");
		LinkedList<Integer> list = new LinkedList<>();
		System.out.println("The list contains: \n" + list.toString());
		
		System.out.println("Add 3 numbers to the linked list...");
		list.addAt(0, 1);
		System.out.println("The list contains: \n" + list.toString());
		list.addAt(1, 2);
		System.out.println("The list contains: \n" + list.toString());
		list.addAt(1, 3);
		System.out.println("The list contains: \n" + list.toString());
		
		System.out.println("The list has " + list.size() + " element(s).");
		
		System.out.println("Get an existing element...");		
		System.out.println(list.getElementAt(2));
		
		System.out.println("Try to get a non-existing element...");
		try{
			list.getElementAt(4);
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
		
		System.out.println("Remove the 2nd element...");
		System.out.println(list.removeAt(2));
		System.out.println("The list contains: \n" + list.toString());
		
		System.out.println("Remove the last element...");
		System.out.println(list.removeAt(2));
		System.out.println("The list contains: \n" + list.toString());
		
		System.out.println("Remove the first element...");
		System.out.println(list.removeAt(1));
		System.out.println("The list contains: \n" + list.toString());
		
		System.out.println("The list has " + list.size() + " element(s).");
	}
}
