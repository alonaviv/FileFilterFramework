package oop.ex5.filters;

import java.io.FileFilter;
import java.util.LinkedList;

/**
 * A factory for creating filter instances. 
 * @author alonav11
 *
 */
public class FilterFactory {
	
	private static final String GREATER_THAN = "greater_than";
	private static final String BETWEEN = "between";
	private static final String SMALLER_THAN = "smaller_than";
	private static final String FILE = "file";
	private static final String CONTAINS = "contains";
	private static final String PREFIX = "prefix";
	private static final String SUFFIX = "suffix";
	private static final String WRITABLE = "writable";
	private static final String EXECUTABLE = "executable";
	private static final String HIDDEN = "hidden";
	private static final String ALL = "all";
	
	
	
	/**
	 * Receives a name of a FileFilter and parameters and returns an instance of
	 * the requested filter. If the filter has a NOT modifier (this is determined by each filter)
	 * then a not decorator is added to the filter.
	 * Since all filters should filter files and not directories, a IsFileDecorator
	 * is attached to every returned filter.
	 * @param filterName The name of the filter
	 * @param filterParameters A linked list of the filter parameters
	 * @return Requested FileFilter object.
	 * @throws BadFilterException
	 */
	public static FileFilter createFilter(String filterName, LinkedList<String> filterParameters)
			                                               throws BadFilterException{
		AbstractFilter filter;
		switch(filterName){
		
		case GREATER_THAN: filter = new GreaterThanFilter(filterParameters);
						   if(filter.hasNotModifier()){
							   filter = new NotFilterDecorator(filter);
						   }
						   return filter;
						   
		case BETWEEN: filter = new BetweenFilter(filterParameters);
		   if(filter.hasNotModifier()){
			   filter = new NotFilterDecorator(filter);
		   }
		   break;
		
		case SMALLER_THAN: filter = new SmallerThanFilter(filterParameters);
		   if(filter.hasNotModifier()){
			   filter = new NotFilterDecorator(filter);
		   }
		   break;
		   
		   
		case FILE: filter = new FileNameFilter(filterParameters);
		   if(filter.hasNotModifier()){
			   filter = new NotFilterDecorator(filter);
		   }
		   break;
		   
		case CONTAINS: filter = new ContainsFilter(filterParameters);
		   if(filter.hasNotModifier()){
			   filter = new NotFilterDecorator(filter);
		   }
		   break;
		   
		   
		case PREFIX	: filter = new PrefixFilter(filterParameters);
		   if(filter.hasNotModifier()){
			   filter = new NotFilterDecorator(filter);
		   }
		   break;
		   
		   
		case SUFFIX: filter = new SuffixFilter(filterParameters);
		   if(filter.hasNotModifier()){
			   filter = new NotFilterDecorator(filter);
		   }
		   break;
		   
		case WRITABLE: filter = new WritableFilter(filterParameters);
		   if(filter.hasNotModifier()){
			   filter = new NotFilterDecorator(filter);
		   }
		   break;
		   
		case EXECUTABLE: filter = new ExecutableFilter(filterParameters);
		   if(filter.hasNotModifier()){
			   filter = new NotFilterDecorator(filter);
		   }
		   break;
		   
		case HIDDEN: filter = new HiddenFilter(filterParameters);
		   if(filter.hasNotModifier()){
			   filter = new NotFilterDecorator(filter);
		   }
		   break;
		   
		   
		case ALL: filter = new AllFilter(filterParameters);
		   if(filter.hasNotModifier()){
			   filter = new NotFilterDecorator(filter);
		   }
		   break;
		   
		default: //If there is no known name, the name is wrong
			throw new BadFilterNameException();	   
		}
		
		return new isFileDecorator(filter); //Adding a decorator to the filter which
											//makes sure that the given file is an actual file.
		
	}
	
	/**
	 * Default creator.
	 * @return the default all filter. 
	 * Adds the isFile decorator.
	 */
	public static FileFilter createFilter(){
		return new isFileDecorator(new AllFilter());
	}
	
}
