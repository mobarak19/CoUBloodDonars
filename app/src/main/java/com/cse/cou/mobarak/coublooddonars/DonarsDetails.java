package com.cse.cou.mobarak.coublooddonars;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonarsDetails extends AppCompatActivity {

    TextView name, age, phone, blood, last_donation;
    Button back;
    String p;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donars_details);

        String key = getIntent().getStringExtra("post_key");
        name = (TextView) findViewById(R.id.single_donar_name);
        age = (TextView) findViewById(R.id.single_donar_age);
        phone = (TextView) findViewById(R.id.single_donar_phone);
        blood = (TextView) findViewById(R.id.single_donar_blood);
        last_donation = (TextView) findViewById(R.id.single_donar_Last_donation);

        back = (Button) findViewById(R.id.back_btn);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("BloodDonars").child(key);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                p = dataSnapshot.child("phone").getValue() + "";
                name.setText("Name : " + dataSnapshot.child("name").getValue() + "");
                age.setText("Age : " + dataSnapshot.child("age").getValue() + "");
                phone.setText("Phone : " + p);
                blood.setText("Blood : " + dataSnapshot.child("blood").getValue() + "");
                last_donation.setText("Last Donation : " + dataSnapshot.child("last_donation").getValue() + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonarsDetails.this,BloodDonars.class));

            }
        });


    }
}
