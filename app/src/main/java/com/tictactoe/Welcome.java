package com.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Welcome extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        findViewById(R.id.button_start).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        if (clickedButton.getText().equals("Start Game")) {
            startGame();
        }
    }

    public void startGame() {
        TextView username = findViewById(R.id.username_field);
        String usernameString = username.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", usernameString);
        startActivity(intent);
    }

}