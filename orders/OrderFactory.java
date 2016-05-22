package oop.ex5.orders;

import java.io.File;
import java.util.Comparator;
import java.util.LinkedList;


/**
 * This class receives a the name of an order command and a modifier. 
 * It returns an Order object that compares files according to the 
 * given command and modifier. If no name of order is given, the
 * default order with an abs comparator is given.
 * @author alonav11
 *
 */
public class OrderFactory {
	private static final String ABS = "abs"; 
	private static final String TYPE = "type";
	private static final String SIZE = "size";
	
	private static final Comparator<File> ABS_COMPARATOR = new AbsComparator();
	private static final Comparator<File> TYPE_COMPARATOR = new TypeComparator();
	private static final Comparator<File> SIZE_COMPARATOR = new SizeComparator();
	private static final Comparator<File> DEFAULT_COMPARATOR = ABS_COMPARATOR;
	
	
	/**
	 * Creates a default order (abs), when no order name is given, or
	 * an order exception is thrown.
	 * @return Default order (abs)
	 */
	public static FileOrder createOrder(){
		return new FileOrder(DEFAULT_COMPARATOR);
	}
	
	/**
	 * Creates an order object that holds the given comparator.
	 * @param orderName Name of order to create
	 * @param modifiers Modifier list (should only hold REVERSED if anything)
	 * @return Requested order object
	 * @throws BadOrderException
	 */
	public static FileOrder createOrder(String orderName, LinkedList<String> modifiers) 
			throws BadOrderException{
				
		switch(orderName){
		
		case ABS:  return new FileOrder(ABS_COMPARATOR, modifiers);
				         		
		case TYPE: return new FileOrder(TYPE_COMPARATOR, modifiers);
		
		case SIZE: return new FileOrder(SIZE_COMPARATOR, modifiers);
		
		// If there is no known order name, it is an error in the name
		default: throw new BadOrderNameException();
		}
	}
}
