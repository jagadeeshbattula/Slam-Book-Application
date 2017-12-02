package com.slapapp.slambook;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class UserHome extends AppCompatActivity {

    private Button mWrite;
    private Button mList;
    private Button mBirth;
    private Button mLogout;

    private  String emailFor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slam_home);
        Toast.makeText(UserHome.this,"Welcome to Home Activity",Toast.LENGTH_LONG);

        //SETTING ACTIVITIES TO BUTTONS
        mWrite = (Button) findViewById(R.id.write);
        mWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeMemory();
            }
        });
        mList = (Button)findViewById(R.id.list);
        mList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFriendList();
            }
        });
        mBirth = (Button) findViewById(R.id.bday);
        mBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                birthdayReminders();
            }
        });
        mLogout = (Button) findViewById(R.id.logout);
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    //ACTIVITY CALLING METHODS:-
    private void logout() {
        Toast.makeText(UserHome.this,"LOGOUT",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(UserHome.this,LoginActivity.class);
        startActivity(intent);
    }

    private void birthdayReminders() {
        Toast.makeText(UserHome.this,"Birthday Reminders",Toast.LENGTH_SHORT).show();
        Log.e("UserHome:","BDay");
        Intent intent = new Intent(UserHome.this,BirthDayList.class);
        startActivity(intent);
    }

    private void writeMemory() {
        Toast.makeText(UserHome.this,"Write A Memory",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(UserHome.this,WriteForm.class);
        startActivity(intent);
    }

    private void readFriendList(){
        Toast.makeText(UserHome.this, "Slam Book Friend List", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UserHome.this,FriendList.class);
        startActivity(intent);
    }
}
