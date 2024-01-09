import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;

public class GuessingGame{
  private LinkedBinaryTree<String> tree;

  /**
   * Builds the decision tree based on the contents of the given file
   *
   * @param filename the name of the input file
   * @throws FileNotFoundException if the input file is not found
   */
  
  //read the data file and save the entries into linked list
  public GuessingGame(String filename) throws FileNotFoundException
  {
    File inputFile = new File(filename);
    LinkedList<String> elements = new LinkedList<String>();
    try (Scanner scan = new Scanner(inputFile)) {
		while(scan.hasNext()){
		  elements.add(scan.nextLine());
		}
	}
    tree = build_tree(elements);
  }
  
 /* Produce the binary decision tree from the linked list
 The tree nodes should appear in pre-order 
 (i.e., in the order produced by a pre-order traversal of the tree).*/
  
  private LinkedBinaryTree<String> build_tree(LinkedList<String> elements){
    String element = elements.remove();
    if(element.charAt(0) == 'A'){
      return new LinkedBinaryTree<String>(element);
    }else{
      LinkedBinaryTree<String> left = build_tree(elements);
      LinkedBinaryTree<String> right = build_tree(elements);
      LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>(
        element, left, right);
      return tree;
    }
  }

  public static void main(String[] args){
	 
   try (Scanner scan = new Scanner(System.in)) {
    while (true) {
    		 
		System.out.println("Welcome to the Guessing Game");
		GuessingGame rules = null;
		boolean done = true;
		
		do{
			  System.out.print("Please enter the file name? ");
			  String fileName = scan.nextLine();
			  try{
			    rules = new GuessingGame(fileName);
			    done = true;
			  }catch(FileNotFoundException e){
			    done = false;
			  		}
		}while(!done);

		System.out.println("Please think of an object for me to guess...\n");

		LinkedBinaryTree<String> current = rules.tree;
		boolean end = false;
		
		while(!end){
		  String element = current.getRootElement();
		  if(element.charAt(0) == 'Q'){
		    System.out.print(element.substring(2)+" ");
		    String answer = scan.nextLine();
		    if(answer.toLowerCase().charAt(0) == 'y'){
		      current = current.getLeft();
		    }else{
		      current = current.getRight();
		    }
		  }else{
		    System.out.print("Would your object happen to be "
		      +current.getRootElement().substring(2)+" ");
		    String answer = scan.nextLine();
		    if(answer.toLowerCase().charAt(0) == 'y'){
		      System.out.println("Hooray, I got it right!");
		    }else{
		      System.out.println("Sorry, I cannot guess it.");
		    }
		    end = true;
		  }
		}
		 System.out.print("Would you like to play again?");
		    String response = scan.nextLine();
		    if (response.toLowerCase().charAt(0) == 'n')
		    	break;
    	 }
    	 }

     catch (EmptyCollectionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
 }


