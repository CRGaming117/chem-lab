package chem_lab;
import java.util.*;
import java.io.*;
public class Element {
	private ArrayList<String> allSymbols,allNames;
	private ArrayList<Integer> allMasses;
	private ArrayList<Boolean> allIsotopes;
	private String symbol,name;
	private int p,n,e,m;
	private boolean isotope;
	private void allElements() throws FileNotFoundException {
		allSymbols=new ArrayList<String>();
		allNames=new ArrayList<String>();
		allMasses=new ArrayList<Integer>();
		allIsotopes=new ArrayList<Boolean>();
		Scanner fS=new Scanner(new File("portfolio/chem_lab/ElementNames"));
		while(fS.hasNextLine()) {
			allSymbols.add(fS.next());
			allNames.add(fS.next());
			allMasses.add(fS.nextInt());
			allIsotopes.add(fS.nextBoolean());
		}
		fS.close();
		
	}
	public Element(int num) throws FileNotFoundException {
		allElements();
		if(num<1) num=1;
		else if(num>118) num=118;
		this.p=num;
		this.e=num;
		num--;
		this.symbol=allSymbols.get(num);
		this.name=allNames.get(num);
		this.m=allMasses.get(num);
		this.isotope=allIsotopes.get(num);
		this.n=m-p;
	}
	public Element(int p, int n, int e) {
		this.p=p;
		this.e=e;
		this.n=n;
		this.isotope=true;
		this.symbol="";
		this.name="";
		this.m=p+n;
		
	}
	//COMPARE ELEMENTS
	public boolean equals(Element e) {
		boolean same=false;
		if(this.p==e.getProtons()&&this.e==e.getElectrons()) {
			if(isotope) {
				if(this.n>=e.getNeutrons()-2&&this.n<=e.getNeutrons()+2) {
					same=true;
				}
			}
		}	
		return same;
	}
	
	//GETTERS
	public String getSymbol() {
		return symbol;
	}
	public String getName() {
		return name;
	}
	public int getProtons() {
		return p;
	}
	public int getNeutrons() {
		return n;
	}
	public int getElectrons() {
		return e;
	}
	public int getMass() {
		return m;
	}
	public boolean hasIsotopes() {
		return isotope;
	}
	//SETTERS
	public void setProtons(int p) {
		this.p=p;
	}
	public void setNeutrons(int n) {
		this.n=n;
	}
	public void setElectrons(int e) {
		this.e=e;
	}
	//TO STRING
	public String toString() {
		return symbol+" "+name+" "+p+" "+n+" "+e+" "+m;
	}
}