package scale;

/** EditThread Interface contain two methods to present multi-threading function*/
public interface EditThread {
	
	/** Can edit OptionSet Name*/
	public void EditOptionSetName(String modelname, String optionsetname, String newname);
	
	/** Can edit Option Price*/
	public void EditOptionPrice(String modelname, String optsetname, String optname, float newprice);
}
