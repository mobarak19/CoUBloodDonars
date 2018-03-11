package com.cse.cou.mobarak.coublooddonars;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.security.AccessController.getContext;

/**
 * Created by Mobarak on 4/24/2017.
 */

public class MyAccountFragment extends Fragment {

    TextView name,age,blood,last_donation,phone;
    Button update,delete_btn;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.my_account,container,false);
        mAuth=FirebaseAuth.getInstance();
        update= (Button) view.findViewById(R.id.update_button);

        progressDialog=new ProgressDialog(getContext());


        delete_btn= (Button) view.findViewById(R.id.delete_account);


        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext()).setTitle("Confirmation message").setMessage("Are you want  to delete your profile?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                progressDialog.setMessage("Deleting...");
                                progressDialog.show();
                                String users=mAuth.getCurrentUser().getUid();
                                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("BloodDonars").child(users);
                                Intent intent=new Intent(getContext(),MainActivity.class);
                                startActivity(intent);
                                databaseReference.removeValue();
                                mAuth.signOut();
                                progressDialog.dismiss();
                            }
                        }).setNegativeButton("No",null).show();

            }
        });

        name= (TextView) view.findViewById(R.id.show_name_txt);
        age= (TextView) view.findViewById(R.id.show_age_txt);
        blood= (TextView) view.findViewById(R.id.show_blood_txt);
        last_donation= (TextView) view.findViewById(R.id.show_last_donatin_txt);
        phone= (TextView) view.findViewById(R.id.show_phone_txt);


        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(getActivity(),MainActivity.class));
        }
        else{

            final String users=mAuth.getCurrentUser().getUid();
            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("BloodDonars").child(users);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot!=null) {
                        name.setText(dataSnapshot.child("name").getValue()+"");
                        age.setText(dataSnapshot.child("age").getValue()+"");
                        blood.setText(dataSnapshot.child("blood").getValue()+"");
                        phone.setText(dataSnapshot.child("phone").getValue()+"");
                        last_donation.setText(dataSnapshot.child("last_donation").getValue()+"");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(view.getContext(),UpdateAccount.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    intent.putExtra("name",name.getText());
                    intent.putExtra("age",age.getText());
                    intent.putExtra("phone",phone.getText());
                    intent.putExtra("blood",blood.getText());
                    intent.putExtra("last_donation",last_donation.getText());
                    intent.putExtra("user_id",users);

                    startActivity(intent);
                }
            });

        }





        return view;
    }
}
