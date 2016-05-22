package oop.ex5.filters;

import java.io.File;
import java.util.LinkedList;

/**
 * This Filter gets a single string as a parameter and filters all files that
 * have the name of that string.
 * @author alonav11
 *
 */
class FileNameFilter extends AbstractFilter{
	
	private static final int VALID_NUMBER_OF_PARAMETERS = 1;

	
	private String parameter;
	
	/**
	 * Constructor. Sets the data members and validates that the parameters are in
	 * correct format.
	 * @param parameters List of parameters as Strings
	 * @throws BadFilterValueException
	 * @throws BadFilterFormatException
	 */
	public FileNameFilter(LinkedList<String> parameters)
											throws BadFilterFormatException{
		this.parameters = parameters;
		validateLine(VALID_NUMBER_OF_PARAMETERS);
		parameter = parameters.getFirst();
	
	}

	
	/**
	 * @return true iff the file's name (excluding the path)
	 * is the same as the String parameter. This is done by splitting the file's name
	 * around "." and then rebuilding it without the last section.
	 */
	public boolean accept(File file) {
		return file.getName().equals(parameter);
	}
}
