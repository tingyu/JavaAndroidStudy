package util;

import java.text.NumberFormat;
import java.util.Scanner;

import android.R.integer;
 
/**
 * calculates and displays the mortgage payment amount given the
 * amount of the mortgage, the term of the mortgage, and the
 * interest rate of the mortgage.
 *
 * Then display the balance over the term of the loan.
 *
 *
 *
 */
 
public class MortgageCalculator {
    
    private String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
 
    
   /**
    * Calculates the monthly payment for a specified loan
    *
    * @param loanAmount total amount of loan
    * @param termInYears term of loan in years
    * @param interestRate loan interest rate, 5.6% = 5.6
    * @return monthly payment
    */
   public double calculateMonthlyPayment(
         int loanAmount, double downPayment, int termInYears, double interestRate, double propertyTax, double propertyInsurance) {
          
         // Convert interest rate into a decimal
         // eg. 6.5% = 0.065
          
         interestRate /= 100.0;
          
         // Monthly interest rate
         // is the yearly rate divided by 12
          
         double monthlyRate = interestRate / 12.0;
          
         // The length of the term in months
         // is the number of years times 12
          
         int termInMonths = termInYears * 12;
          
         // Calculate the monthly payment
         // Typically this formula is provided so
         // we won't go into the details
          
         // The Math.pow() method is used
         // to calculate values raised to a power
          
         double monthlyPayment =
            (loanAmount*(1-downPayment/100)*monthlyRate) /
               (1-Math.pow(1+monthlyRate, -termInMonths));
          //double monthlyPayment=(loanAmount+(loanAmount*0.80)*interestRate*termInYears)/termInMonths;
         
         monthlyPayment = monthlyPayment + propertyTax/12 + propertyInsurance/12;
         return monthlyPayment;
      }
   
       /**
        * totalPaymentForMortageTerm
        * @param monthlyPayment
        * @param termInYears
        * @return
        */
       public double totalPaymentForMortageTerm(double monthlyPayment, int termInYears){
           return monthlyPayment*termInYears*12;
       }
       
       
       /**
        * calaculate payoff date
        * @param year
        * @param monthIndex
        * @param termInYears
        * @return
        */
       public String calculatePayoffDate(String year, int monthIndex, int termInYears){
           StringBuffer date = new StringBuffer();
           String dateMonth;
           
           int dateYear = Integer.valueOf(year);
           dateYear = dateYear + termInYears;
           
           if(monthIndex -1 >=0){
               dateMonth = month[monthIndex-1];
           }else{
               dateYear = dateYear -1;
               dateMonth = month[11];
           }
           
           
           date.append(dateMonth);
           date.append(",");
           date.append(dateYear);
           
           return date.toString();
       }

 
}