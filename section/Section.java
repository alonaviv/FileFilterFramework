package oop.ex5.section;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

import oop.ex5.orders.FileOrder;

/**
 * This class holds the data for one instance - one filter
 * and one order. The class receives a directory of files and returns an 
 * array of files that passed the given filter and sorted by the given order.
 * @author alonav11
 */
public class Section{
	
	private FileFilter filter;
	private FileOrder order;
	private ArrayList<String> warningMessages;
	
	/**
	 * Constructor
	 * @param filter A FileFilter object to filter with
	 * @param order A FileOrder object to sort with
	 */
	public Section(FileFilter filter, FileOrder order, ArrayList<String> warningMessages){
		this.filter = filter;
		this.order = order;	
		this.warningMessages = warningMessages;
	}
	
	

	/**
	 * Filters and sorts an array of files according to his filter and
	 * order objects. 
	 * @param files An array of files
	 * @return A filtered and sorted array of files
	 */
	public File[] filterAndSort(File directory){
		return order.SortFiles(directory.listFiles(filter));
	}
	
	/**
	 * @return array list of warning messages.
	 */
	public ArrayList<String> getWarningMessages(){
		return warningMessages;
	}

}
