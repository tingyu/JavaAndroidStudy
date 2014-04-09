package adapter;

/** EditThread Interface contain two methods to present multi-threading function*/
public interface CreateAuto {
	/** encapsulate the functions of read in txt file and populate the Automobile object in buildAuto*/
	public void buildAuto(String filename, String fileType);
	
	/** encapsulate the functions of print Automobile in buildAuto*/
	public void printAuto(String modelname);
}
