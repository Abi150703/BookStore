package com.example.firstapp;

        import android.Manifest;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.os.Bundle;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;
        import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private static final int REQUEST_WRITE_STORAGE = 112;

    RecyclerView bookRecyclerView;
    List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        bookRecyclerView = findViewById(R.id.bookRecyclerView);

        // Check for storage permissions
        boolean hasPermission = (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }

        // Extract category name from intent
        String category = getIntent().getStringExtra("category");
        setTitle(category + " Books");

        // Initialize book list based on the category
        bookList = generateBookList(category);

        BookAdapter adapter = new BookAdapter(this, bookList);
        bookRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {

            @Override
            public void onDownloadClick(int position) {
                Book selectedBook = bookList.get(position);
                // Perform download logic here
                adapter.downloadBookDetails(selectedBook);
            }

            @Override
            public void onAddToCartClick(int position) {
                // Handle add to cart button click
                Book selectedBook = bookList.get(position);
                Intent intent = new Intent(BookListActivity.this, BookDetailsActivity.class);
                intent.putExtra("bookTitle", selectedBook.getTitle());
                intent.putExtra("bookPrice", selectedBook.getPrice());
                intent.putExtra("bookImageRes", selectedBook.getImageResource());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted
                } else {
                    Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private List<Book> generateBookList(String category) {
        List<Book> list = new ArrayList<>();
        // Example data with short descriptions
        switch (category) {
            case "Fiction":
                list.add(new Book(1,"Book A", 19.99, R.drawable.fiction," Short description of Book A"));
                list.add(new Book(2,"Book B", 24.99, R.drawable.fiction1," Short description of Book b"));
                list.add(new Book(3,"Book C", 24.99, R.drawable.fiction2," Short description of Book A"));
                list.add(new Book(4,"Book D", 24.99, R.drawable.fiction3," Short description of Book A"));
                list.add(new Book(5,"Book E", 24.99, R.drawable.fiction4," Short description of Book A"));
                list.add(new Book(6,"Book F", 24.99, R.drawable.fiction5," Short description of Book A"));
                list.add(new Book(7,"Book G", 24.99, R.drawable.fiction6," Short description of Book A"));
                list.add(new Book(8,"Book H", 24.99, R.drawable.fiction7," Short description of Book A"));
                list.add(new Book(9,"Book I", 24.99, R.drawable.fiction3," Short description of Book A"));
                list.add(new Book(10,"Book J", 24.99, R.drawable.fiction1," Short description of Book A"));
                list.add(new Book(11,"Book K", 24.99, R.drawable.fiction5," Short description of Book A"));
                break;
            case "Non-Fiction":
                list.add(new Book(12,"Book L", 14.99, R.drawable.non_fiction," Short description of Book A"));
                list.add(new Book(13,"Book M", 17.99, R.drawable.nonfic1," Short description of Book A"));
                list.add(new Book(14,"Book N", 24.99, R.drawable.nonfic2," Short description of Book A"));
                list.add(new Book(15,"Book O", 24.99, R.drawable.nonfic3," Short description of Book A"));
                list.add(new Book(16,"Book P", 24.99, R.drawable.nonfic4," Short description of Book A"));
                list.add(new Book(17,"Book Q", 24.99, R.drawable.nonfic5," Short description of Book A"));
                list.add(new Book(18,"Book R", 24.99, R.drawable.nonfig6," Short description of Book A"));
                list.add(new Book(19,"Book S", 24.99, R.drawable.nonfig7," Short description of Book A"));
                list.add(new Book(20,"Book T", 24.99, R.drawable.nonfic8," Short description of Book A"));
                list.add(new Book(21,"Book U", 24.99, R.drawable.nonfic9," Short description of Book A"));
                list.add(new Book(22,"Book V", 24.99, R.drawable.nonfic10," Short description of Book A"));
                break;
            case "Science":
                list.add(new Book(23,"Book W", 14.99, R.drawable.science," Short description of Book A"));
                list.add(new Book(24,"Book X", 17.99, R.drawable.science1," Short description of Book A"));
                list.add(new Book(25,"Book Y", 24.99, R.drawable.science2," Short description of Book A"));
                list.add(new Book(26,"Book Z", 24.99, R.drawable.science3," Short description of Book A"));
                list.add(new Book(27,"Book A1", 24.99, R.drawable.science4," Short description of Book A"));
                list.add(new Book(28,"Book B1", 24.99, R.drawable.science5," Short description of Book A"));
                list.add(new Book(29,"Book C1", 24.99, R.drawable.science6," Short description of Book A"));
                list.add(new Book(30,"Book D1", 24.99, R.drawable.science7," Short description of Book A"));
                list.add(new Book(31,"Book E1", 24.99, R.drawable.science8," Short description of Book A"));
                list.add(new Book(32,"Book F1", 24.99, R.drawable.science9," Short description of Book A"));
                list.add(new Book(33,"Book G1", 24.99, R.drawable.science10," Short description of Book A"));
                break;
            case "Technology":
                list.add(new Book(34,"Book H1", 14.99, R.drawable.tech1," Short description of Book A"));
                list.add(new Book(35,"Book I1", 17.99, R.drawable.tech2," Short description of Book A"));
                list.add(new Book(36,"Book J1", 24.99, R.drawable.education1," Short description of Book A"));
                list.add(new Book(37,"Book K1", 24.99, R.drawable.education2," Short description of Book A"));
                list.add(new Book(38,"Book L1", 24.99, R.drawable.education3," Short description of Book A"));
                list.add(new Book(39,"Book M1", 24.99, R.drawable.education4," Short description of Book A"));
                list.add(new Book(40,"Book N1", 24.99, R.drawable.education5," Short description of Book A"));
                list.add(new Book(41,"Book O1", 24.99, R.drawable.education6," Short description of Book A"));
                list.add(new Book(42,"Book P1", 24.99, R.drawable.education7," Short description of Book A"));
                list.add(new Book(43,"Book Q1", 24.99, R.drawable.education8," Short description of Book A"));
                break;
            case "History":
                list.add(new Book(44,"Book R1", 14.99, R.drawable.history," Short description of Book A"));
                list.add(new Book(45,"Book S1", 17.99, R.drawable.history1," Short description of Book A"));
                list.add(new Book(46,"Book T1", 24.99, R.drawable.history2," Short description of Book A"));
                list.add(new Book(47,"Book U1", 24.99, R.drawable.history3," Short description of Book A"));
                list.add(new Book(48,"Book V1", 24.99, R.drawable.history4," Short description of Book A"));
                list.add(new Book(49,"Book W1", 24.99, R.drawable.history5," Short description of Book A"));
                list.add(new Book(50,"Book X1", 24.99, R.drawable.history6," Short description of Book A"));
                list.add(new Book(51,"Book Y1", 24.99, R.drawable.history7," Short description of Book A"));
                list.add(new Book(52,"Book Z1", 24.99, R.drawable.history2," Short description of Book A"));
                list.add(new Book(53,"Book A2", 24.99, R.drawable.history1," Short description of Book A"));
                list.add(new Book(54,"Book B2", 24.99, R.drawable.history4," Short description of Book A"));
                break;
            case "Biography":
                list.add(new Book(55,"Book C2", 14.99, R.drawable.biography1," Short description of Book A"));
                list.add(new Book(56,"Book D2", 17.99, R.drawable.biography2," Short description of Book A"));
                list.add(new Book(57,"Book E2", 24.99, R.drawable.biography3," Short description of Book A"));
                list.add(new Book(58,"Book F2", 24.99, R.drawable.biography4," Short description of Book A"));
                list.add(new Book(59,"Book G2", 24.99, R.drawable.biography5," Short description of Book A"));
                list.add(new Book(60,"Book H2", 24.99, R.drawable.biography6," Short description of Book A"));
                list.add(new Book(61,"Book I2", 24.99, R.drawable.biography3," Short description of Book A"));
                list.add(new Book(62,"Book J2", 24.99, R.drawable.biography2," Short description of Book A"));
                list.add(new Book(63,"Book K2", 24.99, R.drawable.biography5," Short description of Book A"));
                list.add(new Book(64,"Book L2", 24.99, R.drawable.biography4," Short description of Book A"));
                break;
        }
        return list;

    }
}
