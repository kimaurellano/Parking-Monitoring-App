package com.cdtekk.parkingmonitoringapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ImageView sensor1;
    private ImageView sensor2;
    private ImageView sensor3;
    private ImageView sensor4;
    private ImageView sensor5;
    private ImageView btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensor1 = findViewById(R.id.sensor1);
        sensor2 = findViewById(R.id.sensor2);
        sensor3 = findViewById(R.id.sensor3);
        sensor4 = findViewById(R.id.sensor4);
        sensor5 = findViewById(R.id.sensor5);
        btnExit = findViewById(R.id.btnExit);
        mDatabase = FirebaseDatabase.getInstance().getReference("/sensor");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer sensor1State = snapshot.child("slot_1").getValue(Integer.class);
                sensor1.setImageDrawable(sensor1State > 0 ?
                        getDrawable(R.drawable.ic_occupied) : getDrawable(R.drawable.ic_unoccupied));
                Integer sensor2State = snapshot.child("slot_2").getValue(Integer.class);
                sensor2.setImageDrawable(sensor2State > 0 ?
                        getDrawable(R.drawable.ic_occupied) : getDrawable(R.drawable.ic_unoccupied));
                Integer sensor3State = snapshot.child("slot_3").getValue(Integer.class);
                sensor3.setImageDrawable(sensor3State > 0 ?
                        getDrawable(R.drawable.ic_occupied) : getDrawable(R.drawable.ic_unoccupied));
                Integer sensor4State = snapshot.child("slot_4").getValue(Integer.class);
                sensor4.setImageDrawable(sensor4State > 0 ?
                        getDrawable(R.drawable.ic_occupied) : getDrawable(R.drawable.ic_unoccupied));
                Integer sensor5State = snapshot.child("slot_5").getValue(Integer.class);
                sensor5.setImageDrawable(sensor5State > 0 ?
                        getDrawable(R.drawable.ic_occupied) : getDrawable(R.drawable.ic_unoccupied));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("ERROR", "Failed to read value.", error.toException());
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }
}
