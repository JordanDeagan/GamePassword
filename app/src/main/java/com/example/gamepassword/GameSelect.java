package com.example.gamepassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GameSelect extends AppCompatActivity {
    public static DBHelper db;
    public ArrayList<String> games, previous;
    private int selectionState;
    private String username;
    LinearLayout chess, monopoly, clue, scrabble;
    Button sub,back,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);
        db = MainActivity.db;
        back = findViewById(R.id.back);
        sub = findViewById(R.id.submit);
        reset = findViewById(R.id.resetChoice);
        TextView header = findViewById(R.id.FirstHeader);
        chess = findViewById(R.id.Chess);
        monopoly = findViewById(R.id.Monopoly);
        clue =  findViewById(R.id.Clue);
        scrabble = findViewById(R.id.Scrabble);
        if (getIntent().getExtras().getBoolean("INITIAL")){
            games = new ArrayList<>();
        }
        else {
            games = getIntent().getStringArrayListExtra("GAMES");
        }
        username = getIntent().getStringExtra("USERNAME");
        selectionState = getIntent().getExtras().getInt("STATE");

        if(selectionState==2){
            previous=getIntent().getStringArrayListExtra("LAST");
            header.setText("Confirm Password");
            back.setText("Restart");
        }
        else if (selectionState == 1){
            header.setText("Make Password");
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectionState == 0 || selectionState == 1) {
                    alterBasic(selectionState);
                }
                else if(selectionState==2){
                    confirmPassword(previous);
                }
            }
        });

        chess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(Chess.class);
            }
        });
        monopoly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(Monopoly.class);
            }
        });
        clue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(Clue.class);
            }
        });
        scrabble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(Scrabble.class);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectionState == 0 || selectionState == 1) {
                    goBack();
                }
                else if(selectionState==2){
                    alterBasic(1);
                }
            }
        });
    }


    private void startGame(Class choice){
        if(selectionState==0 || selectionState == 1) {
            Intent intent = new Intent(getBaseContext(), choice);
            intent.putExtra("GAMES", games);
            intent.putExtra("USERNAME", username);
            intent.putExtra("STATE", selectionState);
            startActivity(intent);
        }
        else if (selectionState == 2){
            Intent intent = new Intent(getBaseContext(), choice);
            intent.putExtra("GAMES", games);
            intent.putExtra("USERNAME", username);
            intent.putExtra("STATE", selectionState);
            intent.putExtra("LAST",previous);
            startActivity(intent);
        }
    }

    private void submit(){
        if(!games.isEmpty()) {
            if (selectionState == 0) {
                if (db.logsIn(username, games)) {
                    Intent intent = new Intent(getBaseContext(), LoggedIn.class);
                    intent.putExtra("USERNAME", username);
                    startActivity(intent);
                }
            } else if (selectionState == 1) {
                confirmPassword(games);
                //Log.d("GameSelect","Confirm Password");
            } else if (selectionState == 2) {
                if (previous.equals(games)) {
                    db.addUser(username, games);
                    goBack();
                }
            }
        }
    }

    private void goBack(){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }

    private void confirmPassword(ArrayList<String> previous){
        Intent intent = new Intent(getBaseContext(), getClass());
        intent.putExtra("INITIAL", true);
        intent.putExtra("USERNAME", username);
        intent.putExtra("STATE", 2);
        intent.putExtra("LAST", previous);
        startActivity(intent);
    }

    private void alterBasic(int state){
        Intent intent = new Intent(getBaseContext(), getClass());
        intent.putExtra("INITIAL", true);
        intent.putExtra("USERNAME", username);
        intent.putExtra("STATE", state);
        startActivity(intent);
    }
}
