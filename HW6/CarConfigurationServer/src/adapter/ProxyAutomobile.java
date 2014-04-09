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

/**Abstract class ProxyAutomobile, contains definition of functions in Interface CreateAuto, UpdateAuto, EditThread*/
public abstract class ProxyAutomobile {
	/** A static LinkedHashMap of Automobile */
	private static LinkedHashMap<String, Automobile> autoHashMap = new LinkedHashMap<String, Automobile>();
	private StringBuffer allModel = new StringBuffer();
	
	/**Definition of buildAuto function in Interface CreateAuto*/
	public void buildAuto(String filename, String fileType){
		FileIO fileio = new FileIO();
		if(fileType.equals("Txt")){
			Automobile al = fileio.buildAutoObject(filename);
			autoHashMap.put(al.getModel(), al);
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

}
