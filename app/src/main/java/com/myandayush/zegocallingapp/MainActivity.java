package com.myandayush.zegocallingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    EditText meetingIDInput, nameInput;
    Button joinBtn, createBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meetingIDInput =findViewById(R.id.meetingId);
        nameInput = findViewById(R.id.nameInput);
        joinBtn = findViewById(R.id.joinBtn);
        createBtn = findViewById(R.id.createBtn);


        joinBtn.setOnClickListener(v -> {
            String meetingID = meetingIDInput.getText().toString();
            if (meetingID.length() != 10){
                meetingIDInput.setError("Invalid Meeting ID");
                meetingIDInput.requestFocus();
                return;
            }

            String name = nameInput.getText().toString();
            if (name.isEmpty()){
                nameInput.setError("Name is required to join the Meeting");
                nameInput.requestFocus();
                return;
            }
            startMeeting(meetingID, name);
        });

        createBtn.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            if (name.isEmpty()){
                nameInput.setError("Name is required to join the Meeting");
                nameInput.requestFocus();
                return;
            }
            startMeeting(getrandomMeetingId(), name);
        });
    }

    void startMeeting(String meetingID, String name){
        String userID = UUID.randomUUID().toString(); // this will provide us the random user id

        Intent intent = new Intent(MainActivity.this, ConfrenceActivity.class);
        intent.putExtra("meeting_ID", meetingID);
        intent.putExtra("name", name);
        intent.putExtra("user_id", userID);
        startActivity(intent);
    }
  String getrandomMeetingId(){
        StringBuilder id = new StringBuilder();
        while (id.length() != 10){
            int random = new Random().nextInt(10);  // from 0 - 10 it will return the random number
            id.append(random);
        }
        return id.toString();
  }
}