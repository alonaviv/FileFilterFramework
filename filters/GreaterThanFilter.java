package oop.ex5.filters;

import java.io.File;
import java.util.LinkedList;

/**
 * This Filter gets a single double as a parameter and filters all file larger than
 * than k-bytes of the given double.
 * @author alonav11
 *
 */
class GreaterThanFilter extends AbstractFilter{
	
	private static final int VALID_NUMBER_OF_PARAMETERS = 1;
	
	private static final int K_BYTE_MULTIPLIER = 1024;
	private static final double MINIMUM_PARAMETER = 0;
	
	private double firstParameter;
	
	/**
	 * Constructor. Sets the data members and validates that the parameters are in
	 * correct format and contain a single non negative double.
	 * @param parameters List of parameters as Strings
	 * @throws BadFilterValueException
	 * @throws BadFilterFormatException
	 */
	public GreaterThanFilter(LinkedList<String> parameters)
											throws BadFilterValueException, BadFilterFormatException{
		this.parameters = parameters;
		validateLine(VALID_NUMBER_OF_PARAMETERS);
		try{
			firstParameter = Double.parseDouble(parameters.getFirst());
		}
		catch(NumberFormatException e){ //In case the parameter isn't a double.
			throw new BadFilterValueException();
		}
		validateParameter();
	
	}

	
	/**
	 * @return true iff the file is larger than the
	 * parameter * 1024 (k-byte)
	 */
	public boolean accept(File file) {
		return file.length() > (long)(firstParameter*K_BYTE_MULTIPLIER);
	}

	
	/*
	 * The parameter is invalid if it is negative
	 */
	private void validateParameter() throws BadFilterValueException{
		if(firstParameter < MINIMUM_PARAMETER){
			throw new BadFilterValueException();
		}
	}
}
