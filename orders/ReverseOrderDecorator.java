package oop.ex5.orders;

import java.io.File;
import java.util.Comparator;

/**
 * This decorator implements Comparator and holds a specific file order
 * Comparator. 
 * It takes receives two files to compare and returns the opposite answer
 * as the given comparator.
 * @author alonav11
 *
 */
class ReverseOrderDecorator implements Comparator<File>{

	private static final int NEGATING_INT = -1;
	private Comparator<File> comparator;

	/**
	 * Constructor.
	 * @param comparator The comparator to reverse.
	 */
	public ReverseOrderDecorator(Comparator<File> comparator){
		this.comparator = comparator;
	}
	
	
	/**
	 * Negates the answer of the comparator the object decorates
	 * @param firstFile First File
	 * @param secondFile Second File
	 * @return The answer of the decorated comparator to the
	 * given files, multiplied by -1.
	 */
	public int compare(File firstFile, File secondFile) {
		return comparator.compare(firstFile, secondFile) * NEGATING_INT;
	}
	
}
