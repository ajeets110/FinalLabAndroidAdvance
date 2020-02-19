package com.example.finallab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPerson extends AppCompatActivity {

        EditText name;
        EditText lastName;
        EditText phoneNumber;
        EditText address;

        Button addPersonButton;

        final DatabaseHelper helper = new DatabaseHelper(this);

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_person);

            name= findViewById(R.id.txt_name);
            lastName = findViewById(R.id.txt_last_name);
            phoneNumber = findViewById(R.id.txt_phone_number);
            address=findViewById(R.id.txt_address);

            addPersonButton = findViewById(R.id.btn_add_person_activity);

            addPersonButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!name.getText().toString().equals("") && !lastName.getText().toString().equals("") && !phoneNumber.getText().toString().equals("") && !address.getText().toString().equals("")){

                        Person person = new Person();
                        person.setFirstName(name.getText().toString());
                        person.setLastName(lastName.getText().toString());
                        person.setPhoneNumber(phoneNumber.getText().toString());
                        person.setAddress(address.getText().toString());

                        helper.addPerson(person);
                        Toast.makeText(AddPerson.this,"New person added to phonebook",Toast.LENGTH_SHORT).show();
                        finish();

                    }

                    else {
                        Toast.makeText(AddPerson.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }

