package haqnawaz.org.DBListView_A020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button buttonAdd, buttonViewAll;
    EditText editName, editRollNumber;
    Switch switchIsActive;
    ListView listViewStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonViewAll = findViewById(R.id.buttonViewAll);
        editName = findViewById(R.id.editTextName);
        editRollNumber = findViewById(R.id.editTextRollNumber);
        switchIsActive = findViewById(R.id.switchStudent);
        listViewStudent = findViewById(R.id.listViewStudent);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            StudentModel studentModel;

            @Override
            public void onClick(View v) {
                try {
                    studentModel = new StudentModel(editName.getText().toString(), Integer.parseInt(editRollNumber.getText().toString()), switchIsActive.isChecked());
                    //Toast.makeText(MainActivity.this, studentModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                DBHelper dbHelper  = new DBHelper(MainActivity.this);
                dbHelper.addStudent(studentModel);
            }
        });

        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                //List<StudentModel> list = dbHelper.getAllStudents();
//                ArrayAdapter arrayAdapter = new ArrayAdapter<StudentModel>
//                        (MainActivity.this, android.R.layout.simple_list_item_1,list);
//                listViewStudent.setAdapter(arrayAdapter);
                ArrayList<StudentModel> ListViewArray =  dbHelper.getAllStudents();

                StudentAdapter adapter = new StudentAdapter(getBaseContext(), ListViewArray);
                ListView listView = findViewById(R.id.listViewStudent);
                listView.setAdapter(adapter);
                //setAdapterData(MainActivity.this);
            }
        });

    }

    /*public void callSet(){
        setAdapterData(MainActivity.this);
    }*/

    //private static ListView listView = null;
    //static DBHelper dbHelper = null;
    //static StudentAdapter adapter=null;
    /*public  void setAdapterData(Context context){
        Log.d("Error check", "setAdapterData: here");

        if(listView==null){
            listView=findViewById(R.id.listViewStudent);
        }
        if(dbHelper==null){
            dbHelper= new DBHelper(MainActivity.this);
        }


        ArrayList<StudentModel> ListViewArray =  dbHelper.getAllStudents();
        Log.d("Error check", "setAdapterData: " + ListViewArray);
        if(adapter==null){
            adapter = new StudentAdapter(context, ListViewArray);
        }
        //StudentAdapter adapter = new StudentAdapter(context, ListViewArray);
        //ListView listView = findViewById(R.id.listViewStudent);
        listView.setAdapter(adapter);
    }*/
}