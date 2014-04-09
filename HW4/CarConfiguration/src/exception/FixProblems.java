package exception;
import java.io.*;
import java.util.ArrayList;

public class FixProblems extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorNo;
	private String errorMsg;
	private ArrayList<String> input = new ArrayList<String>();
	
	public FixProblems(){
		super();
		printMyProblem();
	}
	
	public FixProblems(String errorMsg){
		super();
		this.errorMsg = errorMsg;
		printMyProblem();
	}
	
	public FixProblems(int errorNo){
		super();
		this.errorNo = errorNo;
		printMyProblem();
		selHealingMechanism();
	}
	
	public FixProblems(int errorNo, String errorMsg){
		super();
		this.errorNo = errorNo;
		this.errorMsg = errorMsg;
		printMyProblem();
	}
	
	public int getErrorNo(){
		return errorNo;
	}
	
	public void setErrorNo(int errorNo){
		this.errorNo = errorNo;
	}
	
	public String getErrorMsg(){
		return errorMsg;
	}
	
	public void selHealingMechanism(){
		switch (errorNo) {
			case 1:
				System.out.println("Please input the missing price"); 
				try{
				    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				    String s = bufferRead.readLine();
				    input.add(s);
			 
				    System.out.println("Add basePrice: " + s  + " sucessfully!!");
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Please add the missing OptionSet data"); 
				System.out.println("Please input the missing OptionSet's name"); 
				try{
				    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				    String s = bufferRead.readLine();
				    input.add(s);

				    System.out.println("Add OptionSet Name: " + s  + " sucessfully!!");
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}

				break;
			case 3:
				System.out.println("Please add the missing Option data"); 
				System.out.println("Please input the missing Option data's name"); 
				try{
				    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				    String s = bufferRead.readLine();
				    input.add(s);

				    System.out.println("Add Option name: " + s  + " sucessfully!!");
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				
				System.out.println("Please input the missing Option's price"); 
				try{
				    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				    String s = bufferRead.readLine();
				    input.add(s);

				    System.out.println("Add Option price: " + s  + " sucessfully!!");
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				break;
			case 4:
				System.out.println("Please input the filename or check the filename"); 
				try{
				    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				    String s  = bufferRead.readLine();
				    input.add(s);

				    System.out.println("Input filename: " + s  + " sucessfully!!");
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("Please input the missing model"); 
				try{
				    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				    String s = bufferRead.readLine();
				    input.add(s);
			 
				    System.out.println("Add model: " + s  + " sucessfully!!");
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				break;
			case 6:
				System.out.println("Please input the missing make"); 
				try{
				    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				    String s = bufferRead.readLine();
				    input.add(s);
			 
				    System.out.println("Add make: " + s  + " sucessfully!!");
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				break;
			default:
				break;
			
		}
	}
	
	public void printMyProblem() {
		System.out.println("FixProblems [errorNo=" + errorNo + ", errorMsg=" + errorMsg + "]"); 
	}
	
	public ArrayList<String> getInputData(){
		return input;
	}
	
}
