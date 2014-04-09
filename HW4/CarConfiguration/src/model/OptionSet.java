package model;//change to non capital

import java.util.ArrayList;

class OptionSet implements java.io.Serializable {

	private String name = "";
	private ArrayList<Option> opt;
	private Option choice = null;

	private static final long serialVersionUID = 1L; // generate for
														// Serializable

	// default constructor
	protected OptionSet() {
	}

	protected OptionSet(String n) {
		opt = new ArrayList<Option>();
		name = n;
	}
	
	//add new Option to the end of Option ArrayList
	protected void addOption(String optName, int optPrice){
		Option tmp = new Option(optName, optPrice);
		opt.add(tmp);
	}

	//Getter
	protected String getName() {
		return name;
	}

	protected Option getOption(int index) {
		return opt.get(index);
	}

	protected Option getOptionChoice(){
		return choice;
	}
	
	//Setter
	protected void setName(String name) {
		this.name = name;
	}

	//set OptionName by Option index
	protected void setOptionName(int index, String name) {
		opt.get(index).setName(name);
	}
	
	//set OptionPrice by Option index
	protected void setOptionPrice(int index, int price) {
		opt.get(index).setPrice(price);
	}
	
	protected void setOptionChoice(String optionName){
		Option tmp = findOption(optionName);
		choice = tmp;
	}
	
	//find
	protected Option findOption(String findname) {
		for (Option tmp: opt) {
			if (tmp.getName().equals(findname)) {
				//System.out.println("Find the Option!");
				return tmp;
			}
		}
		System.out.println("Cannot find the Option");
		return null;
	}

	//update, by finding one option and set the variable
	protected void updateOption(String findname, String updatename,
			int updateprice) {
		Option tmp;
		tmp = findOption(findname);
		if (tmp != null) {
			tmp.setName(updatename);
			tmp.setPrice(updateprice);
		}
	}

	//delete Option by giving an Option, this one and the following one which one is better
	protected void deleteOption(Option tmp) {
		if (tmp == null) {
			System.out.println("You cannot delete an unexisted object");
		} else {
			tmp = null;
			System.out.println("delete the Option Successfully");
		}
	}
	
	//delete option by index
	protected void deleteOption(int index) {
		if (opt.get(index) == null) {
			System.out.println("You cannot delete an unexisted Option!");
		} else {
			opt.remove(index);
			//opt[index] = null;
			System.out.println("delete the Option Successfully!");
		}
	}

	//print property in OptionSet class, use StringBuffer to concatenate
	protected void print() {
		StringBuffer strbuf = new StringBuffer();
		strbuf.append("Property in OptionSet. Name: ");
		strbuf.append(name);
		strbuf.append(". Option:");
		System.out.println(strbuf.toString());
		for (Option tmp: opt) {
			if(tmp!=null){
				tmp.print();
			}
		}
		System.out.println();
	}

	protected class Option implements java.io.Serializable {

		private String name;
		private int price;
		
		private static final long serialVersionUID = 1L; // generate for Serializable

		//default constructor
		protected Option() {
		}

		//constructor
		protected Option(String name, int price) {
			this.name = name;
			this.price = price;
		}

		//Getter
		protected String getName() {
			return name;
		}

		protected int getPrice() {
			return price;
		}

		//Setter
		protected void setName(String name) {
			this.name = name;
		}

		protected void setPrice(int price) {
			this.price = price;
		}

		//print property in option class, use StringBuffer to concatenate
		protected void print() {
			StringBuffer strbuf = new StringBuffer();
			strbuf.append("name: ");
			strbuf.append(name);
			strbuf.append(". price: ");
			strbuf.append(price);
			System.out.println(strbuf.toString());
		}

	}

}
