package com.myandayush.zegocallingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceConfig;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceFragment;

public class ConfrenceActivity extends AppCompatActivity {

    TextView meetingIdText;
    ImageView shareBtn;
    String meetingId, userId, name;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confrence);


        meetingIdText = findViewById(R.id.meeting_if_textview);
        shareBtn = findViewById(R.id.sharebtn);

        meetingId = getIntent().getStringExtra("meeting_ID");
        name = getIntent().getStringExtra("name");
        userId = getIntent().getStringExtra("user_id");

        meetingIdText.setText("Meeting ID : " + meetingId);
        addFragment();

        shareBtn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "Join meeting in Zego Calling App \n Meeting ID : " + meetingId);
            startActivity(Intent.createChooser(intent, "Share via"));
        });
    }
    public void addFragment() {
        long appID = AppConstants.appId;
        String appSign = AppConstants.appSign;


        ZegoUIKitPrebuiltVideoConferenceConfig config = new ZegoUIKitPrebuiltVideoConferenceConfig();
        config.turnOnCameraWhenJoining = false;
        config.turnOnMicrophoneWhenJoining = true;
        ZegoUIKitPrebuiltVideoConferenceFragment fragment = ZegoUIKitPrebuiltVideoConferenceFragment.newInstance(appID, appSign, userId, name,meetingId,config);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow();
    }
}