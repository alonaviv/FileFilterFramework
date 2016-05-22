package oop.ex5.filters;

import java.io.File;
import java.util.LinkedList;

/**
 * This Filter gets a single string as a parameter and filters all files that
 * contain that string in their name.
 * @author alonav11
 *
 */
class ContainsFilter extends AbstractFilter{
	
	private static final int VALID_NUMBER_OF_PARAMETERS = 1;

	
	private String parameter;
	
	/**
	 * Constructor. Sets the data members and validates that the parameters are in
	 * correct format.
	 * @param parameters List of parameters as Strings
	 * @throws BadFilterValueException
	 * @throws BadFilterFormatException
	 */
	public ContainsFilter(LinkedList<String> parameters)
											throws BadFilterFormatException{
		this.parameters = parameters;
		validateLine(VALID_NUMBER_OF_PARAMETERS);
		parameter = parameters.getFirst();
	
	}

	
	/**
	 * @return true iff the parameter appears
	 * within the file's name.
	 */
	public boolean accept(File file) {
		return file.getName().contains(parameter);
	}
}
