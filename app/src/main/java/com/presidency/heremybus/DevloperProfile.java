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
    ImageView whatsapp2,facebook2,insta2,linkin3;
    ImageView whatsapp3,facebook3,insta3,linkedin3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devloper_profile);

        whatsapp1 = findViewById(R.id.whatapp1);
        facebook1 = findViewById(R.id.facebook1);
        insta1 = findViewById(R.id.insta1);
        linkin1 = findViewById(R.id.linkin1);

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
                String sApplink ="fb://page//237564710351658";
                String sPackage = "com.facebook.katana";
                String sWebLink = "https://www.facebook.com/Abhiseknayak00";
                openLink(sApplink,sPackage,sWebLink);
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


    }
}