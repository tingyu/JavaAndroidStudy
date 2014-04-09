package com.example.studrecordapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class StatisticsActivity extends ActionBarActivity {
    
    private TextView q1HighTextView;
    private TextView q2HighTextView;
    private TextView q3HighTextView;
    private TextView q4HighTextView;
    private TextView q5HighTextView;
    
    private TextView q1LowTextView;
    private TextView q2LowTextView;
    private TextView q3LowTextView;
    private TextView q4LowTextView;
    private TextView q5LowTextView;
    
    
    private TextView q1AvgTextView;
    private TextView q2AvgTextView;
    private TextView q3AvgTextView;
    private TextView q4AvgTextView;
    private TextView q5AvgTextView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        q1HighTextView = (TextView) findViewById(R.id.q1HighTextView);
        q2HighTextView = (TextView) findViewById(R.id.q2HighTextView);
        q3HighTextView = (TextView) findViewById(R.id.q3HighTextView);
        q4HighTextView = (TextView) findViewById(R.id.q4HighTextView);
        q5HighTextView = (TextView) findViewById(R.id.q5HighTextView);
        
        
        q1LowTextView = (TextView) findViewById(R.id.q1LowTextView);
        q2LowTextView = (TextView) findViewById(R.id.q2LowTextView);
        q3LowTextView = (TextView) findViewById(R.id.q3LowTextView);
        q4LowTextView = (TextView) findViewById(R.id.q4LowTextView);
        q5LowTextView = (TextView) findViewById(R.id.q5LowTextView);
        
        
        q1AvgTextView = (TextView) findViewById(R.id.q1AvgTextView);
        q2AvgTextView = (TextView) findViewById(R.id.q2AvgTextView);
        q3AvgTextView = (TextView) findViewById(R.id.q3AvgTextView);
        q4AvgTextView = (TextView) findViewById(R.id.q4AvgTextView);
        q5AvgTextView = (TextView) findViewById(R.id.q5AvgTextView);
        
        DatabaseConnector dcConnector = new DatabaseConnector(getApplicationContext());
        
        q1HighTextView.setText(String.valueOf(dcConnector.getHighScore(1)));
        q2HighTextView.setText(String.valueOf(dcConnector.getHighScore(2)));
        q3HighTextView.setText(String.valueOf(dcConnector.getHighScore(3)));
        q4HighTextView.setText(String.valueOf(dcConnector.getHighScore(4)));
        q5HighTextView.setText(String.valueOf(dcConnector.getHighScore(5)));
        
        q1LowTextView.setText(String.valueOf(dcConnector.getLowScore(1)));
        q2LowTextView.setText(String.valueOf(dcConnector.getLowScore(2)));
        q3LowTextView.setText(String.valueOf(dcConnector.getLowScore(3)));
        q4LowTextView.setText(String.valueOf(dcConnector.getLowScore(4)));
        q5LowTextView.setText(String.valueOf(dcConnector.getLowScore(5)));
        
        q1AvgTextView.setText(String.valueOf(dcConnector.getAvgScore(1)));
        q2AvgTextView.setText(String.valueOf(dcConnector.getAvgScore(2)));
        q3AvgTextView.setText(String.valueOf(dcConnector.getAvgScore(3)));
        q4AvgTextView.setText(String.valueOf(dcConnector.getAvgScore(4)));
        q5AvgTextView.setText(String.valueOf(dcConnector.getAvgScore(5)));

        
    }


}
