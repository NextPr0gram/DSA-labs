/**
 * 
 */
package dictionary;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import dictionary.exceptions.EmptyMagicalBagException;

/**
 * A MagicalBag is a set-like collection (i.e. all elements in the collection must be unique)
 * modelled by a HashSet.
 * 
 * @author S H S Wong
 * @version 03-10-2020
 * @param <T>
 */
public class MagicalBag4<T> implements MagicalBag<T> {
	/* A random number generator for helping with the picking and removing
	 * 	of random elements from the bag.
	 */ 
	private static Random randomiser = new Random();
	
	private Set<T> contents;
	
	/**
	 * Constructor
	 */
	public MagicalBag4() {
		/* !!!! Initialise the instance variable with an appropriate object from JCF.
		 * 		Don't forget to set the initial capacity of the object.
		 */		
		int capacity = (int) (WordPicker.MAX_BAG_CAPACITY * 1.5);
		contents = new HashSet<T>(capacity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see dictionary.MagicalBag#add(java.lang.Object)
	 */
	@Override
	public void add(T item) {
		contents.add(item);
	}
	
	/*
	 * (non-Javadoc)
	 * @see dictionary.MagicalBag#pick()
	 */
	@Override
	public T pick() {
		/* Checks if the set is empty */
		if(contents.isEmpty()){
			throw new EmptyMagicalBagException();
		}
		
		int index = randomiser.nextInt(size());

		/* ++++
		 * The implementation of HashSet within JCF doesn't have 
		 * 	a method for returning a random item.
		 * 
		 * To implement this operation, we use an enhanced for loop to 
		 * go through the elements one by one. When we have gone through
		 * sufficient number of elements as indicated by 
		 * the generated random number to pick, we note down that
		 * object reference. 
		 * 
		 * (As you can tell, this way of implementing remove is
		 * 	rather clumsy. Hence, for our intended usage of a MagicalBag object,
		 *  HashSet is not a suitable collection.)
		 */		
		int count = 0;
		for(T element : contents) {
			if(count == index) {
				return element;	// Item found!
			}
			count++;
		}
		
		return null;

	}
	
	/*
	 * (non-Javadoc)
	 * @see dictionary.MagicalBag#remove()
	 */
	@Override
	public T remove() {
		/* Checks if the set is empty */
		if(contents.isEmpty()) {
			throw new EmptyMagicalBagException();
		}
		
		T item = null;
		
		/* Picks the item randomly */
		int index = randomiser.nextInt(size());
	
		/* !!!!
		 *  The implementation of HashSet within JCF doesn't have 
		 * 	a method for removing a random item.
		 * 
		 * To implement this operation, we use an enhanced for loop to 
		 * go through the elements one by one. When we have gone through
		 * sufficient number of elements as indicated by 
		 * the generated random number to remove, we remove that
		 * object from the HashSet using its object reference. 
		 */		
		int count = 0;
		for(T element : contents) {
			if(count == index) {
				item = element;	// Item found!
				break;			// Quit the loop.
			}
			count++;
		}
		// Remove the required element.
		if(item != null) {
			contents.remove(item);
		}
		
		return item;
	}
	
	/*
	 * (non-Javadoc)
	 * @see dictionary.MagicalBag#size()
	 */
	@Override
	public int size() {
		return contents.size();
	}
	
	/**
	 * Returns the contents of the bag as a string.
	 * @return
	 */
	@Override
	public String toString() {
		String result = "";
		for(T item : contents) {
			result += item + "  ";
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return contents.iterator();
	}
	
}
