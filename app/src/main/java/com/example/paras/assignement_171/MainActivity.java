package com.example.paras.assignement_171;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button play, stop;

    // onCreate method.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing the play and stop button by referencing to their id in the layout of main activity.
        play = (Button)findViewById(R.id.playBtn);
        stop = (Button)findViewById(R.id.stopBtn);
        // setting the on click listener to the play and stop button.
        play.setOnClickListener(this);
        stop.setOnClickListener(this);

    }

    // on click method called when the button is clicked.
    @Override
    public void onClick(View v) {

        // switch case to check which button is clicked out of the two buttons.
        switch (v.getId()){

            case R.id.playBtn:
                // intent fired to the custom service class to handle the music service.
                Intent playIntent = new Intent(this, MyServiceManager.class);
                // start service with the intent.
                startService(playIntent);
                break;

            case R.id.stopBtn:
                // intent fired to the custom service class to handle the music service.
                Intent stopIntent = new Intent(this, MyServiceManager.class);
                // stop service with the intent.
                stopService(stopIntent);
                break;

        }
    }
}
