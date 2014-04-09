package Model;

import Analysis.Printable;

public class Student implements Printable{
	private int SID;
	private int scores[] = new int[5];
	
	public Student(){
	}
	
	//write public get and set methods for SID and scores
	public void setSID(int SID){
		this.SID = SID;
	}
	
	public void setScores(int[] temp){
		this.scores= temp.clone();
	}
	
	public int getSID(){
		return SID;
	}
	
	public int[] getScores(){
		return scores;
	}
	
	//add methods to print values of instance variables	
	public void printInstanceVariables(){
		System.out.println(SID + "\t" + scores[0] + "\t" + scores[1] + "\t" + scores[2] + "\t" + scores[3] + "\t" + scores[4]);
	}
}
