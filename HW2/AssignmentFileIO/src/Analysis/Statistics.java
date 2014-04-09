package Analysis;

import Model.Student;

public class Statistics extends StatisticAbstract implements Printable{
	int [] lowscores = new int [5];
	int [] highscores = new int [5];
	float [] avgscores = new float [5];
	
	public Statistics(){
		for(int i = 0; i < 5; i++){
			lowscores[i] = 0;
			highscores[i] = 0;
			avgscores[i] = 0;
		}
	}
	
	public void findlow(Student [] a){
		//This method will find lowest score and store it in an array names lowscores
		for(int i=0; i<5; i++){
			lowscores[i] = a[0].getScores()[i];
			for(int j=1; j<a.length; j++){
				if(lowscores[i] > a[j].getScores()[i]){
					lowscores[i] = a[j].getScores()[i];
				}
			}
		}
	}
	
	public void findhigh(Student [] a){
		//This method will find highest score and store it in an array names highscores
		for(int i=0; i<5; i++){
			highscores[i] = a[0].getScores()[i];
			for(int j=1; j<a.length; j++){
				if(highscores[i] < a[j].getScores()[i]){
					highscores[i] = a[j].getScores()[i];
				}
			}
		}
	}
	
	public void findavg(Student [] a){
		//This method will find avg score for each quiz and store it in an array names avgscores
		for(int i=0; i<5; i++){
			for(int j=0; j<a.length; j++){
				avgscores[i]+=a[j].getScores()[i];
			}
			
			avgscores[i] /= a.length;
		}
	}
	
	//add methods to print values of instance variables
	public void printInstanceVariables(){
		System.out.format("Low Score    %-5d %-5d %-5d %-5d %-5d%n", lowscores[0], lowscores[1], lowscores[2], lowscores[3], lowscores[4]);
		System.out.format("High Score   %-5d %-5d %-5d %-5d %-5d%n", highscores[0], highscores[1], highscores[2], highscores[3], highscores[4]);
		System.out.format("Avarage      %-5.1f %-5.1f %-5.1f %-5.1f %-5.1f", avgscores[0], avgscores[1], avgscores[2], avgscores[3], avgscores[4]);
	}
}

