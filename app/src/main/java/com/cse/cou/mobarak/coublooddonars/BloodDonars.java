package com.cse.cou.mobarak.coublooddonars;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;


public class BloodDonars extends AppCompatActivity implements  ActionBar.TabListener{

    MyAdapter myAdapter;
    ViewPager viewPager;
    FirebaseAuth  mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donars);

        mAuth=FirebaseAuth.getInstance();

        myAdapter=new MyAdapter(getSupportFragmentManager());
        viewPager= (ViewPager) findViewById(R.id.activity_blood_donars_pager);
        viewPager.setAdapter(myAdapter);



    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.logout){
            mAuth.signOut();
            Intent intent=new Intent(BloodDonars.this,MainActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);

    }
}
