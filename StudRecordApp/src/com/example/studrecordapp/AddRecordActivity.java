package com.example.studrecordapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class AddRecordActivity extends ActionBarActivity {

    // EditTexts for record information
    private EditText studEditText;
    private EditText q1EditText;
    private EditText q2EditText;
    private EditText q3EditText;
    private EditText q4EditText;
    private EditText q5EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        studEditText = (EditText) findViewById(R.id.studEditText);
        q1EditText = (EditText) findViewById(R.id.q1EditText);
        q2EditText = (EditText) findViewById(R.id.q2EditText);
        q3EditText = (EditText) findViewById(R.id.q3EditText);
        q4EditText = (EditText) findViewById(R.id.q4EditText);
        q5EditText = (EditText) findViewById(R.id.q5EditText);

        Bundle extras = getIntent().getExtras(); // get Bundle of extras

        // if there are extras, use them to populate the EditTexts
        if (extras != null) {
            // rowID = extras.getLong("row_id");
            studEditText.setText(extras.getString("studId"));
            q1EditText.setText(extras.getString("q1"));
            q2EditText.setText(extras.getString("q2"));
            q3EditText.setText(extras.getString("q3"));
            q4EditText.setText(extras.getString("q4"));
            q5EditText.setText(extras.getString("q5"));

        } // end if

        // set event listener for the Save Contact Button
        Button saveRecordButton = (Button) findViewById(R.id.saveRecordButton);
        saveRecordButton.setOnClickListener(saveRecordButtonClicked);
    }

    // responds to event generated when user clicks the Done Button
    OnClickListener saveRecordButtonClicked = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (studEditText.getText().length() != 0) {
                AsyncTask<Object, Object, Object> saveContactTask = new AsyncTask<Object, Object, Object>() {
                    @Override
                    protected Object doInBackground(Object... params) {
                        saveRecord(); // save contact to the database
                        return null;
                    } // end method doInBackground

                    @Override
                    protected void onPostExecute(Object result) {
                        finish(); // return to the previous Activity
                    } // end method onPostExecute
                }; // end AsyncTask

                // save the contact to the database using a separate thread
                saveContactTask.execute((Object[]) null);
            } // end if
            else {/*
                   * // create a new AlertDialog Builder AlertDialog.Builder
                   * builder = new AlertDialog.Builder(AddEditContact.this);
                   * 
                   * // set dialog title & message, and provide Button to
                   * dismiss builder.setTitle(R.string.errorTitle);
                   * builder.setMessage(R.string.errorMessage);
                   * builder.setPositiveButton(R.string.errorButton, null);
                   * builder.show(); // display the Dialog
                   */
            } // end else
        } // end method onClick
    }; // end OnClickListener saveContactButtonClicked

    // saves contact information to the database
    private void saveRecord() {
        // get DatabaseConnector to interact with the SQLite database
        DatabaseConnector databaseConnector = new DatabaseConnector(this);

        if (getIntent().getExtras() == null) {
            // insert the contact information into the database
            databaseConnector.insertRecord(
                    Integer.parseInt(studEditText.getText().toString()),
                    Integer.parseInt(q1EditText.getText().toString()),
                    Integer.parseInt(q2EditText.getText().toString()),
                    Integer.parseInt(q3EditText.getText().toString()),
                    Integer.parseInt(q4EditText.getText().toString()),
                    Integer.parseInt(q5EditText.getText().toString()));
        } // end if
        else {
            databaseConnector.updateContact(
                    Integer.parseInt(studEditText.getText().toString()),
                    Integer.parseInt(q1EditText.getText().toString()),
                    Integer.parseInt(q2EditText.getText().toString()),
                    Integer.parseInt(q3EditText.getText().toString()),
                    Integer.parseInt(q4EditText.getText().toString()),
                    Integer.parseInt(q5EditText.getText().toString()));
        } // end else
    } // end class saveContact

}
