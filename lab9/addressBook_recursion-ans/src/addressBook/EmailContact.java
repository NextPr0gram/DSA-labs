/**
 * 
 */
package addressBook;

/**
 * An EmailContact object keeps track of the email address of a contact (person).
 * 
 * @author Sylvia Wong
 * @version 23/11/2020
 */
public class EmailContact {

	private String name;	// the name of the contact (person)
	private String email;	// the email of this contact
	
	/**
	 * Constructor for an EmailContact object
	 * @param name	the name of a contact
	 * @param email	the email of this contact
	 */
	public EmailContact(String name, String email) {
		this.name = name;
		this.email = email;
	}

	/**
	 * @return	a String representation of this object
	 */
	@Override
	public String toString()
	{
		return name + '\t' + email;
	}
	
	/**
	 * Change the email address of this contact.
	 * @param newEmail
	 */
	public void changeEmail(String newEmail)
	{
		email = newEmail;
	}
}
