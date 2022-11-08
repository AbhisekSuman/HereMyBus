package com.presidency.heremybus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
//import android.provider.ContactsContract;
//import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.Spinner;
//import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/*
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;
*/

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText efullname,eemail,epassword1,econfirm,ephoneno;
    Button eregisterbutton;

    FirebaseFirestore fstore;
    FirebaseAuth fauth;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference();

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        efullname = findViewById(R.id.fullname);            //Fullname of the User
        eemail = findViewById(R.id.email);                  //Email of the User
        epassword1 = findViewById(R.id.password1);          //Password of the User
        econfirm = findViewById(R.id.confirm);              //Confirm Password
        ephoneno = findViewById(R.id.PhnNumber);          //Phone number of the User
        eregisterbutton = findViewById(R.id.button3);       //Register Button

        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


        if(fauth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(),login.class));
            Toast.makeText(this, "Only One User can Access!", Toast.LENGTH_SHORT).show();
            finish();
        }

        eregisterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = eemail.getText().toString().trim();            //# # # # # #
                String password1 = epassword1.getText().toString().trim();              //*************
                final String fullname = efullname.getText().toString().trim();              //---------------Fill the Details
                final String confirm = econfirm.getText().toString().trim();             //*************
                String number = ephoneno.getText().toString();                      //# # # # # #


                if (TextUtils.isEmpty(email)) {
                    eemail.setError("Email is Required");                   //Email
                    return;
                }
                if (TextUtils.isEmpty(password1)) {
                    epassword1.setError("Password is Required");            //Password
                    return;
                }
                if (password1.length() < 6) {
                    epassword1.setError("Password must be >= 6 character");     //Password Length
                    return;
                }
                if(number.trim().isEmpty()){
                    Toast.makeText(Register.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();        //Mobile Number
                    return;
                }
                if(confirm.isEmpty()){
                    econfirm.setError("Enter Confirm Password");        //Password Confirmation
                    return;
                }
                if(password1.equals(confirm)){
                    econfirm.setError("enter Confirm Password same as password");
                    return;
                }

// ****************************************         Registration Process        **************************************************

                fauth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser fuser = fauth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "Register successful", Toast.LENGTH_SHORT).show();

                                    fstore.collection("User")
                                            .document(FirebaseAuth.getInstance().getUid())
                                            .set(new User(fullname,number,email));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"On failure: Email Not Sent" + e.getMessage());
                                }
                            });
                            Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(Register.this,"Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }
}