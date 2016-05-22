package oop.ex5.filters;

import java.io.File;
import java.util.LinkedList;

/**
 * This Filter returns true on all files. Doesn't get any parameters. If a 
 * parameter is given, an exception is thrown.
 * @author alonav11
 *
 */
class AllFilter extends AbstractFilter{
	
	private static final int VALID_NUMBER_OF_PARAMETERS = 0;

	
	/**
	 * Constructor. Makes sure there are no parameters.
	 * @param parameters List of parameters as Strings
	 * @throws BadFilterValueException
	 * @throws BadFilterFormatException
	 */
	public AllFilter(LinkedList<String> parameters)
							throws BadFilterFormatException,BadFilterValueException{
		this.parameters = parameters;
		validateLine(VALID_NUMBER_OF_PARAMETERS);
		
	}
	
	/**
	 * Empty constructor. Creates the filter without receiving any parameters.
	 * To be used when the All filter is called as the default filter in the event of
	 * a filter exception.
	 */
	public AllFilter(){
	}

	
	/**
	 * @return true, regardless of the file.
	 */
	public boolean accept(File file) {
		return true;
	}
	
	
}
