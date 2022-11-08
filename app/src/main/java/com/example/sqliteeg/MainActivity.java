package com.example.sqliteeg;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText Name, Phone_no, update_phone, id,editDel;
    Button Submit, update, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MYDBHelper mydbHelper = new MYDBHelper(this);
        Name = findViewById(R.id.editTextTextPersonName);
        Phone_no = findViewById(R.id.editTextTextPersonName2);
        update_phone = findViewById(R.id.editText);
        id = findViewById(R.id.id1);
        Submit = findViewById(R.id.button);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.del);
        editDel = findViewById(R.id.editText2);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        int id = Integer.parseInt(editDel.getText().toString());
            mydbHelper.deleteContact(id);
                Toast.makeText(MainActivity.this, "Delete The Data Successfully!!", Toast.LENGTH_SHORT).show();
            }
        });


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString();
                String phone_no = Phone_no.getText().toString();
                mydbHelper.addContact(name, phone_no);
                Toast.makeText(MainActivity.this, "Add The Data Successfully!!", Toast.LENGTH_SHORT).show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ID = Integer.parseInt(id.getText().toString());
                String phone = update_phone.getText().toString();
                ContactModel model = new ContactModel();
                model.id = ID;
                model.Description = phone;
                mydbHelper.updateContact(model);
                Toast.makeText(MainActivity.this, "Update The Data Successfully!!", Toast.LENGTH_SHORT).show();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(MainActivity.this, model.id, Toast.LENGTH_SHORT).show();
//                    }
//                },1000);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(MainActivity.this, model.Description, Toast.LENGTH_SHORT).show();
//                    }
//                },2000);
            }
        });

        ArrayList<ContactModel> arrayList = mydbHelper.fetchData();
        for (int i = 0; i < arrayList.size(); i++) {
            int id = arrayList.get(i).id;
            String name = arrayList.get(i).Name;
            String phone = arrayList.get(i).Description;
            Log.d("Contact", "id: " + id + " Name: " + name + " Phone no: " + phone);
        }


    }


}
