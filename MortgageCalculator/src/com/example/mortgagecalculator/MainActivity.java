package com.example.mortgagecalculator;

/**
 * MortgageCalculator
 * Prepare an Android App using the interface shown at http://www.mlcalc.com/. Only code the Mortgage App and do not code the loan app.
 *
 *For calculations show:
 *..Total Monthly Payment
 *..Total of payment for Mortgage term
 *..Payoff date
 *
 * @author tingy
 */

import util.MortgageCalculator;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements OnItemSelectedListener {

    //constants used when saving/restoring state
    private static final String INPUT_DOUBLE = "INPUT_DOUBLE";
    private static final String INPUT_ZIPCODE = "INPUT_ZIPCODE";
    private static final String INPUT_DATE = "INPUT_DATE";
    
    private EditText purchasePriceEditText; //purchase price entered by the user
    private EditText downPaymentEditText;   //down payment entered by the user
    private EditText mortgageTermEditText;  //mortgage term entered by the user
    private EditText interestRateEditText;  //interest rate entered by the user
    private EditText propertyTaxEditText;   //property tax entered by the user
    private EditText propertyInsuranceEditText; //property insurane entered by the user
    private EditText pmiEditText;   //pmi entered by the user
    private EditText zipCodeEditText;   //zip code entered by the user
    private Spinner monthSpinner;    //month selected by the user
    private Spinner yearSpinner;    //year selected by the user
    private TextView showDownPaymentTextView;
    private Button button;
    
    private double purchasePrice;
    private double downPayment;
    private double mortgageTerm;
    private double interestRate;
    private double propertyTax;
    private double propertyInsurance;
    private double pmi;
    private double showDownPayment;
    private int zipcode;
    private String month;
    private String year;
    
    private String[] state = { "1999", "2000", "2001", "2002","2003","2004","2005","2006","2007",
            "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018",
            "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029"};    
    
 //   Spinner yearSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // inflate the GUI
        
        //populate the year spinner
        System.out.println(state.length);
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
          android.R.layout.simple_spinner_item, state);
        adapter_state
          .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapter_state);
        yearSpinner.setOnItemSelectedListener(this);

        
        //get references
        purchasePriceEditText = (EditText) findViewById(R.id.purchasePriceEditText);
        downPaymentEditText = (EditText) findViewById(R.id.downPaymentEditText);
        mortgageTermEditText = (EditText) findViewById(R.id.mortgageTermEditText);
        interestRateEditText = (EditText) findViewById(R.id.interestRateEditText);
        propertyTaxEditText = (EditText) findViewById(R.id.propertyTaxEditText);
        propertyInsuranceEditText = (EditText) findViewById(R.id.propertyInsuranceEditText);
        pmiEditText = (EditText) findViewById(R.id.PMIEditText);
        zipCodeEditText = (EditText) findViewById(R.id.zipCodeEditText);
        monthSpinner = (Spinner) findViewById(R.id.monthSpinner);
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        showDownPaymentTextView = (TextView) findViewById(R.id.showDownPaymentTextView);
        
        
        //check if app just started or is being restored from memroy
        if(savedInstanceState == null) //the app just started running
        {
            //The following do the initialization
            purchasePriceEditText.setText("200000");
            downPaymentEditText.setText("20");
            showDownPaymentTextView.setText("($40000)");
            mortgageTermEditText.setText("30");
            interestRateEditText.setText("6.25");
            propertyTaxEditText.setText("3000");
            propertyInsuranceEditText.setText("1500");
            pmiEditText.setText("0.52");
            zipCodeEditText.setText("94305");
            monthSpinner.setSelection(2);
            yearSpinner.setSelection(15);
        }//end if
        else //app is being restoreed from memory, not executed from scatch
        {
            //initialize the purchase price to saved amount
            purchasePrice = savedInstanceState.getDoubleArray(INPUT_DOUBLE)[0];
            downPayment = savedInstanceState.getDoubleArray(INPUT_DOUBLE)[1];
            mortgageTerm = savedInstanceState.getDoubleArray(INPUT_DOUBLE)[2];
            interestRate = savedInstanceState.getDoubleArray(INPUT_DOUBLE)[3];
            propertyTax = savedInstanceState.getDoubleArray(INPUT_DOUBLE)[4];
            propertyInsurance = savedInstanceState.getDoubleArray(INPUT_DOUBLE)[5];
            pmi = savedInstanceState.getDoubleArray(INPUT_DOUBLE)[6];
   //         showDownPayment = savedInstanceState.getDoubleArray(INPUT_DOUBLE)[7];
            zipcode = savedInstanceState.getInt(INPUT_ZIPCODE);
            month = savedInstanceState.getStringArray(INPUT_DATE)[0];
            year = savedInstanceState.getStringArray(INPUT_DATE)[1];
        }
        
        
        purchasePriceEditText.addTextChangedListener(textWatcher);
        downPaymentEditText.addTextChangedListener(textWatcher);
        
        addListenerOnButton();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
        // TODO Auto-generated method stub
        yearSpinner.setSelection(position);
        String selState = (String) yearSpinner.getSelectedItem();
        //selVersion.setText("Selected Android OS:" + selState);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
        
    }
    /**
     * addListenerOnButton(), capture Edittext value for clicking button
     */
    private void addListenerOnButton(){
        button = (Button) findViewById(R.id.calculator);
        
        button.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               purchasePrice = Double.parseDouble(purchasePriceEditText.getText().toString());
               downPayment = Double.parseDouble(downPaymentEditText.getText().toString());
               mortgageTerm = Double.parseDouble(mortgageTermEditText.getText().toString());
               interestRate = Double.parseDouble(interestRateEditText.getText().toString());
               propertyTax = Double.parseDouble(propertyTaxEditText.getText().toString());
               propertyInsurance = Double.parseDouble(propertyInsuranceEditText.getText().toString());
               pmi = Double.parseDouble(pmiEditText.getText().toString());
               zipcode = Integer.parseInt(zipCodeEditText.getText().toString());
               month = monthSpinner.getSelectedItem().toString();
               year = yearSpinner.getSelectedItem().toString();
               int monthAt = monthSpinner.getSelectedItemPosition();      
               
               MortgageCalculator cal = new MortgageCalculator();
               
               double totalMonthlyPayment = cal.calculateMonthlyPayment((int)purchasePrice, downPayment, (int)mortgageTerm, interestRate, propertyTax, propertyInsurance);
               double totalPaymentForMortageTerm = cal.totalPaymentForMortageTerm(totalMonthlyPayment, (int)mortgageTerm);
               String payoffDateString = cal.calculatePayoffDate(year, monthAt, (int)mortgageTerm);

               
               Intent calculateIntent = new Intent(MainActivity.this, ResultActivity.class);
               calculateIntent.putExtra("totalMonthlyPayment", totalMonthlyPayment);
               calculateIntent.putExtra("totalPaymentForMortageTerm", totalPaymentForMortageTerm);
               calculateIntent.putExtra("Date", payoffDateString);
               
               startActivity(calculateIntent);
            }
        });

    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
       super.onSaveInstanceState(outState);
       double[] saveDoubles = {purchasePrice, downPayment, mortgageTerm, interestRate, propertyTax, propertyInsurance, pmi, showDownPayment};
       String[] dateStrings ={month, year};
       
       outState.putDoubleArray(INPUT_DOUBLE, saveDoubles);
       outState.putDouble(INPUT_ZIPCODE, zipcode);
       outState.putStringArray(INPUT_DATE, dateStrings);
    } // end method onSaveInstanceState


    
    
  // event-handling object that responds to billEditText's events
    private TextWatcher textWatcher = new TextWatcher() {
        @SuppressLint("NewApi")
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
            if(purchasePriceEditText.getText().toString().isEmpty()||downPaymentEditText.getText().toString().isEmpty()){
                showDownPaymentTextView.setText("($NaN)");

            }else{
                purchasePrice = Double.parseDouble(purchasePriceEditText.getText().toString());
                downPayment = Double.parseDouble(downPaymentEditText.getText().toString());
                
                double down = purchasePrice * (downPayment/100.0);
                showDownPaymentTextView.setText("($"+String.valueOf(down)+")");
            }
        }
        
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                int after) {
            // TODO Auto-generated method stub
            
        }
        
        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            
        }
    };

}
