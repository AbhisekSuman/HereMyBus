package com.presidency.heremybus;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DevloperProfile extends AppCompatActivity {
    ImageView whatsapp1,facebook1,insta1,linkin1;
    ImageView whatsapp2,facebook2,insta2,linkin2;
    ImageView whatsapp3,facebook3,insta3,linkedin3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devloper_profile);

        getSupportActionBar().setTitle("Developers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//                                                          Abhi
        whatsapp1 = findViewById(R.id.whatapp1);
        facebook1 = findViewById(R.id.facebook1);
        insta1 = findViewById(R.id.insta1);
        linkin1 = findViewById(R.id.linkin1);

        whatsapp2 = findViewById(R.id.whatapp2);
        facebook2 = findViewById(R.id.facebook2);
        insta2 = findViewById(R.id.insta2);
        linkin2 = findViewById(R.id.linkin2);

        whatsapp3 = findViewById(R.id.whatapp3);
        facebook3 = findViewById(R.id.facebook3);
        insta3 = findViewById(R.id.insta3);
        linkedin3 = findViewById(R.id.linkin3);


        whatsapp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sNumber1 = "+918917342632";

                Uri uri = Uri.parse(String.format(
                        "https://api.whatsapp.com/send?phone=%s",sNumber1
                ));

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        facebook1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sApplink ="https://www.facebook.com/Abhiseknayak00?mibextid=ZbWKwL";
                String sPackage = "com.facebook.katana";
                String sWebLink = "https://www.facebook.com/Abhiseknayak00?mibextid=ZbWKwL";
                openLink(sApplink,sPackage,sWebLink);
            }
        });

        insta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sApplink ="https://www.instagram.com/abhisek_suman";
                String sPackage = "com.instagram.android";
                openLink(sApplink,sPackage,sApplink);
            }
        });
        linkin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sApplink ="https://www.linkedin.com/in/abhisek-nayak-1858b6225";
                String sPackage = "com.linkedin.android";
                openLink(sApplink,sPackage,sApplink);
            }
        });

        whatsapp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sNumber2 = "+919692358823";

                Uri uri = Uri.parse(String.format(
                        "https://api.whatsapp.com/send?phone=%s",sNumber2
                ));

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        facebook2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sPackage2 = "com.facebook.katana";
                String sWebLink2 = "https://www.facebook.com/omprasad.panda.9?mibextid=ZbWKwL";
                String sApplink2 ="https://www.facebook.com/omprasad.panda.9?mibextid=ZbWKwL";
                openLink2(sApplink2,sPackage2,sWebLink2);
            }
        });

        insta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sApplink2 ="https://instagram.com/pandaomprasad";
                String sPackage2 = "com.instagram.android";
                openLink2(sApplink2,sPackage2,sApplink2);
            }
        });
        linkin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sApplink2 ="https://www.linkedin.com/in/om-prasad-panda-6a5a5921b";
                String sPackage2 = "com.linkedin.android";
                openLink2(sApplink2,sPackage2,sApplink2);
            }
        });


        whatsapp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sNumber3 = "+917043324554";

                Uri uri = Uri.parse(String.format(
                        "https://api.whatsapp.com/send?phone=%s",sNumber3
                ));

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        facebook3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sApplink3 ="https://www.facebook.com/harihar.maharana.790?mibextid=ZbWKwL";
                String sPackage3 = "com.facebook.katana";
                String sWebLink3 = "https://www.facebook.com/harihar.maharana.790?mibextid=ZbWKwL";
                openLink3(sApplink3,sPackage3,sWebLink3);
            }
        });

        insta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sApplink3 ="http://www.instagram.com/itz_harihar09";
                String sPackage3 = "com.instagram.android";
                openLink3(sApplink3,sPackage3,sApplink3);
            }
        });
        linkedin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sApplink3 ="https://www.linkedin.com/in/harihar-maharana-498051229";
                String sPackage3 = "com.linkedin.android";
                openLink3(sApplink3,sPackage3,sApplink3);
            }
        });
    }


    private void openLink(String sApplink, String sPackage, String sWebLink) {
        try {
            Uri uri = Uri.parse(sApplink);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException activityNotFoundException){
            Uri uri = Uri.parse(sWebLink);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }


// _________________________________________________________  End   _______________________________________

//                                                          Om


    }

    private void openLink2(String sApplink2, String sPackage2, String sWebLink2) {
        try {
            Uri uri = Uri.parse(sApplink2);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException activityNotFoundException){
            Uri uri = Uri.parse(sWebLink2);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }


//  ____________________________________________________        End2        ________________________________________________

//                                                              Harihar



    }

    private void openLink3(String sApplink3, String sPackage3, String sWebLink3) {
        try {
            Uri uri = Uri.parse(sApplink3);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException activityNotFoundException){
            Uri uri = Uri.parse(sWebLink3);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }




    }
}