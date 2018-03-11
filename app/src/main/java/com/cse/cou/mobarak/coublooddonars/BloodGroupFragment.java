package com.cse.cou.mobarak.coublooddonars;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mobarak on 4/24/2017.
 */

public class BloodGroupFragment extends Fragment {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blood_group_fragment, container, false);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("BloodDonars");
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<ShowList, DonerViewHolder> recyclerAdapter = new FirebaseRecyclerAdapter<ShowList, DonerViewHolder>(
                ShowList.class,
                R.layout.blood_list,
                DonerViewHolder.class,

                databaseReference
        ) {
            @Override
            protected void populateViewHolder(DonerViewHolder viewHolder, ShowList model, int position) {

                final String post_key=getRef(position).getKey();
                viewHolder.setName(model.getName());
                viewHolder.setBlood(model.getBlood());
                viewHolder.setLastDonation(model.getLast_donation());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getContext(),DonarsDetails.class);
                        intent.putExtra("post_key",post_key);
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(recyclerAdapter);


    }


    public static class DonerViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public DonerViewHolder(View itemView) {

            super(itemView);

            mView = itemView;

        }

        public void setName(String s) {

            TextView name = (TextView) mView.findViewById(R.id.donar_name);
            name.setText("Name : "+s);
        }

        public void setBlood(String s) {

            TextView name = (TextView) mView.findViewById(R.id.donar_blood);
            name.setText("Blood : "+s);
        }

        public void setLastDonation(String s) {

            TextView name = (TextView) mView.findViewById(R.id.donar_last_donation);
            name.setText("Last Donation : "+s);
        }
    }

    public class Myadaper extends  ArrayAdapter<ShowList>{

        Activity context;
        ArrayList<ShowList> mylist;

        public Myadaper(Context context, ArrayList<ShowList> objects) {
            super(context, R.layout.blood_list, objects);
            this.context=(Activity) context;
            this.mylist=objects;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v=null;

            if(convertView==null){

                LayoutInflater inflater=context.getLayoutInflater();
                v=inflater.inflate(R.layout.blood_list,null);
                TextView name= (TextView) v.findViewById(R.id.donar_name);
                TextView blood= (TextView) v.findViewById(R.id.donar_blood);
                TextView last_donate= (TextView) v.findViewById(R.id.donar_last_donation);


                ShowList e=mylist.get(position);
                name.setText(e.getName());
                blood.setText(e.getBlood());
                last_donate.setText(e.getLast_donation());



            }else{

                v=convertView;
            }
            return super.getView(position, convertView, parent);


        }
    }

}
