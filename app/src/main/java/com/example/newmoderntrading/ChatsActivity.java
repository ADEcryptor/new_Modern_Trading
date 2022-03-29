package com.example.newmoderntrading;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class ChatsActivity extends AppCompatActivity {

    private final ArrayList<String> db = new ArrayList<>(Arrays.asList("Bike", "Pencil", "Laptop", "Chair", "Car", "Pencil", "Laptop", "Chair", "Car", "Pencil", "Laptop", "Chair", "Car"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        EditText searchChatsInput = findViewById(R.id.searchChatsInput);
        updateUI(searchChatsInput.getText().toString().trim());
        searchChatsInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateUI(searchChatsInput.getText().toString().trim());
            }
            @Override
            public void afterTextChanged(Editable editable){}
        });
    }

    public void updateUI(String searchChatsInputValue) {
        if(!searchChatsInputValue.equals("")) {
            ListView listOfItems = findViewById(R.id.ListOfItems);
            ArrayList<String> dbFiltered = new ArrayList<>();
            for (String str: db) {
                if(str.toLowerCase(Locale.ROOT).contains(searchChatsInputValue)) {
                    dbFiltered.add(str);
                }
            }
            //listOfItems.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ArrayList<String>()));
            ListAdapter myListAdapter = new CustomAdapter(this, dbFiltered);
            listOfItems.setAdapter(myListAdapter);
            //ArrayAdapter<String> listAdapter = (ArrayAdapter<String>) listOfItems.getAdapter();
            listOfItems.setOnItemClickListener((adapterView, view, position, id) -> {
                //listAdapter.remove(listAdapter.getItem(position));
                Intent intent = new Intent(ChatsActivity.this, ChatActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }
}