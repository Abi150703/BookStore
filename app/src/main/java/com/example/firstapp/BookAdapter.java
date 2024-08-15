package com.example.firstapp;

        import android.content.Context;
        import android.os.Environment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.io.File;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context context;
    private List<Book> bookList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onDownloadClick(int position);
        void onAddToCartClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);

        holder.bookTitle.setText(book.getTitle());
        holder.bookPrice.setText(String.valueOf(book.getPrice()));
        holder.bookImage.setImageResource(book.getImageResource());

        holder.downloadButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDownloadClick(position);
            }
        });

        holder.addToCartButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAddToCartClick(position);
            }
            Toast.makeText(context, "Added to Cart: " + book.getTitle(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle;
        TextView bookPrice;
        ImageView bookImage;
        Button downloadButton;
        Button addToCartButton;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookPrice = itemView.findViewById(R.id.bookPrice);
            bookImage = itemView.findViewById(R.id.bookImage);
            downloadButton = itemView.findViewById(R.id.downloadButton);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }

    public void downloadBookDetails(Book book) {
        File downloadFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if (!downloadFolder.exists()) {
            downloadFolder.mkdirs();
        }
        File bookFile = new File(downloadFolder, book.getTitle() + ".txt");

        try (FileWriter writer = new FileWriter(bookFile)) {
            writer.write("Title: " + book.getTitle() + "\n");
            writer.write("Price: " + book.getPrice() + "\n");
            writer.write("Description: " + book.getDescription() + "\n");
            Toast.makeText(context, "Downloaded: " + book.getTitle(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Download failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}



