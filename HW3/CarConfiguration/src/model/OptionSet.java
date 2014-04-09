package model;//change to non capital

class OptionSet implements java.io.Serializable {

	private String name = "";
	private Option opt[];

	private static final long serialVersionUID = 1L; // generate for
														// Serializable

	// default constructor
	protected OptionSet() {
	}

	protected OptionSet(String n, int size) {
		opt = new Option[size];
		name = n;
	}

	//Getter
	protected String getName() {
		return name;
	}

	protected Option getOption(int index) {
		return opt[index];
	}

	//Setter
	protected void setName(String name) {
		this.name = name;
	}

	protected void setOption(int size) {
		this.opt = new Option[size];
	}

	protected void setOption(String name, int price, int optNum) {
		opt[optNum] = new Option(name, price);
	}

	//find
	protected Option findOption(String findname) {
		for (int i = 0; i < opt.length; i++) {
			if (findname.equals(opt[i].name)) {
				System.out.println("Find the Option!");
				return opt[i];
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
/*
	protected void deleteOption(Option tmp) {
		if (tmp == null) {
			System.out.println("You cannot delete an unexisted object");
		} else {
			tmp = null;
			System.out.println("delete the Option Successfully");
		}
	}*/
	
	//delete option by index
	protected void deleteOption(int index) {
		if (opt[index] == null) {
			System.out.println("You cannot delete an unexisted Option!");
		} else {
			//opt[index] = null;
			for(int i =index; i< opt.length-1; i++){
				opt[i] = opt[i+1];
			}
			opt[opt.length -1] = null; 
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
		for (int i = 0; i < opt.length; i++) {
			if(opt[i]!=null){
				opt[i].print();
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
