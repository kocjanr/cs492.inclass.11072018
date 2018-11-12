package com.kocjan.kocjanryaninclass;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mGrade;
    private Button mAdd;
    private Button mAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        mFirstName = (EditText) findViewById(R.id.first_name);
        mLastName = (EditText) findViewById(R.id.last_name);
        mGrade = (EditText) findViewById(R.id.grade);

        mAdd = (Button) findViewById(R.id.add_button);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();
                String grade = mGrade.getText().toString();

                boolean result = myDb.insertData(firstName, lastName, grade);

                if (result) {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mAll = (Button) findViewById(R.id.view_all_button);
        mAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = myDb.getAllData();
                if (cursor.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "NO entries", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("Id :" + cursor.getString(0));
                    buffer.append("First :" + cursor.getString(1));
                    buffer.append("Last :" + cursor.getString(2));
                    buffer.append("Grade :" + cursor.getString(3));
                }

                showMessage("All users", buffer.toString());

            }
        });
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
