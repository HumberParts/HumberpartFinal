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
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    static private ProgressBar spinner;
    private EditText et_name, et_email;
    private String Fullname, Email;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        spinner = (ProgressBar) findViewById(R.id.progressBar9);
        spinner.setVisibility(View.GONE);


        et_name = (EditText)findViewById(R.id.admin_name);
        et_email = (EditText)findViewById(R.id.Email);

        Register = (Button)findViewById(R.id.registerhere);
        Register.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Register(); //call when button is clicked for validation
            }
        });
    }

    public void Register(){
        initialize();
        if(!validate()){
            Toast.makeText(this,"Registration has failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Registration is successful. Thank you!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validate() {
        boolean valid = true;
        if (Fullname.equals(null) || Fullname.length() < 3) {
            et_name.setError("Please Enter a valid name");
            valid = false;
        }

        if (Email.equals(null) || !Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            et_email.setError("Please Enter a valid email");
            valid = false;
        }
        return valid;
    }

    private void initialize() {
        Fullname = et_name.getText().toString().trim();
        Email = et_email.getText().toString().trim();
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
    public void onBackPressed(){
        MainActivity.clearSpinner();
        super.onBackPressed();  // optional depending on your needs
    }
}
