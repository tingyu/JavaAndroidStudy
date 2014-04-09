package com.example.mortgagecalculator;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class ResultActivity extends ActionBarActivity {
    private double totalMonthlyPayment;
    private double totalMortgagePayment;
    private String payoffDate;
    
    private TextView totalMonthlyPaymentTextView;
    private TextView totalMortgagePaymentTextView;
    private TextView payoffDateTextView;
    private Button returnButton;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //get intent from main
        Intent intent = getIntent();
        totalMonthlyPayment = intent.getDoubleExtra("totalMonthlyPayment", 0); 
        totalMortgagePayment = intent.getDoubleExtra("totalPaymentForMortageTerm", 0);
        payoffDate = intent.getStringExtra("Date");
        
        totalMonthlyPaymentTextView = (TextView) findViewById(R.id.totalMonthlyPaymentTextView);
        totalMonthlyPaymentTextView.setText("$"+String.format("%.2f", totalMonthlyPayment));
        
        totalMortgagePaymentTextView = (TextView) findViewById(R.id.totalMortgagePaymentTextView);
        totalMortgagePaymentTextView.setText("$"+String.format("%.2f", totalMortgagePayment));
        
        payoffDateTextView = (TextView) findViewById(R.id.payoffDateTextView);
        payoffDateTextView.setText(payoffDate);
        
        //provide a return button for return to the calculator
        returnButton = (Button) findViewById(R.id.reCalculate);
        
        returnButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent reCaltulateIntent = new Intent(ResultActivity.this, MainActivity.class);
                
                startActivity(reCaltulateIntent);
            }
        });
        

    }


}
