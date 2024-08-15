package com.example.firstapp; // Replace with your actual package name

        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.View;

        import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first); // Set the layout for this activity

        // Use a Handler to post a delayed action to start SecondActivity after 3 seconds

    }

    public void explore(View view) {
        startActivity(new Intent(FirstActivity.this, SecondActivity.class));
    }
}










