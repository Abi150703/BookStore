package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    GridView categoryGridView;
    String[] categoryNames = {"Fiction", "Non-Fiction", "Science", "Technology", "History", "Biography"};
    int[] categoryImages = {R.drawable.fiction2, R.drawable.nonfic9, R.drawable.science, R.drawable.tech1, R.drawable.history4, R.drawable.biography};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        categoryGridView = findViewById(R.id.categoryGridView);
        CategoryAdapter adapter = new CategoryAdapter(this, categoryNames, categoryImages);
        categoryGridView.setAdapter(adapter);

        categoryGridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(SecondActivity.this, BookListActivity.class);
            intent.putExtra("category", categoryNames[position]);
            startActivity(intent);


        });
    }
}



