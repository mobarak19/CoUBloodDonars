package com.cse.cou.mobarak.coublooddonars;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserRegistration extends AppCompatActivity {
    DonarClass donarClass;
    Spinner spinner;
    String blood_groups[];
    ArrayAdapter<String> arrayAdapter;
    Button register_btn;
    EditText name, email, password, age, phone;
    String str_name, str_email, str_password, str_age, str_phone, str_blood;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        mAuth = FirebaseAuth.getInstance();

        register_btn = (Button) findViewById(R.id.register_btn);

        name = (EditText) findViewById(R.id.input_name);
        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        phone = (EditText) findViewById(R.id.input_phone);
        age = (EditText) findViewById(R.id.input_age);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });













        /*Spinner */
        spinner = (Spinner) findViewById(R.id.blood_spinner);
        blood_groups = getResources().getStringArray(R.array.blood_group);

        arrayAdapter = new ArrayAdapter<String>(UserRegistration.this, android.R.layout.simple_spinner_item, blood_groups);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                str_blood = blood_groups[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void register() {
        str_name = name.getText().toString();
        str_email = email.getText().toString();
        str_phone = phone.getText().toString();
        str_password = password.getText().toString();
        str_age = age.getText().toString();

        if (!str_blood.isEmpty() && !str_phone.isEmpty() && !str_password.isEmpty() && !str_name.isEmpty() && !str_age.isEmpty() && !str_email.isEmpty()) {


            mAuth.createUserWithEmailAndPassword(str_email, str_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            String user_id = mAuth.getCurrentUser().getUid();

                            donarClass = new DonarClass(str_name, str_email, str_age, str_phone, str_password, str_blood);
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference().child("BloodDonars").child(user_id);
                            myRef.child("name").setValue(donarClass.getName());
                            myRef.child("age").setValue(donarClass.getAge());
                            myRef.child("phone").setValue(donarClass.getPhone());
                            myRef.child("blood").setValue(str_blood);
                            myRef.child("last_donation").setValue("");

                            Intent intent=new Intent(UserRegistration.this, BloodDonars.class);
                            startActivity(intent);

                        }


                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "Please, enter all the fields", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}
