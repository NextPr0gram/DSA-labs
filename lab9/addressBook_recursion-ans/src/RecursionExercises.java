import java.util.Arrays;

/**
 * 
 */

/**
 * This class includes static methods for carrying out simple operations using recursion.
 * These methods demonstrate how recursion could be used in place of iteration.
 * 
 * @author Sylvia Wong
 * @version 20/11/2022
 */
public class RecursionExercises {

	public static final int[][] NUMBER_ARRAYS = 
		{{13}, 
		 {13, 50}, 
		 {13, 50, 41},
		 {13, 50, 41, 33},
		 {13, 50, 41, 33, 6},
		 {13, 50, 41, 33, 6, 120},
		 {13, 50, 41, 33, 6, 120, 64}};
	
	public static final int[] NUMBERS = {13, 50, 41, 33, 6, 120, 64};
	
	/**
	 * Sum all numbers in the specified array.
	 * 
	 * @param integers
	 * @return	the sum of the numbers in the specified array of integers
	 */
	public static int sum(int[] integers) {
		return sum(integers, integers.length-1);
	}
	
	/*
	 * A private recursive method for carrying out the summing.
	 * 
	 * @param integers
	 * @param cursor
	 * @return	the sum of the numbers in the specified array that have been processed so far
	 */
	private static int sum(int[] integers, int cursor) {
		
		if (cursor == 0) {
			return integers[cursor];
		}
		else {
			return integers[cursor] + sum(integers, cursor-1);
		}
	}
	
	/**
	 * Sum all even numbers in the specified array.
	 * 
	 * @param integers
	 * @return	the sum of the even numbers in the specified array of integers
	 */
	public static int sumEven(int[] integers) {
		/* !!!! Write a return statement here.
		 * 
		 * This return statement calls a private helper method for
		 * performing the summing. 
		 * 
		 * The helper method uses recursion to process each element in 
		 * the given int[] backward, i.e. starting from the last cell in the array.
		 */
		
		return sumEven(integers, integers.length-1);
	}
	
	
	/* !!!! Write the required helper method for sumEven(int[]) below.
	 * 
	 * This helper method uses recursion to process each element in 
	 * the given int[] backward, i.e. starting from the last cell in the array.
	 */
	/*
	 * A private recursive method for carrying out the summing of even numbers.
	 * 
	 * @param integers
	 * @param cursor
	 * @return	the sum of the even numbers in the specified array that have been processed so far
	 */
	private static int sumEven(int[] integers, int cursor) {
		
		int result = 0;
		
		if (isEven(integers[cursor])) {
			result = integers[cursor];
		}
		
		if (cursor == 0) {
			return result;
		}
		else {
			return result + sumEven(integers, cursor-1);
		}	
	}
	
	/*
	 * Returns true if the specified number is even, false otherwise.
	 */
	private static boolean isEven(int number) {
		return (number % 2 == 0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		for (int[] numbers : RecursionExercises.NUMBER_ARRAYS) {
			System.out.println("Sum of all numbers in " + Arrays.toString(numbers) 
				+ ": " + sum(numbers));
		}
		
		System.out.println("\nSum of all even numbers in " + Arrays.toString(RecursionExercises.NUMBERS) 
			+ ": " + sumEven(RecursionExercises.NUMBERS));

	}

}
