package chem_lab;
import java.io.FileNotFoundException;
import java.util.*;
public class ElementGenerator {
	private static ArrayList<Element> all;
	public static void main(String[] args) throws FileNotFoundException {
	//public ElementGenerator() throws FileNotFoundException {
		all=new ArrayList<Element>();
		for(int i=1;i<=118;i++) {
			all.add(new Element(i));
		}
		System.out.println(all.size());
		System.out.println(all);
		
	}
	 
}