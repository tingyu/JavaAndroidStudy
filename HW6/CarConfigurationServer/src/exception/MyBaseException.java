package exception;

//Make this classs abstract so that developers are forced to create 
//suitable exception types only
public abstract class MyBaseException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//each exception message will be hold here
	private String message;
	
	public MyBaseException(String msg){
		this.message = msg;
	}
	
	//Message can be retrieved using this accessor method
	public String getMessage(){
		return message;
	}
	
}
