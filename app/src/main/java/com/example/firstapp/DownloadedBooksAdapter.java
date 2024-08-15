package com.example.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DownloadedBooksAdapter extends RecyclerView.Adapter<DownloadedBooksAdapter.BookViewHolder> {

    // DownloadedBooksAdapter.java

        private Context context;
        private List<String> downloadedBooks;

        public DownloadedBooksAdapter(Context context, List<String> downloadedBooks) {
            this.context = context;
            this.downloadedBooks = downloadedBooks;
        }

        @NonNull
        @Override
        public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_downloaded_book, parent, false);
            return new BookViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
            String bookInfo = downloadedBooks.get(position);
            holder.bookInfoTextView.setText(bookInfo);
        }

        @Override
        public int getItemCount() {
            return downloadedBooks.size();
        }

        public static class BookViewHolder extends RecyclerView.ViewHolder {
            TextView bookInfoTextView;

            public BookViewHolder(View itemView) {
                super(itemView);
                bookInfoTextView = itemView.findViewById(R.id.bookInfoTextView);
            }
        }
    }


