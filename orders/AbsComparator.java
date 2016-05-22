package oop.ex5.orders;

import java.io.File;
import java.util.Comparator;

/**
 * Compares two file lexicographically according to their absolute path.
 * @author alonav11
 *
 */
class AbsComparator implements Comparator<File>{

	/**
	 * Compare files according to absolute path.
	 * @param firstFile First File
	 * @param secondFile Second File
	 * @return 0 if they are the same, 1 if the first file is larger, -1 if
	 * the first file is smaller.
	 */
	public int compare(File firstFile, File secondFile){
		return firstFile.getAbsolutePath().compareTo(secondFile.getAbsolutePath());
	}

}
