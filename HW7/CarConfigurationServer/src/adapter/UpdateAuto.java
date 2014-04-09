package adapter;

/** EditThread Interface contain two methods to present multi-threading function*/
public interface UpdateAuto {
	/** encapsulate the functions of updateOptionSetName of Automobile in buildAuto*/
	public void updateOptionSetName(String modelname, String optionsetname, String newname);
	
	/** encapsulate the functions of updateOptionPrice of Automobile in buildAuto*/
	public void updateOptionPrice(String modelname, String optionname, String option, float newprice);
}
