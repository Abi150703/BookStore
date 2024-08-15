package com.example.firstapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DownloadedBooksActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DownloadedBooksAdapter adapter;
    private List<String> downloadedBooksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaded_books);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get downloaded book details
        downloadedBooksList = getDownloadedBooks(this);

        // Initialize and set adapter
        adapter = new DownloadedBooksAdapter(this, downloadedBooksList);
        recyclerView.setAdapter(adapter);
    }

    // Method to retrieve downloaded book details from SharedPreferences
    private List<String> getDownloadedBooks(Context context) {
        List<String> downloadedBooks = new ArrayList<>();
        SharedPreferences sharedPreferences = context.getSharedPreferences("DownloadedBooks", Context.MODE_PRIVATE);

        // Retrieve all keys (book titles)
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            // Value is the file path
            String filePath = (String) entry.getValue();
            downloadedBooks.add(entry.getKey() + " - " + filePath);
        }

        return downloadedBooks;
    }
}
