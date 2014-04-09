package model;
import java.util.ArrayList;

public class Automobile implements java.io.Serializable {

	private String name;
	private int basePrice;
	private String make = "";
	private String model = "";
	ArrayList<OptionSet> opset;
	//private LinkedHashMap<String, Object> choiceset = new LinkedHashMap<String, Object>();
	
	private static final long serialVersionUID = 1L;	//generate for Serializable

	// default constructor
	public Automobile() {
		opset = new ArrayList<OptionSet>();
	}

	// constructor
	public Automobile(String name, int basePrice) {
		synchronized(this){
		this.name = name;
		this.basePrice = basePrice;
		//opset = new OptionSet[OptionSetSize];
		opset = new ArrayList<OptionSet>();
		}
	}
	
	//Adder
	//add new OptionSet to the end of OptionSet ArrayList
	public void addOptionSet(String newName){
		synchronized(this){
		OptionSet tmp = new OptionSet(newName);
		opset.add(tmp);
		}
	}
	
	//add new Option to the end of Option ArrayList
	public void addOption(OptionSet tmp, String optName, int optPrice){
		synchronized(this){
		//OptionSet tmp = getOptionSet(opsetIndex);
		tmp.addOption(optName, optPrice);
		}
	}

	// Getter
	public String getName() {
		return name;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public OptionSet getOptionSet(int index) {
		return opset.get(index);
	}
	
	public String getMake(){
		return make;
	}
	
	public String getModel(){
		return model;
	}
	
	public String getOptionChoice(String setName){
		synchronized(this){
		OptionSet tmp = findOptionSet(setName);
		return tmp.getOptionChoice().getName();
		}
	}
	
	public int getOptionChoicePrice(String setName){
		synchronized(this){
		OptionSet tmp = findOptionSet(setName);
		return tmp.getOptionChoice().getPrice();
		}
	}
	
	public int getTotalPrice(){
		synchronized(this){
		int totalPrice = basePrice;
		for(OptionSet tmp: opset){
			totalPrice += tmp.getOptionChoice().getPrice();
		}
		return totalPrice;
		}
	}

	// Setter
	public void setName(String name) {
		this.name = name;
	}

	public void setBasePrice(int baseprice) {
		this.basePrice = baseprice;
	}
	
	public void setMake(String make){
		this.make = make;
	}
	
	public void setModel(String model){
		this.model = model;
	}
	
	public void setOptionChoice(String setName, String optionName){
		synchronized(this){
		OptionSet tmp = findOptionSet(setName);
		tmp.setOptionChoice(optionName);
		}
	}
	
	//Set OptionSet by giving an index
	public void setOptionSet(int index, String name){
		synchronized(this){
		OptionSet tmp = opset.get(index);
		tmp.setName(name);
		}
	}
	
	//Set Option by giving an 
	public void setOption(int opsetIndex, int optIndex,String optName, int optPrice){
		synchronized(this){
		OptionSet tmp = opset.get(opsetIndex);
		tmp.setOptionName(optIndex, optName);
		tmp.setOptionPrice(optIndex, optPrice);
		}
	}
/*
	//this two function to be modified
	public void setOptionSet(OptionSet tmp, String name, int optionsize) {
		tmp.setName(name);
		// call setOption in OptionSet class to initialize the option object
		tmp.setOption(optionsize);
	}

	public void setOption(OptionSet tmp, String name, int price, int optIndex) {
		tmp.setOption(name, price, optIndex);
	}*/

	// Find
	public OptionSet findOptionSet(String findname) {
		synchronized(this){
		for (OptionSet tmp: opset) {
			if (tmp.getName().equals(findname)) {
				//System.out.println("Find the Option Set!");
				return tmp;
			}
		}
		System.out.println("Cannot find the Option Set");
		return null;
		}
	}

	public void findOption(String optionSetName, String optionName) {
		synchronized(this){
		OptionSet tmp;
		tmp = findOptionSet(optionSetName);
		tmp.findOption(optionName);
		}
	}
	
	// delete
	public void deleteOption(OptionSet tmp, int index) {
		synchronized(this){
		tmp.deleteOption(index);
		}
	}

	public void deleteOptionSet(int index) {
		synchronized(this){
		if (opset.get(index) == null) {
			System.out.println("You cannot delete an unexisted Option Set!");
		} else {
			opset.remove(index);
			System.out.println("delete the OptionSet Successfully!");
		}
		}
		}


	// Update OptionSetName by name
	public void updateOptionSet(String findname, String updatename) {
		synchronized(this){
		OptionSet tmp;
		tmp = findOptionSet(findname);
		if (tmp != null) {
			tmp.setName(updatename);
		}
		}
	}

	// Update Option name and price by OptionSet name and Option name
	public void updateOption(String opsetname, String findoptname, String updateoptname, int updateprice) {
		synchronized(this){
		//findOption(opsetname, findoptname);
		OptionSet tmp;
		tmp = findOptionSet(opsetname);
		tmp.updateOption(findoptname, updateoptname, updateprice);
		}
	}

	public void print() {
		synchronized(this){
		StringBuffer strbuf = new StringBuffer();
		strbuf.append("Property in Automobile. Model: ");
		strbuf.append(model);
		strbuf.append(". Make: ");
		strbuf.append(make);
		strbuf.append(". Name: ");
		strbuf.append(name);
		strbuf.append(". Baseprice: ");
		strbuf.append(basePrice);
		strbuf.append(".OptionSet: ");
		System.out.println(strbuf.toString());
		for (OptionSet tmp: opset) {
			if(tmp != null){
				tmp.print();
			}
		}
		}
	}
}
