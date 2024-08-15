package com.example.firstapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        // Set confirmation text
        TextView confirmationTextView = findViewById(R.id.confirmationTextView);
        confirmationTextView.setText("Order Placed Successfully!");

        // Track Order Button
        Button trackOrderButton = findViewById(R.id.trackOrderButton);
        trackOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Google Maps or any other map application to show location
                openMapForTracking();
            }
        });

        // Logout Button
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform logout action
                logout();
            }
        });
    }

    private void openMapForTracking() {
        String latitude = "37.7749";
        String longitude = "-122.4194";
        String label = "My Location"; // Optional label for the location

        // Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + latitude + "," + longitude + "(" + label + ")");

        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        // Set package explicitly to handle the intent
        mapIntent.setPackage("com.google.android.apps.maps");

        // Verify that the intent will resolve to an activity
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            // If Google Maps app is not available, let user choose any map application
            Intent chooser = Intent.createChooser(mapIntent, "Open Location in Maps");
            if (chooser.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            } else {
                // If no map application is available, notify the user
                Toast.makeText(this, "No map application available", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void logout() {
        // Add logout logic here, for example:
        Intent intent = new Intent(OrderConfirmationActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
