package com.example.finallab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button addNewPersonButton;
    EditText searchBar;
    ListView listView;

    ArrayList<Person> arrayList;
    ArrayAdapter arrayAdapter;

    boolean isSearchFinished = true;

    final DatabaseHelper personDatabase = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNewPersonButton = findViewById(R.id.btn_add_person);
        searchBar = findViewById(R.id.search_text);
        listView = findViewById(R.id.list_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                Intent intent = new Intent(MainActivity.this, DetailsOfPerson.class);

                if(isSearchFinished==true){
                    intent.putExtra("personId", arrayList.get(position));

                }

                else {

                    Person person = (Person) adapterView.getItemAtPosition(position);
                    intent.putExtra("personId", person.getPersonId());
                }

                startActivity(intent);

            }
        });

        addNewPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPerson.class);
                startActivity(intent);
            }
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.this.arrayAdapter.getFilter().filter(charSequence);
                isSearchFinished=false;
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.toString().equals("")){
                    isSearchFinished=true;
                }

            }


        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        arrayList = personDatabase.loadPersons();

        arrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);



    }
}
