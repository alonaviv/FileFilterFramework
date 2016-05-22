package oop.ex5.filters;

import java.io.FileFilter;
import java.util.LinkedList;


/**
 * This class is an abstract filter object. Its purpose is to be constructed with
 * valid parameters and then to determine if a certain file passes the filter. Specific
 * filters inherit this object. 
 * @author alonav11
 *
 */
public abstract class AbstractFilter implements FileFilter{

	private final static int NUM_OF_MODIFIERS = 1;
	private final static String NOT_MODIFIER = "NOT";
	
	protected LinkedList<String> parameters;
	protected boolean hasNotModifier = false;
	
	
	/**
	 * @return true iff the given parameter line has a valid NOT parameter.
	 */
	boolean hasNotModifier(){

		return hasNotModifier;
	}
	
	/**
	 * This method receives the number of allowed parameters in the specific filter and
	 * checks if the given parameters satisfy this rule. If it doesn't, it checks if the
	 * end of he parameter has a valid NOT modifier. If so, it removes the NOT modifier
	 * from the modifier list and marks the hasNotModifier as true. In any other case
	 * there is a format error. To be called by the constructor of the specific filter.
	 * @param allowedNumOfParameters The number of parameters the filter implementation
	 * expects.
	 * @throws BadFilterFormatException If the number of parameters is invalid (taking
	 * into consideration the NOT modifier).
	 */
	protected void validateLine(int validNumOfParameters) throws BadFilterFormatException{
		if(parameters.size() < validNumOfParameters || 
				parameters.size() > validNumOfParameters + NUM_OF_MODIFIERS){
			throw new BadFilterFormatException();
		}
		if(parameters.size() == validNumOfParameters + NUM_OF_MODIFIERS){
			if(parameters.removeLast().equals(NOT_MODIFIER)){
				hasNotModifier = true;
			}else{
				throw new BadFilterFormatException();
			}	
		}
		
	}
	

}
