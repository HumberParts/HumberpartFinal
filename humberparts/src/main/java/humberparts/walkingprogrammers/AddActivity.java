/**
 * Team Name: The Walking Programmers
 * Team Members: Rafil Yashooa, Masoud Rahguzar, Divesh Oree
 * Date: Oct/17th/2016
 * Project Name: Humber Parts (HP)
 */
package humberparts.walkingprogrammers;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity {
    static private ProgressBar spinner;
    DatabaseActivity db;
    EditText student_id, part1, part2, part3, part4, part5;
    String date;
    String buffer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DatabaseActivity(this);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        spinner = (ProgressBar)findViewById(R.id.progressBar3);
        spinner.setVisibility(View.GONE);

        student_id = (EditText)findViewById(R.id.edittextstudentnumber);
        part1 = (EditText)findViewById(R.id.Part);
        part2 = (EditText)findViewById(R.id.Part2);
        part3 = (EditText)findViewById(R.id.Part3);
        part4 = (EditText)findViewById(R.id.Part4);
        part5 = (EditText)findViewById(R.id.Part5);
        date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        buffer2 = part1.getText().toString();
        //+ part2.getText().toString() +
             // part3.getText().toString() + part4.getText().toString()
            //  + part5.getText().toString();
    }
    static void clearSpinner(){
        spinner.setVisibility(View.GONE);
    }
    public void callIntentAdd(View view) throws InterruptedException {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.buttonadd1:
                Boolean isAdded = db.insertData(student_id.getText().toString(),date,part1.getText().toString());
                if (isAdded) {
                    spinner.setVisibility(View.VISIBLE);
                    Toast.makeText(this, "Part Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,AdminActivity.class));
                } else {
                    Toast.makeText(this, "Error in adding ", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_search).setVisible(false);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, settingActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){
        MainActivity.clearSpinner();
        super.onBackPressed();  // optional depending on your needs
    }


}
