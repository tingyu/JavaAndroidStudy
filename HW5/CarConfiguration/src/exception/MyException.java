package exception;

public class MyException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Missing price for Automobile in Text file
	public static class MissPrice extends MyBaseException{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MissPrice(String msg){
			super(msg);
		}
	}
	
	//Missing OptionSet data (or part of it)
	public static class MissOptionSetData extends MyBaseException{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MissOptionSetData(String msg){
			super(msg);
		}
	}
	
	//Missing Option data
	public static class MissOptionData extends MyBaseException{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MissOptionData(String msg){
			super(msg);
		}
	}
	
	//Missing filename or wrong name
	public static class MissFileName extends MyBaseException{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MissFileName(String msg){
			super(msg);
		}
	}
	
	//Missing filename or wrong name
	public static class MissModel extends MyBaseException{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MissModel(String msg){
			super(msg);
		}
	}
	
	//Missing filename or wrong name
	public static class MissMake extends MyBaseException{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MissMake(String msg){
			super(msg);
		}
	}
}
