package com.presidency.heremybus;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DriverLogin extends AppCompatActivity {


    EditText epassword;
    Button elogin;

    String correctpassword = "123654";
    String correctpassword1 = "123456";
    String correctpassword2 = "654321";


    Boolean isvalid = false;
    private int counter = 5;

    Boolean isvalid1 = false;
    private int counter1 = 5;


    Boolean isvalid2 = false;
    private int counter2 = 5;



    FirebaseAuth fauth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        epassword = findViewById(R.id.password);
        elogin = findViewById(R.id.button3);


        elogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                String password = epassword.getText().toString().trim();


                if(TextUtils.isEmpty(password))
                {
                    epassword.setError("Password is Required");
                    return;
                }

                if(password.length() < 6)
                {
                    epassword.setError("Password must be >= 6 character");
                    return;
                }

//                Driver Login
                isvalid = validate(password);

                if (!isvalid) {
                    counter--;
                    Toast.makeText(DriverLogin.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                    if (counter == 0) {
                        elogin.setEnabled(false);
                    }

                }
                else {
                    Toast.makeText(DriverLogin.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Driver1Profile.class);
                    startActivity(intent);
                }
//              Driver2 login

                isvalid1 = validate1(password);

                if (!isvalid2) {
                    counter1--;
                    Toast.makeText(DriverLogin.this, ".....", Toast.LENGTH_SHORT).show();
                    if (counter1 == 0) {
                        elogin.setEnabled(false);
                    }

                }
                else {
                    Toast.makeText(DriverLogin.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(getApplicationContext(),Driver2Profile.class);
                    startActivity(intent1);
                }


                //              Driver3 login

                isvalid2 = validate2(password);

                if (!isvalid2) {
                    counter2--;
                    Toast.makeText(DriverLogin.this, ".....", Toast.LENGTH_SHORT).show();
                    if (counter2 == 0) {
                        elogin.setEnabled(false);
                    }

                }
                else {
                    Toast.makeText(DriverLogin.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Driver3Profile.class);
                    startActivity(intent);
                }

            }
        });

    }

    private Boolean validate2(String password) {
        if (password.equals(correctpassword2)) {
            return  true;

        }
        return  false;


    }

    private Boolean validate1(String password) {


        if ( password.equals(correctpassword1)) {
            return  true;

        }
        return  false;

    }

    private Boolean validate(String password) {
        if (password.equals(correctpassword)) {
            return  true;

        }
        return  false;
    }


}