package oop.ex5.orders;

import java.io.File;
import java.util.Comparator;

/**
 * Compares two files according to their size.
 * 
 * @author alonav11
 *
 */
class SizeComparator implements Comparator<File>{
	private static final int LARGER_THAN = 1;
	private static final int SMALLER_THAN = -1;
	/**
	 * Compare files according to size. If both files are the
	 * same size, compare them according to their absolute value.
	 * @param firstFile First File
	 * @param secondFile Second File
	 * @return 0 if they are the same, 1 if the first file is larger, -1 if
	 * the first file is smaller.
	 */
	public int compare(File firstFile, File secondFile){
		long firstFileSize = firstFile.length();
		long secondFileSize = secondFile.length();
		
		if (firstFileSize == secondFileSize){
			Comparator<File> abs = new AbsComparator();
			return abs.compare(firstFile, secondFile);
		}else if(firstFileSize > secondFileSize){
			return LARGER_THAN;
		}else{
			return SMALLER_THAN;
		}
	}

}
