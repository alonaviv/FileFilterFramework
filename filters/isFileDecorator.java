package oop.ex5.filters;

import java.io.File;

/**
 * This decorator extends AbstractFilter 
 * it checks if a given file is actually a file, and if so
 * returns the answer of the filter it holds. If the given file isn't a 
 * (is a directory for instance), it returns false.
 * @author alonav11
 *
 */
class isFileDecorator extends AbstractFilter{
	
	private AbstractFilter filter;
	
	/**
	 * Constructor. Holds the given filter, which it decorates
	 * @param filter Filter to decorate
	 */
	public isFileDecorator(AbstractFilter filter){
		this.filter = filter;
	}

	
	/**
	 * @return false if it doesn't receive a valid file.
	 * If if does, returns the answer of the filter it holds.
	 */
	public boolean accept(File file) {
		if(file.isFile()){
			return filter.accept(file);
		}else{
			return false;
		}
	}

}
