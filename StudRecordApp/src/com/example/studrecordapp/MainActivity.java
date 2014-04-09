package com.example.studrecordapp;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {
    Button addStudRecordButton;
    Button viewAllRecordButton;
    Button viewAllStatisticsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addStudRecordButton = (Button) findViewById(R.id.addStudRecordButton);
        viewAllRecordButton = (Button) findViewById(R.id.viewAllRecordButton);
        viewAllStatisticsButton = (Button) findViewById(R.id.viewAllStatisticsButton);

        addStudRecordButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent addRecordIntent = new Intent(MainActivity.this,
                        AddRecordActivity.class);
                startActivity(addRecordIntent);
            }
        });

        viewAllRecordButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent viewAllIntent = new Intent(MainActivity.this,
                        RecordListActivity.class);
                startActivity(viewAllIntent);
            }
        });
        
        viewAllStatisticsButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent viewStatisticsIntent = new Intent(MainActivity.this, StatisticsActivity.class);
                startActivity(viewStatisticsIntent);
            }
        });

    }

}
