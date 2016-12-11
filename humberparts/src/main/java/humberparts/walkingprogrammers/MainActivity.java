/**
 * Team Name: The Walking Programmers
 * Team Members: Rafil Yashooa, Masoud Rahguzar, Divesh Oree
 * Date: Oct/17th/2016
 * Project Name: Humber Parts (HP)
 */

package humberparts.walkingprogrammers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    static private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner = (ProgressBar)findViewById(R.id.progressBar_database);
        spinner.setVisibility(View.GONE);

    }

    static void clearSpinner(){
        spinner.setVisibility(View.GONE);
    }

    public void callIntent(View view) throws InterruptedException {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.buttonInventory:
                spinner.setVisibility(View.VISIBLE);
                intent = new Intent(this, InventoryActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonadminlogin:
                spinner.setVisibility(View.VISIBLE);
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.registerhere:
                spinner.setVisibility(View.VISIBLE);
                intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
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

}
