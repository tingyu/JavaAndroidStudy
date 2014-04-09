package Exception;

import java.lang.Exception;

public class MoreStudentsException extends ArrayIndexOutOfBoundsException{
	
	public MoreStudentsException(){}
	
	public MoreStudentsException(String message){
		super(message);
	}
}
