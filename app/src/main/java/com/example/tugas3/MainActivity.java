package com.example.tugas3;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etPhone;
    private Button btnInsert;
    private RecyclerView recyclerView;
    private DatabaseHelper dbHelper;
    private DataAdapter dataAdapter;
    private ArrayList<String> nameList;
    private ArrayList<String> phoneList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        btnInsert = findViewById(R.id.btn_insert);
        recyclerView = findViewById(R.id.recyclerView);

        dbHelper = new DatabaseHelper(this);
        nameList = new ArrayList<>();
        phoneList = new ArrayList<>();

        // Set RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataAdapter = new DataAdapter(nameList, phoneList);
        recyclerView.setAdapter(dataAdapter);

        // Load data from database
        loadData();

        // Insert button click listener
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();

                if (!name.isEmpty() && !phone.isEmpty()) {
                    if (dbHelper.insertData(name, phone)) {
                        Toast.makeText(MainActivity.this, "Data added", Toast.LENGTH_SHORT).show();
                        etName.setText("");
                        etPhone.setText("");
                        loadData();  // Reload data
                    } else {
                        Toast.makeText(MainActivity.this, "Error adding data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter name and phone", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadData() {
        Cursor cursor = dbHelper.getAllData();
        nameList.clear();
        phoneList.clear();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                nameList.add(cursor.getString(1)); // COL_2
                phoneList.add(cursor.getString(2)); // COL_3
            }
            cursor.close();
        }
        dataAdapter.notifyDataSetChanged();
    }
}
