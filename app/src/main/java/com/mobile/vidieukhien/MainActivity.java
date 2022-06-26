package com.mobile.vidieukhien;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button btnOpen;
    private Button btnClose;
    private TextView txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtview = findViewById(R.id.txtword);
        btnOpen = findViewById(R.id.button);
        btnClose = findViewById(R.id.button2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("work");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue().toString().equals("1")){
                    txtview.setText("Door Closed");
                }
                else {
                    txtview.setText("Door Open");
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Bug");
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.setValue(0.0);
                System.out.println("Closeed");
            }
        });

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.setValue(1.0);
                System.out.println("Opened");
            }
        });
    }
}