package model;

public class Automotive implements java.io.Serializable {

	private String name;
	private int baseprice;
	OptionSet opset[];
	
	private static final long serialVersionUID = 1L;	//generate for Serializable

	// default constructor
	public Automotive() {
	}

	// constructor
	public Automotive(String name, int baseprice, int OptionSetSize) {
		this.name = name;
		this.baseprice = baseprice;
		opset = new OptionSet[OptionSetSize];

		for (int i = 0; i < opset.length; i++) {
			opset[i] = new OptionSet();
		}
	}

	// Getter
	public String getName() {
		return name;
	}

	public int getBasePrice() {
		return baseprice;
	}

	public OptionSet getOptionSet(int index) {
		return opset[index];
	}

	// Find
	public OptionSet findOptionSet(String findname) {
		for (int i = 0; i < opset.length; i++) {
			if (opset[i].getName().equals(findname)) {
				System.out.println("Find the Option Set!");
				return opset[i];
			}
		}
		System.out.println("Cannot find the Option Set");
		return null;
	}

	public void findOption(String optionSetName, String optionName) {
		OptionSet tmp;
		tmp = findOptionSet(optionSetName);
		tmp.findOption(optionName);
	}

	// Setter
	public void setName(String name) {
		this.name = name;
	}

	public void setBasePrice(int baseprice) {
		this.baseprice = baseprice;
	}

	public void setOptionSet(OptionSet tmp, String name, int optionsize) {
		tmp.setName(name);
		// call setOption in OptionSet class to initialize the option object
		tmp.setOption(optionsize);
	}

	public void setOption(OptionSet tmp, String name, int price, int optIndex) {
		tmp.setOption(name, price, optIndex);
	}

	// delete Option according to index
	public void deleteOption(OptionSet tmp, int index) {
		tmp.deleteOption(index);
	}
	
	// delete OptionSet according to index
	public void deleteOptionSet(int index) {
		if (opset[index] == null) {
			System.out.println("You cannot delete an unexisted Option Set!");
		} else {
			opset[index] = null;
			for(int i =index; i< opset.length-1; i++){
				opset[i] = opset[i+1];
			}
			opset[opset.length -1] = null; 
			System.out.println("delete the OptionSet Successfully!");
		}
	}

	// Update OptionSet
	public void updateOptionSet(String findname, String updatename) {
		OptionSet tmp;
		tmp = findOptionSet(findname);
		if (tmp != null) {
			tmp.setName(updatename);
		}
	}

	//Update Option
	public void updateOption(String opsetname, String findoptname, String updateoptname, int updateprice) {
		//findOption(opsetname, findoptname);
		OptionSet tmp;
		tmp = findOptionSet(opsetname);
		tmp.updateOption(findoptname, updateoptname, updateprice);
	}

	//print property in Automotive class, use StringBuffer to concatenate
	public void print() {
		StringBuffer strbuf = new StringBuffer();
		strbuf.append("Property in Automotive. Name: ");
		strbuf.append(name);
		strbuf.append(". Baseprice: ");
		strbuf.append(baseprice);
		strbuf.append(".OptionSet: ");
		System.out.println(strbuf.toString());
		for (int i = 0; i < opset.length; i++) {
			if(opset[i]!=null){
				opset[i].print();
			}
		}
	}
}
