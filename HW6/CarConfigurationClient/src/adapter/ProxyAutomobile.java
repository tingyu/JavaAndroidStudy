package adapter;
import model.*;
import autoutil.*;

import java.util.LinkedHashMap;

/**Abstract class ProxyAutomobile, contains definition of functions in Interface CreateAuto, UpdateAuto, EditThread*/
public abstract class ProxyAutomobile {
	/** A static LinkedHashMap of Automobile */
	private static LinkedHashMap<String, Automobile> autoHashMap = new LinkedHashMap<String, Automobile>();
	
	/**Definition of buildAuto function in Interface CreateAuto*/
	public void buildAuto(String filename, String fileType){
		FileIO fileio = new FileIO();
		if(fileType.equals("Txt")){
			Automobile al = fileio.buildAutoObject(filename);
			autoHashMap.put(al.getModel(), al);
		}
		if(fileType.equals("Properties"))
		{
			Automobile al = fileio.parsePropertyFile(filename);
			autoHashMap.put(al.getModel(), al);
		}
	}
	
	/**Definition of printAuto function in Interface CreateAuto*/
	public void printAuto(String modelname){
		Automobile al = autoHashMap.get(modelname);
		al.print();
	}
	
	/**Definition of updateOptionSetName function in Interface UpdateAuto*/
	public void updateOptionSetName(String modelname, String optionsetname, String newname){
		Automobile al = autoHashMap.get(modelname);
		al.updateOptionSet(optionsetname, newname);
	}
	
	/**Definition of updateOptionPrice function in Interface UpdateAuto*/
	public void updateOptionPrice(String modelname, String optsetname, String optname, float newprice){
		Automobile al = autoHashMap.get(modelname);
		al.updateOption(optsetname, optname, optname, (int)(newprice));
	}
	
	/**Definition of EditOptionSetName function in Interface EditThread*/
	public void EditOptionSetName(String modelname, String optionsetname, String newname){
		Automobile al = autoHashMap.get(modelname);
		al.updateOptionSet(optionsetname, newname);
		printAuto(modelname);
	}
	
	/**Definition of EditOptionPrice function in Interface EditThread*/
	public void EditOptionPrice(String modelname, String optsetname, String optname, float newprice){
		Automobile al = autoHashMap.get(modelname);
		al.updateOption(optsetname, optname, optname, (int)(newprice));
		printAuto(modelname);
	}

}
