package oop.ex5.filters;

import java.io.File;
import java.util.LinkedList;

/**
 * This Filter gets a two doubles as parameters and filters all files in between (inclusive)
 * those two doubles (in kbytes).
 *  * @author alonav11
 *
 */
class BetweenFilter extends AbstractFilter{
	
	private static final int VALID_NUMBER_OF_PARAMETERS = 2;
	
	private static final int K_BYTE_MULTIPLIER = 1024;
	private static final double MINIMUM_PARAMETER = 0;
	
	private double firstParameter;
	private double secondParameter;
	
	/**
	 * Constructor. Sets the data members and validates that the parameters are in
	 * correct format and contain a single non negative double.
	 * @param parameters List of parameters as Strings
\	 * @throws BadFilterValueException
	 * @throws BadFilterFormatException
	 */
	public BetweenFilter(LinkedList<String> parameters)
											throws BadFilterValueException, BadFilterFormatException{
		this.parameters = parameters;
		validateLine(VALID_NUMBER_OF_PARAMETERS);
		try{
			firstParameter = Double.parseDouble(parameters.getFirst());
			secondParameter = Double.parseDouble(parameters.getLast());
		}
		catch(NumberFormatException e){ //In case one of the parameters isn't a double.
			throw new BadFilterValueException();
		}
		validateParameters();
	
	}

	
	/**
	 * @return true iff the file is larger than the
	 * parameter * 1024 (k-byte)
	 */
	public boolean accept(File file) {
		return file.length()>= (long)(firstParameter*K_BYTE_MULTIPLIER)
				&& file.length()<= (long)(secondParameter*K_BYTE_MULTIPLIER);
	}

	
	/*
	 * The parameter is invalid if it is negative
	 */
	private void validateParameters() throws BadFilterValueException{
		if(firstParameter < MINIMUM_PARAMETER || 
				secondParameter < MINIMUM_PARAMETER || firstParameter > secondParameter){
			throw new BadFilterValueException();
		}
	}
}
