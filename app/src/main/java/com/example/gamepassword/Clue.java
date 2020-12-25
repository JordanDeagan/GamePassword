package com.example.gamepassword;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.Objects;

public class Clue extends GameBase {
    ListView mListView;
    ClueObject gameBoard;
    RumorArray rumorAdapt;
    Button startRumor, submit, undo, reset;
    public static final int OtherChoiceActivityRequestCode = 999;
    public static final String KEY_ROOM_I = "rImage";
    public static final String KEY_ROOM_N = "rName";
    public static final String KEY_SUSPECT_I = "sImage";
    public static final String KEY_SUSPECT_N = "sName";
    public static final String KEY_WEAPON_I = "wImage";
    public static final String KEY_WEAPON_N = "wName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clue);
        setValues();
        gameBoard = new ClueObject();
        mListView =(ListView) findViewById(R.id.RumorList);
        startRumor = findViewById(R.id.RumorButton);
        startRumor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRumor();
            }
        });
        submit = findViewById(R.id.clueSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnObjectHash();
            }
        });
        undo = findViewById(R.id.clueUndo);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameBoard.undo();
                setAdapter();
            }
        });
        reset = findViewById(R.id.clueReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        setAdapter();
    }

    private void setAdapter(){
        rumorAdapt = new RumorArray(this, R.layout.rumor, gameBoard.rumors);
        mListView.setAdapter(rumorAdapt);
    }

    private void startRumor(){
        startActivityForResult(new Intent(this, OtherChoice.class), OtherChoiceActivityRequestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OtherChoiceActivityRequestCode && resultCode == RESULT_OK && data != null) {
            gameBoard.makeRumor(data.getStringExtra(KEY_ROOM_N),data.getStringExtra(KEY_SUSPECT_N),data.getStringExtra(KEY_WEAPON_N),
                    data.getExtras().getInt(KEY_ROOM_I),data.getExtras().getInt(KEY_SUSPECT_I),data.getExtras().getInt(KEY_WEAPON_I));
            setAdapter();
        }
    }

    @Override
    protected void returnObjectHash() {
        if (!gameBoard.isEmpty()) {
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
}