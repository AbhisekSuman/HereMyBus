package com.presidency.heremybus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.widget.ConstraintLayout;

//import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText eemail,epassword;                      //email and password
    Button elogin;                                  // Login Button
    TextView eregister;                             //Register Button
    TextView efpassword;                            // Forget Password


    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        LinearLayout constraintLayout = findViewById(R.id.Animation_layout);


        eemail = findViewById(R.id.email);
        epassword = findViewById(R.id.password);
        elogin = findViewById(R.id.button3);
        eregister = findViewById(R.id.register);
        efpassword = findViewById(R.id.fpassword);


        final SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);

        final String type = sharedPreferences.getString("Email","");
        if (type.isEmpty()){
            Toast.makeText(getApplicationContext(), "please Login", Toast.LENGTH_SHORT).show();

        }
        else {
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();

        }


        fauth = FirebaseAuth.getInstance();

//  ..................................        Goto Registration Process         ..............................

        eregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

//      +++++++++++++++++++++++++           Login       ++++++++++++++++++++++++++++
        elogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String email = eemail.getText().toString().trim();                              //.................**
                String password = epassword.getText().toString().trim();                        //...................**                 //.................**

                if(TextUtils.isEmpty(email))
                {
                    eemail.setError("Email is Required");
                    return;
                }

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
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!               Login Process             !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {


                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Email",email);
                            editor.putString("Password",password);
                            editor.commit();


                            Toast.makeText(getApplicationContext(), "Login SuccessFull", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            Intent inte = new Intent(getApplicationContext(),MainActivity.class);
                            inte.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(inte);
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

//      """"""""""""""""""""""""""""""""""""        Forget Password Process        """""""""""""""""""""""""""""""""""""""
        efpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = eemail.getText().toString();
                fauth.sendPasswordResetEmail(email)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(login.this, "Email sent", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

}
