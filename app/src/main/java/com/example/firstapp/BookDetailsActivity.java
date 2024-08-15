package com.example.firstapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BookDetailsActivity extends AppCompatActivity {
    ImageView bookImageView;    TextView bookTitleTextView, bookPriceTextView, quantityTextView;
    EditText addressEditText, mobileNumberEditText, emailEditText;
    Button decreaseQuantityButton, increaseQuantityButton, orderButton, sendEmailButton, sendSmsButton;

    int quantity = 1; // Default quantity
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        // Initialize views
        bookImageView = findViewById(R.id.bookImageView);
        bookTitleTextView = findViewById(R.id.bookTitleTextView);
        bookPriceTextView = findViewById(R.id.bookPriceTextView);
        quantityTextView = findViewById(R.id.quantityTextView);
        decreaseQuantityButton = findViewById(R.id.decreaseQuantityButton);
        increaseQuantityButton = findViewById(R.id.increaseQuantityButton);
        addressEditText = findViewById(R.id.addressEditText);
        mobileNumberEditText = findViewById(R.id.mobileNumberEditText);
        emailEditText = findViewById(R.id.emailEditText);
        orderButton = findViewById(R.id.orderButton);
        sendEmailButton = findViewById(R.id.sendEmailButton);
        sendSmsButton = findViewById(R.id.sendSmsButton);

        // Extract book details from intent
        String bookTitle = getIntent().getStringExtra("bookTitle");
        double bookPrice = getIntent().getDoubleExtra("bookPrice", 0.0);
        int bookImageRes = getIntent().getIntExtra("bookImageRes", R.drawable.ic_launcher_foreground);

        // Set book details
        bookImageView.setImageResource(bookImageRes);
        bookTitleTextView.setText(bookTitle);
        bookPriceTextView.setText(String.format("Price: $%.2f", bookPrice));
        quantityTextView.setText(String.valueOf(quantity));

        // Decrease quantity button click listener
        decreaseQuantityButton.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                quantityTextView.setText(String.valueOf(quantity));
            }
        });

        // Increase quantity button click listener
        increaseQuantityButton.setOnClickListener(v -> {
            quantity++;
            quantityTextView.setText(String.valueOf(quantity));
        });

        // Order button click listener
        orderButton.setOnClickListener(v -> {
            String address = addressEditText.getText().toString().trim();
            String phoneNumber = mobileNumberEditText.getText().toString().trim();

            if (!address.isEmpty() && !phoneNumber.isEmpty()) {
                storeOrderInFirestore(bookTitle, quantity, address, phoneNumber);
                navigateToOrderConfirmation(bookTitle, quantity, address);
            } else {
                Toast.makeText(BookDetailsActivity.this, "Please enter your address and mobile number", Toast.LENGTH_SHORT).show();
            }
        });

        // Send SMS button click listener
        sendSmsButton.setOnClickListener(v -> {
            String address = addressEditText.getText().toString().trim();
            String phoneNumber = mobileNumberEditText.getText().toString().trim();

            if (!address.isEmpty() && !phoneNumber.isEmpty()) {
                sendSMS(phoneNumber, bookTitle, quantity, address);
            } else {
                Toast.makeText(BookDetailsActivity.this, "Please enter your address and mobile number", Toast.LENGTH_SHORT).show();
            }
        });

        // Email button click listener
        sendEmailButton.setOnClickListener(v -> {
            String address = addressEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();

            if (!address.isEmpty() && !email.isEmpty()) {
                sendEmail(email, bookTitle, quantity, address);
            } else {
                Toast.makeText(BookDetailsActivity.this, "Please enter your address and email", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to send SMS
    private void sendSMS(String phoneNumber, String bookTitle, int quantity, String address) {
        String message = String.format("Order placed successfully for %s (quantity %d) to %s", bookTitle, quantity, address);
        Uri smsUri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsUri);
        intent.putExtra("sms_body", message);

        try {
            startActivity(intent);
            Toast.makeText(this, "SMS intent started for " + phoneNumber, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to start SMS intent", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void navigateToOrderConfirmation(String bookTitle, int quantity, String address) {
        String message = String.format("Order placed successfully for %s (quantity %d) to %s", bookTitle, quantity, address);

        // Navigate to OrderConfirmationActivity
        Intent intent = new Intent(BookDetailsActivity.this, OrderConfirmationActivity.class);
        intent.putExtra("message", message);
        startActivity(intent);
    }

    // Method to send Email
    private void sendEmail(String email, String bookTitle, int quantity, String address) {
        String subject = "Order Confirmation";
        String message = String.format("Order placed successfully for %s (quantity %d) to %s", bookTitle, quantity, address);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void storeOrderInFirestore(String bookTitle, int quantity, String address, String phoneNumber) {
        // Create a new order with a book title, quantity, address, and phone number
        Map<String, Object> order = new HashMap<>();
        order.put("bookTitle", bookTitle);
        order.put("quantity", quantity);
        order.put("address", address);
        order.put("phoneNumber", phoneNumber);
        order.put("timestamp", System.currentTimeMillis());

        // Add a new document with a generated ID
        db.collection("orders")
                .add(order)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(BookDetailsActivity.this, "Order stored in Firestore", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(BookDetailsActivity.this, "Error storing order in Firestore", Toast.LENGTH_SHORT).show();
                });
    }
}
