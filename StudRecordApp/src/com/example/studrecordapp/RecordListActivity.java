package com.example.studrecordapp;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.R.integer;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Build;

public class RecordListActivity extends ListActivity {

    private ListView recordListView; // the ListActivity's ListView
    private CursorAdapter recordAdapter; // adapter for ListView

    private static class RecordAdapter extends BaseAdapter {

        private Cursor mCursor = null;
        
        private Context mContext = null;
        
        private List<Student> mRecordList = null;
        
        public RecordAdapter(Context context, Cursor cursor) {
            mCursor = cursor;
            mContext = context;
            mRecordList = new ArrayList<>();
            if (cursor != null) {
                while(cursor.moveToNext()) {
                    Student student = new Student();
                    student.studentId = cursor.getInt(0);
                    student.q1 = cursor.getInt(1);
                    student.q2 = cursor.getInt(2);
                    student.q3 = cursor.getInt(3);
                    student.q4 = cursor.getInt(4);
                    student.q5 = cursor.getInt(5);
                    mRecordList.add(student);
                }
            }
        }
        
        private static class Student {
            int studentId;
            int q1,q2,q3,q4,q5;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = null;
            if (convertView != null) {
                tv = (TextView) convertView;
            } else {
                LayoutInflater inflater = (LayoutInflater)mContext.getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_record_list, null);
                tv = (TextView) convertView;
            }
            Student student = mRecordList.get(position);
            tv.setText(" " + student.studentId + "    " + student.q1 + "  " + student.q2 + "  " + student.q3 + "  " + student.q4 + "  " + student.q5);
            return convertView;
        }
        
        @Override
        public long getItemId(int position) {
            return position;
        }
        
        @Override
        public Object getItem(int position) {
            return mRecordList.get(position);
        }
        
        @Override
        public int getCount() {
            return mCursor.getCount();
        }
    };

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_record_list);
        recordListView = getListView(); // get the built-in ListView
        // recordListView.setOnItemClickListener(viewRecordListener);
        

        // map each student id to a TextView in the ListView layout
        String[] from = new String[] { "studId" };
        int[] to = new int[] { R.id.contactTextView };
//        recordAdapter = new SimpleCursorAdapter(RecordListActivity.this,
//                R.layout.activity_record_list, null, from, to);
//        setListAdapter(recordAdapter); // set contactView's adapter
    }

    @Override
    protected void onResume() {
        super.onResume(); // call super's onResume method

        // create new GetContactsTask and execute it
        new GetContactsTask().execute((Object[]) null);
    } // end method onResume

    @SuppressWarnings("deprecation")
    @Override
    protected void onStop() {
        // Cursor cursor = recordAdapter.getCursor(); // get current Cursor

        // if (cursor != null)
        //    cursor.deactivate(); // deactivate it

        // recordAdapter.changeCursor(null); // adapted now has no Cursor
        super.onStop();
    } // end method onStop

    // performs database query outside GUI thread
    private class GetContactsTask extends AsyncTask<Object, Object, Cursor> {
        DatabaseConnector databaseConnector = new DatabaseConnector(
                RecordListActivity.this);

        // perform the database access
        @Override
        protected Cursor doInBackground(Object... params) {
            databaseConnector.open();

            // get a cursor containing call contacts
            return databaseConnector.getAllRecords();
        } // end method doInBackground

        // use the Cursor returned from the doInBackground method
        @Override
        protected void onPostExecute(Cursor result) {
            // recordAdapter.changeCursor(result); // set the adapter's Cursor
            RecordAdapter adapter = new RecordAdapter(RecordListActivity.this, result);
            RecordListActivity.this.setListAdapter(adapter);
            databaseConnector.close();
        } // end method onPostExecute
    } // end class GetContactsTask

    /*
     * // create the Activity's menu from a menu resource XML file
     * 
     * @Override public boolean onCreateOptionsMenu(Menu menu) {
     * super.onCreateOptionsMenu(menu); MenuInflater inflater =
     * getMenuInflater(); inflater.inflate(R.menu.addressbook_menu, menu);
     * return true; } // end method onCreateOptionsMenu
     * 
     * // handle choice from options menu
     * 
     * @Override public boolean onOptionsItemSelected(MenuItem item) { // create
     * a new Intent to launch the AddEditContact Activity Intent addNewContact =
     * new Intent(MainActivity.this, AddEditContact.class);
     * startActivity(addNewContact); // start the AddEditContact Activity return
     * super.onOptionsItemSelected(item); // call super's method } // end method
     * onOptionsItemSelected
     */
    // event listener that responds to the user touching a contact's name
    // in the ListView
    /*
    OnItemClickListener viewRecordListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
            // create an Intent to launch the ViewContact Activity
            Intent viewRecord = new Intent(RecordListActivity.this,
                    ViewStudRecordActivity.class);

            // pass the selected contact's row ID as an extra with the Intent
            viewRecord.putExtra(ROW_ID, arg3);
            startActivity(viewRecord); // start the ViewContact Activity
        } // end method onItemClick
    }; // end viewContactListener*/

}
