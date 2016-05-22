package oop.ex5.orders;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * This class is initialized with a comparator between files. And a modifier.
 * The object receives an array of files and returns that array
 * sorted according to the comparator. 
 * @author alonav11
 *
 */
public class FileOrder{
	
	private static final int VALID_NUMBER_OF_MODIFIERS = 1;
	private static final String ORDER_MODIFIER = "REVERSE";
	
	Comparator<File> comparator;
	boolean hasReverseModifier = false;
	
	/**
	 * Constructor. Checks to see that there is only one or no modifiers, and if so
	 * that it is a REVERSE modifier. If it indeed a reverse modifier, changes the given
	 * comparator so it returns the opposite result.
	 * @param comparator The Comparator between files
	 * @param modifiers A linked list of the order modifiers (there are no parameters).
	 * @throws BadOrderException 
	 */
	public FileOrder(Comparator<File> comparator, LinkedList<String> modifiers)
			throws BadOrderException{
		validateModifiers(modifiers);
		this.comparator = comparator;
		if(hasReverseModifier){
			this.comparator = new ReverseOrderDecorator(this.comparator);
		}	
	}

	/**
	 * Constructor for an order that has no parameters. Just 
	 * uses the given comparator
	 * @param comparator The comparator between file.
	 */
	public FileOrder(Comparator<File> comparator){
		this.comparator =comparator;
	}
	
	/**
	 * Sorts the given list of file according to the comparator the
	 * object holds
	 * @param files List of files
	 * @return sorted list of files.
	 */
	public File[] SortFiles(File[] files){
		Arrays.sort(files, comparator);
		return files;
	}
	
	/*
	 * This Method makes sure that if the modifier list isn't empty,
	 * there is only one modifier there, which is REVERSE. If so, the hasReverseBoolean
	 * is turned to true. For any other case an exception is throw.
	 * modifiers list, and that 
	 */
	private void validateModifiers(LinkedList<String> modifiers) 
			                                     throws BadOrderFormatException,
			                                              BadOrderModifierException{
		if(modifiers.size() > VALID_NUMBER_OF_MODIFIERS){
			throw new BadOrderFormatException();
		}
		if(modifiers.size() == VALID_NUMBER_OF_MODIFIERS){
				if(modifiers.getFirst().equals(ORDER_MODIFIER)){
					hasReverseModifier = true;
				}else{
					throw new BadOrderModifierException();
				}
		}
	}
}
