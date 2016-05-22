package oop.ex5.filters;

import java.io.File;

/**
 * This is a decorator that extends AbstractFilter and holds an instance of
 * a certain filter. When it receives a request it runs it throw the filter and
 * returns the opposite boolean value.
 * @author alonav11
 *
 */
class NotFilterDecorator extends AbstractFilter{
	private AbstractFilter filter;
	
	/**
	 * Constructor.
	 * @param filter A FileFilter object to negate
	 */
	public NotFilterDecorator(AbstractFilter filter){
		this.filter = filter;
	}

	/**
	 * @return The opposite of the filter result the
	 * decorator holds. 
	 */
	public boolean accept(File file) {
		return !filter.accept(file);
	}
	

}
