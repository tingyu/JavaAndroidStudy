package adapter;
import model.*;

import autoutil.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import database.*;

/**Abstract class ProxyAutomobile, contains definition of functions in Interface CreateAuto, UpdateAuto, EditThread*/
public abstract class ProxyAutomobile {
	/** A static LinkedHashMap of Automobile */
	private static LinkedHashMap<String, Automobile> autoHashMap = new LinkedHashMap<String, Automobile>();
	private StringBuffer allModel = new StringBuffer();
	
	AutomobileDB autoDB = new AutomobileDB();
	OptionSetDB opsetDB = new OptionSetDB();
	OptionDB optDB = new OptionDB();
	
	private static int opsetID=1;
	private static int optID=1;

	
	/**Definition of buildAuto function in Interface CreateAuto*/
	public void buildAuto(String filename, String fileType){
		FileIO fileio = new FileIO();
		if(fileType.equals("Txt")){
			Automobile al = fileio.buildAutoObject(filename);
			autoHashMap.put(al.getModel(), al);
			
			/**populate the database 3 tables using Automobile*/
			createDataBase(al);

		}
		if(fileType.equals("Properties"))
		{
			/*
			Automobile al = fileio.parsePropertyFile(filename);
			autoHashMap.put(al.getModel(), al);
			*/
			//parseProperties(filename);
		}
	}

	/*
	public void parseProperties(String filename){
		FileIO fileio = new FileIO();
		
		Automobile al = fileio.parsePropertyFile(filename);
		autoHashMap.put(al.getModel(), al);
	}*/
	
	public void parseProperties(Properties pros){
		FileIO fileio = new FileIO();
		
		Automobile al = fileio.parsePropertyFile(pros);
		autoHashMap.put(al.getModel(), al);
		
		/**populate the database 3 tables using Automobile*/
		createDataBase(al);
		
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
	
	public String GetAllModels(){
		if(autoHashMap!=null){
			Set<Entry<String, Automobile>> entrySet = autoHashMap.entrySet();
//			for(int i = 0; i < autoHashMap.size(); i++){
//				allModel.append(autoHashMap.get(i).getModel());
//				allModel.append(",");
//			}
			for (Entry<String, Automobile> entry : entrySet) {
				allModel.append(entry.getValue().getModel());
				allModel.append(",");
			}
			return allModel.toString();
		}
		else{
			return "null LHM";
		}
		//System.out.println(allModel.toString());
	}

	public void SendAutoObject(String modelName, Socket socket){
		Automobile al = autoHashMap.get(modelName);
		
		try {
			ObjectOutputStream outObj = new ObjectOutputStream(socket.getOutputStream());
			outObj.writeObject(al);
			outObj.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * populate the database 3 tables using Automobile
	 * @param Automobile
	 */
	public void createDataBase(Automobile al){
		autoDB.setParameters(al.getModel(), al.getMake(), al.getBasePrice(), al.getName());
		autoDB.getSQLStatement("CREATEAUTO");
		autoDB.createDB("CREATEAUTO");
		
		for(int i = 0; i< al.getOpsetNameList().size(); i++){
			System.out.println("getName = " + al.getOpsetNameList().get(i));
			opsetDB.setParameters(opsetID, al.getModel(), al.getOpsetNameList().get(i));
			opsetDB.getSQLStatement("CREATEOPSET");
			opsetDB.createDB("CREATEOPSET");
			
			for(int j =0; j< al.getOptNameList(i).size(); j++){
				optDB.setParameters(optID++, opsetID, al.getOptNameList(i).get(j), al.getOptPriceList(i).get(j));
				optDB.getSQLStatement("CREATEOPT");
				optDB.createDB("CREATEOPT");
			}
			opsetID++;
		}
	}
	
	public void addOptionSet(String modelName, String name){
		Automobile al = autoHashMap.get(modelName);
		al.addOptionSet(name);
	
		opsetDB.setParameters(opsetID++, al.getModel(), name);
		opsetDB.getSQLStatement("CREATEOPSET");
		opsetDB.createDB("CREATEOPSET");
	}
	
	public void addOption(String modelName, int opsetID, String name, int price){
		Automobile al = autoHashMap.get(modelName);
		al.addOption(al.getOptionSet(opsetID), name, price);
		
		optDB.setParameters(optID++, opsetID, name, price);
		optDB.getSQLStatement("CREATEOPT");
		optDB.createDB("CREATEOPT");
	}
	
	public void deleteAuto(String modelName){
		Automobile al = autoHashMap.get(modelName);
		autoHashMap.remove(modelName);
		autoDB.setModel(modelName);
		autoDB.getSQLStatement("DELETEAUTO");
		autoDB.deleteDB("DELETEAUTO");
	}
	
	public void deleteOptionSet(String modelName, int id){
		Automobile al = autoHashMap.get(modelName);
		al.deleteOptionSet(id);
		opsetDB.setID(id);
		opsetDB.getSQLStatement("DELETEOPSET");
		opsetDB.deleteDB("DELETEOPSET");
	}
	
	public void deleteOption(String modelName, int opset_id, int id){
		Automobile al = autoHashMap.get(modelName);
		al.deleteOption(al.getOptionSet(opset_id-1), id-1);
		optDB.setID(id);
		optDB.getSQLStatement("DELETEOPT");
		optDB.deleteDB("DELETEOPT");
	}
	
	public void updateAutomobile(String modelName, String make, int basePrice, String name){
		//Automobile al = autoHashMap.get(modelName);
		if(autoHashMap.get(modelName) ==null){
			System.out.println("You input an invaliad model Name, please try again!");
		}
		else{
			autoDB.setParameters(modelName, make, basePrice, name);
			autoDB.getSQLStatement("UPDATEAUTO");
			autoDB.createDB("UPDATEAUTO");
		}
	}

	public void updateOptionSet(int id, String model, String name){
		//Automobile al = autoHashMap.get(modelName);
		if(autoHashMap.get(model) ==null){
			System.out.println("You input an invaliad model Name, please try again!");
		}
		else{
			opsetDB.setParameters(id, model, name);
			opsetDB.getSQLStatement("UPDATEOPSET");
			opsetDB.createDB("UPDATEOPSET");
		}
	}
	
	public void updateOption(int id, int opset_id, String name, int price){
		//Automobile al = autoHashMap.get(modelName);

			optDB.setParameters(id, opset_id, name, price);
			optDB.getSQLStatement("UPDATEOPT");
			optDB.createDB("UPDATEOPT");

	}
	
}
