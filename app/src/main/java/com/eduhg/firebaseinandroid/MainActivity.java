package com.eduhg.firebaseinandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView textViewCondition;
    Button mButtonSunny, mButtonFoggy;
    Button mButtonRDA, mButtonRecylerV;
    Firebase conditionRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        textViewCondition = (TextView) findViewById(R.id.textViewCondition);
        mButtonSunny = (Button) findViewById(R.id.mButtonSunny);
        mButtonFoggy = (Button) findViewById(R.id.mButtonFoggy);
        mButtonRDA = (Button) findViewById(R.id.mButtonRDA);
        mButtonRecylerV = (Button) findViewById(R.id.mButtonRecylerV);

        conditionRef = new Firebase("https://fir-inandroid.firebaseio.com/condition");

        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String condition = dataSnapshot.getValue(String.class);
                textViewCondition.setText(condition);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mButtonSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conditionRef.setValue("Sunny");
            }
        });

        mButtonFoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conditionRef.setValue("Foggy");
            }
        });

        mButtonRDA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RetrieveDataActivity.class);
                startActivity(intent);
            }
        });

        mButtonRecylerV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RecyclerViewActivity.class);
                startActivity(intent);
                //
            }
        });
    }
}
