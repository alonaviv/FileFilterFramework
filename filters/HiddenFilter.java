package oop.ex5.filters;

import java.io.File;
import java.util.LinkedList;

/**
 * This Filter gets a single YES\NO string as a parameter and filters all files that
 * are/aren't hidden, according to that string.
 * @author alonav11
 *
 */
class HiddenFilter extends AbstractFilter{
	
	private static final int VALID_NUMBER_OF_PARAMETERS = 1;
	private static final String YES = "YES";
	private static final String NO = "NO";

	
	private String parameter;
	
	/**
	 * Constructor. Sets the data members and validates that the parameters are in
	 * correct format. Makes sure the String is either Yes or No.
	 * @param parameters List of parameters as Strings
	 * @throws BadFilterValueException
	 * @throws BadFilterFormatException
	 */
	public HiddenFilter(LinkedList<String> parameters)
							throws BadFilterFormatException,BadFilterValueException{
		this.parameters = parameters;
		validateLine(VALID_NUMBER_OF_PARAMETERS);
		parameter = parameters.getFirst();
		validateParameter();
	}

	
	/**
	 * @return true iff the file is hidden\
	 * not hidden, according to the parameter.
	 */
	public boolean accept(File file) {
		boolean hidden = file.isHidden();
		if(parameter.equals(YES)){
			return hidden;
		}else{
			return !hidden;
		}
	}
	
	/**
	 * Throws an exception if the parameter isn't NO or YES.
	 */
	private void validateParameter() throws BadFilterValueException{
		if(!parameter.equals(YES) && !parameter.equals(NO)){
			throw new BadFilterValueException();
		}
		
	}
}
