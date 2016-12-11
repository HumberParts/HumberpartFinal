/**
 * Team Name: The Walking Programmers
 * Team Members: Rafil Yashooa, Masoud Rahguzar, Divesh Oree
 * Date: Oct/17th/2016
 * Project Name: Humber Parts (HP)
 */
package humberparts.walkingprogrammers;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by RAF on 2016-11-12.
 */

public class DatabaseViewer extends AppCompatActivity {
    static private ProgressBar spinner;
    DatabaseActivity db;
    ListView list;
    TextView id_num,student_num,date,part_num;
    LinkedList llist;
    private ListView lv_viewStudent;
    private StudentAdapter adapter_listview;
    private EditText et_search;
    private Button but_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_view);

        db = new DatabaseActivity(getBaseContext());

        Cursor res = db.databaseViewer();

        et_search = (EditText) findViewById(R.id.et_search);
        but_search = (Button) findViewById(R.id.but_find);

        final List<ModelStudent> mStudents = new LinkedList<>();

        while (res.moveToNext()) {
            ModelStudent student = new ModelStudent();

            student.setId(res.getString(0));
            student.setNumber(res.getString(1));
            student.setDate(res.getString(2));
            student.setPartNum(res.getString(3));

            mStudents.add(student);
        }

        but_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterStudents(et_search.getText().toString(), adapter_listview, db, mStudents);
            }
        });

        lv_viewStudent = (ListView)findViewById(R.id.lv_student);

        adapter_listview = new StudentAdapter(getBaseContext(), mStudents);
        lv_viewStudent.setAdapter(adapter_listview);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        db = new DatabaseActivity(this);
//        llist = new LinkedList<String>();
//
//        ActionBar ab = getSupportActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);
//
//        spinner = (ProgressBar)findViewById(R.id.progressBar_view);
//        spinner.setVisibility(View.GONE);
//
//        list = (ListView)findViewById(R.id.listview_database);
//        id_num=(TextView)findViewById(R.id.tv_id);
//        student_num=(TextView)findViewById(R.id.tv_student_num);
//        date =(TextView)findViewById(R.id.tv_date);
//        part_num=(TextView)findViewById(R.id.tv_part_num);
    }

    private void filterStudents(String studentId, StudentAdapter adapter_listview, DatabaseActivity db, List<ModelStudent> students) {
        students.clear();

        Cursor filters = db.search(studentId);

        while (filters.moveToNext()) {
            ModelStudent student = new ModelStudent();

            student.setId(filters.getString(0));
            student.setNumber(filters.getString(1));
            student.setDate(filters.getString(2));
            student.setPartNum(filters.getString(3));

            students.add(student);
        }

        adapter_listview.setItems(students);
        adapter_listview.notifyDataSetChanged();
    }

    //    static void clearSpinner(){
//        spinner.setVisibility(View.GONE);
//    }
    public void callIntent(View view) throws InterruptedException {
        Intent intent = null;
        switch (view.getId()) {
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

//    public void showData(){
//        Cursor cur = db.databaseViewer();
//        String[] strArr = new String[] {db.COL_1,db.COL_2, db.COL_3,db.COL_4};
//        int[] intArr = new int []{R.id.tv_id,R.id.tv_student_num,R.id.tv_date,R.id.tv_part_num};
//
//        SimpleCursorAdapter cursorAdapter;
//        cursorAdapter= new SimpleCursorAdapter(getBaseContext(),R.id.custom_layout2,cur,strArr,intArr,0);
//        list.setAdapter(cursorAdapter);
//
//    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}

class StudentAdapter extends BaseAdapter
{
    private final Context mContext;
    private List<ModelStudent> mStudents;
    private final LayoutInflater mLayoutInflater;
    private View mView;

    public StudentAdapter(Context context, List<ModelStudent> students) {
        mContext = context;
        mStudents = students;

        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setItems(List<ModelStudent> students) {
        this.mStudents = students;
    }
    @Override
    public int getCount() {
        return mStudents.size();
    }

    @Override
    public ModelStudent getItem(int position) {
        return mStudents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mStudents.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position);
    }

    private View createView(int position) {
        mView = mLayoutInflater.inflate(R.layout.view_student, null);

        TextView tv_studentId = (TextView) mView.findViewById(R.id.tv_stuid);
        TextView tv_studentNumber = (TextView) mView.findViewById(R.id.tv_stunumber);
        TextView tv_studentDate = (TextView) mView.findViewById(R.id.tv_studate);
        TextView tv_studentPartNum = (TextView) mView.findViewById(R.id.tv_stupartnum);

        tv_studentId.setText("Id: " + mStudents.get(position).getId());
        tv_studentNumber.setText("Student # " + mStudents.get(position).getNumber());
        tv_studentDate.setText("Date:  " + mStudents.get(position).getDate());
        tv_studentPartNum.setText("Part # " + mStudents.get(position).getPartNum());

        return mView;
    }
}
