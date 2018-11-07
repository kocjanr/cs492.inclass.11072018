package com.kocjan.kocjanryaninclass;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        mFirstName = (EditText)findViewById(R.id.first_name);

        mLastName = (EditText) findViewById(R.id.last_name);

        mGrade = (EditText) findViewById(R.id.grade);

        mAdd = (Button)findViewById(R.id.add_button);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();
                String grade = mGrade.getText().toString();

               boolean result = myDb.insertData(firstName,lastName,grade);

               if(result){
                   Toast.makeText(MainActivity.this,"Data Inserted", Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(MainActivity.this,"Error", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }


}
