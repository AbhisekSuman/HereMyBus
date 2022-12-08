package com.presidency.heremybus;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Feedback extends AppCompatActivity {

    EditText ouremail, subject, message;
    Button send;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ouremail = findViewById(R.id.email);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message1);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ourSubject = subject.getText().toString();
                String OurMessage = message.getText().toString();
                String ourEmail = ouremail.getText().toString();
                String[] email_divide = ourEmail.split(",");

                Intent send = new Intent(Intent.ACTION_SEND);
                send.putExtra(Intent.EXTRA_EMAIL, email_divide);
                send.putExtra(Intent.EXTRA_SUBJECT, ourSubject);
                send.putExtra(Intent.EXTRA_TEXT, OurMessage);
                send.setType("message/rfc822");
                send.setPackage("com.google.android.gm");
                startActivity(send);

            }
        });

    }
}