package oop.ex5.orders;

import java.io.File;
import java.util.Comparator;

/**
 * Compares two file lexicographically according to their file type.
 * File type is indicated by the text after the last period.
 * @author alonav11
 *
 */
class TypeComparator implements Comparator<File>{
	private static final String TYPE_SEPERATOR = "\\.";
	private static final int EQUAL_COMPARISON = 0;
	private static final int TYPE_INDEX_FROM_END = 0;
	
	/**
	 * Compare files lexicographically according to type. If both files are the
	 * same type, compare them according to their absolute value.
	 * @param firstFile First File
	 * @param secondFile Second File
	 * @return 0 if they are the same, 1 if the first file is larger, -1 if
	 * the first file is smaller.
	 */
	public int compare(File firstFile, File secondFile){
		String[] firstNameParsed = firstFile.getName().split(TYPE_SEPERATOR);
		String[] secondNameParsed = secondFile.getName().split(TYPE_SEPERATOR);
		String firstFileType = firstNameParsed[firstNameParsed.length - 1 -TYPE_INDEX_FROM_END];
		String secondFileType = secondNameParsed[secondNameParsed.length - 1 - TYPE_INDEX_FROM_END];
		
		int comparison = firstFileType.compareTo(secondFileType);
		if (comparison == EQUAL_COMPARISON){
			Comparator<File> abs = new AbsComparator();
			return abs.compare(firstFile, secondFile);
		}else{
			return comparison;
		}
	}

}
