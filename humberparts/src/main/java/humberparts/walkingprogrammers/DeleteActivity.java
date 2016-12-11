/**
 * Team Name: The Walking Programmers
 * Team Members: Rafil Yashooa, Masoud Rahguzar, Divesh Oree
 * Date: Oct/17th/2016
 * Project Name: Humber Parts (HP)
 */
package humberparts.walkingprogrammers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
    static private ProgressBar spinner;
    EditText student_id;
    DatabaseActivity db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        db = new DatabaseActivity(this);

        spinner = (ProgressBar)findViewById(R.id.progressBar4);
        spinner.setVisibility(View.GONE);

        student_id =(EditText)findViewById(R.id.ed_del_student_num);
    }
    static void clearSpinner(){
        spinner.setVisibility(View.GONE);
    }
    public void callIntent(View view) throws InterruptedException {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.buttondelete1:
                Integer deletedRows = db.deleteData(student_id.getText().toString());
                if(deletedRows > 0) {
                    Toast.makeText(DeleteActivity.this, "Student Deleted", Toast.LENGTH_SHORT).show();
                    spinner.setVisibility(View.VISIBLE);
                    startActivity(new Intent(this,AdminActivity.class));
                }else {
                    Toast.makeText(DeleteActivity.this, "Error in deleting", Toast.LENGTH_LONG).show();
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
