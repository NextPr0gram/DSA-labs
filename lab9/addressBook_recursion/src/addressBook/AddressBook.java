/**
 * 
 */
package addressBook;

import dsa.LinkedList;
import dsa.List;

/**
 * For modelling an Email Address Book. 
 * 
 * This email address book keeps contacts according to the importance of 
 * these contacts to the address book owner. Hence, if the email contact
 * is very important, it is kept as the first entry within the address book.
 * If the contact is the least important, it would be kept as the last entry
 * in the address book.
 * 
 * @author Sylvia Wong
 * @version 23/11/2020
 */
public class AddressBook {

	private List<EmailContact> contacts;
	
	/**
	 * Constructor of an AddressBook object.
	 * When the AddressBook is created, it doesn't contain any email contacts.
	 */
	public AddressBook() {
		/* We use a linked list to keep the contacts in the address book.
		 * A LinkedNode object that corresponds to the first node in the linked list.
		 */
		contacts = new LinkedList<>();
	}

	/**
	 * Add the given email contact to the email address book 
	 * 	at a specified location.
	 * @param rank		The importance ranking of the given email contact
	 * @param eContact	The given email contact
	 */
	public void addEntry(int rank, EmailContact eContact)
	{
		/* ++++ Remember: 
		 * 		If a contact is ranked 1st, that means it should
		 * 			appear at the front of the list, i.e. at position 0.
		 */ 
		contacts.addAt(rank-1, eContact);
	}
	
	/**
	 * Re-rank an existing email contact within the email address book.
	 * 
	 * @param currentRanking	The current ranking of the required email contact
	 * @param newRanking		The new ranking of the required email contact
	 */
	public void reRankEntry(int currentRanking, int newRanking)
	{
		/* ++++ Re-rank an existing entry can be done by removing
		 * 			the entry and then add the entry to the list 
		 * 			at the required location.
		 */
		EmailContact eContact = contacts.removeAt(currentRanking);
		this.addEntry(newRanking, eContact);
	}
	
	/**
	 * @return	a String representation of this email address book
	 */
	@Override
	public String toString()
	{
		return contacts.toString();
	}
	
	/**
	 * The main: For quick testing...
	 * @param args
	 */
	public static void main(String[] args) {
		AddressBook aBook = new AddressBook();
		
		System.out.println("Add Amy, Brian and Cathy to the address book...");
		aBook.addEntry(1, new EmailContact("Amy", "amy@home.uk"));
		aBook.addEntry(2, new EmailContact("Brian", "brian@safari.com"));
		aBook.addEntry(3, new EmailContact("Cathy", "cath@cathaypacific.hk"));
		System.out.println(aBook.toString() + '\n');
		
		System.out.println("Add Sandy to the 3rd position...");
		aBook.addEntry(3, new EmailContact("Sandy", "rock@gmail.com"));
		System.out.println(aBook.toString() + '\n');
		
		System.out.println("Add Philip to the 1st position...");
		aBook.addEntry(1, new EmailContact("Philip", "phil@me.eu"));
		System.out.println(aBook.toString() + '\n');
		
		System.out.println("Move the contact at the 1st position to the 4th position...");
		aBook.reRankEntry(1,4);
		System.out.println(aBook.toString() + '\n');
	}

}
