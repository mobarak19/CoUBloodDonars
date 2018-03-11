package com.cse.cou.mobarak.coublooddonars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Developers extends AppCompatActivity {
    Intent intent;
    Button button;
    ImageView mobarak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
        mobarak= (ImageView) findViewById(R.id.mobarak_fd);


        button= (Button) findViewById(R.id.back_main);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Developers.this,MainActivity.class));
            }
        });
        intent=new Intent(Developers.this,DevelopersFb.class);
        mobarak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("url", "https://www.facebook.com/mobarak.hossen.73");

                startActivity(intent);
            }
        });


    }
}
