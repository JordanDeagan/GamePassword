package com.example.gamepassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Scrabble extends GameBase {

    Button submit, reset;
    EditText[] board;
    ScrabbleObject gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrabble);
        submit = findViewById(R.id.ScrabbleSubmit);
        reset = findViewById(R.id.ScrabbleReset);
        setValues();
        buildBoard();
        gameBoard = new ScrabbleObject(board);
        for(final EditText square:board){
            square.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(b){
                        square.setBackground(getDrawable(R.drawable.selected_border));
                    }
                    else {
                        square.setBackground(getDrawable(R.drawable.image_border));
                    }
                }
            });
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnObjectHash();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    private void buildBoard() {
        board = new EditText[64];
        board[0] = findViewById(R.id.A1);
        board[1] = findViewById(R.id.A2);
        board[2] = findViewById(R.id.A3);
        board[3] = findViewById(R.id.A4);
        board[4] = findViewById(R.id.A5);
        board[5] = findViewById(R.id.A6);
        board[6] = findViewById(R.id.A7);
        board[7] = findViewById(R.id.A8);
        board[8] = findViewById(R.id.B1);
        board[9] = findViewById(R.id.B2);
        board[10] = findViewById(R.id.B3);
        board[11] = findViewById(R.id.B4);
        board[12] = findViewById(R.id.B5);
        board[13] = findViewById(R.id.B6);
        board[14] = findViewById(R.id.B7);
        board[15] = findViewById(R.id.B8);
        board[16] = findViewById(R.id.C1);
        board[17] = findViewById(R.id.C2);
        board[18] = findViewById(R.id.C3);
        board[19] = findViewById(R.id.C4);
        board[20] = findViewById(R.id.C5);
        board[21] = findViewById(R.id.C6);
        board[22] = findViewById(R.id.C7);
        board[23] = findViewById(R.id.C8);
        board[24] = findViewById(R.id.D1);
        board[25] = findViewById(R.id.D2);
        board[26] = findViewById(R.id.D3);
        board[27] = findViewById(R.id.D4);
        board[28] = findViewById(R.id.D5);
        board[29] = findViewById(R.id.D6);
        board[30] = findViewById(R.id.D7);
        board[31] = findViewById(R.id.D8);
        board[32] = findViewById(R.id.E1);
        board[33] = findViewById(R.id.E2);
        board[34] = findViewById(R.id.E3);
        board[35] = findViewById(R.id.E4);
        board[36] = findViewById(R.id.E5);
        board[37] = findViewById(R.id.E6);
        board[38] = findViewById(R.id.E7);
        board[39] = findViewById(R.id.E8);
        board[40] = findViewById(R.id.F1);
        board[41] = findViewById(R.id.F2);
        board[42] = findViewById(R.id.F3);
        board[43] = findViewById(R.id.F4);
        board[44] = findViewById(R.id.F5);
        board[45] = findViewById(R.id.F6);
        board[46] = findViewById(R.id.F7);
        board[47] = findViewById(R.id.F8);
        board[48] = findViewById(R.id.G1);
        board[49] = findViewById(R.id.G2);
        board[50] = findViewById(R.id.G3);
        board[51] = findViewById(R.id.G4);
        board[52] = findViewById(R.id.G5);
        board[53] = findViewById(R.id.G6);
        board[54] = findViewById(R.id.G7);
        board[55] = findViewById(R.id.G8);
        board[56] = findViewById(R.id.H1);
        board[57] = findViewById(R.id.H2);
        board[58] = findViewById(R.id.H3);
        board[59] = findViewById(R.id.H4);
        board[60] = findViewById(R.id.H5);
        board[61] = findViewById(R.id.H6);
        board[62] = findViewById(R.id.H7);
        board[63] = findViewById(R.id.H8);
    }

    @Override
    protected void returnObjectHash() {
        if (!gameBoard.isEmpty()) {
            gameBoard.compileBoard();
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
