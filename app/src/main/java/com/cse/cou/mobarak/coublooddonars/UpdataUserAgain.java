package com.cse.cou.mobarak.coublooddonars;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdataUserAgain extends AppCompatActivity {

    EditText name,age,last_donation,phone;
    Spinner spinner;
    FirebaseAuth mAuth;
    Button save;
    Calendar myCalendar;

    ProgressDialog progressDialog;
    String update_blood,blood_groups[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);

        progressDialog=new ProgressDialog(this);
        blood_groups=getResources().getStringArray(R.array.blood_group);
        save= (Button) findViewById(R.id.save);
        spinner= (Spinner) findViewById(R.id.update_blood_group);
        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.blood_group));
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                update_blood=blood_groups[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAuth=FirebaseAuth.getInstance();

        name= (EditText) findViewById(R.id.input_name);
        age= (EditText) findViewById(R.id.input_age);
        phone= (EditText) findViewById(R.id.input_phone);


        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(UpdataUserAgain.this,MainActivity.class));
        }
        else{
            final String users=mAuth.getCurrentUser().getUid();
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialog.setMessage("Saving...");
                    progressDialog.show();
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("BloodDonars").child(users);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().child("name").setValue(name.getText().toString()+"");
                            dataSnapshot.getRef().child("age").setValue(age.getText().toString()+"");
                            dataSnapshot.getRef().child("phone").setValue(phone.getText().toString()+"");
                            dataSnapshot.getRef().child("last_donation").setValue("");
                            dataSnapshot.getRef().child("blood").setValue(update_blood+"");
                            Intent intent=new Intent(UpdataUserAgain.this,BloodDonars.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                            startActivity(new Intent(UpdataUserAgain.this,MainActivity.class));

                        }
                    });
                }
            });        }




    }

}
