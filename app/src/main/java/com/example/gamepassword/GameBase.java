package com.example.gamepassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public abstract class GameBase extends AppCompatActivity {


    protected GameObject gameBoard;
    protected String username;
    protected ArrayList<String> games, previous;
    protected int state;

    protected void reset(){
        finish();
        startActivity(getIntent());
    }

    protected void setValues(){
        username = getIntent().getStringExtra("USERNAME");
        games = getIntent().getStringArrayListExtra("GAMES");
        state = getIntent().getExtras().getInt("STATE");
        if(state == 2) {
            previous = getIntent().getStringArrayListExtra("LAST");
        }
    }

    protected void returnObjectHash(){
        if (state == 0 || state == 1) {
            Intent intent = new Intent(getBaseContext(), GameSelect.class);
            games.add(gameBoard.returnHash());
            intent.putExtra("USERNAME", username);
            intent.putExtra("GAMES", games);
            intent.putExtra("STATE", state);
            intent.putExtra("INITIAL", false);
            startActivity(intent);
        } else if (state == 2) {
            Intent intent = new Intent(getBaseContext(), GameSelect.class);
            games.add(gameBoard.returnHash());
            intent.putExtra("USERNAME", username);
            intent.putExtra("GAMES", games);
            intent.putExtra("STATE", state);
            intent.putExtra("INITIAL", false);
            intent.putExtra("LAST", previous);
            startActivity(intent);
        }
    }
}
