package com.example.gamepassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Monopoly extends GameBase {

    Button submit, reset;
    FrameLayout[] board;
    Drawable[] pieces;
    MonopolyObject gameBoard;
    boolean PieceSelected;
    int SelectedPiece;
    String PassedPiece;
    ImageView[] selectablePieces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monopoly);
        gameBoard = new MonopolyObject();
        board = new FrameLayout[40];
        selectablePieces = new ImageView[8];
        pieces = new Drawable[8];
        setValues();
        buildBoard();
        for (int i = 0; i<40; i++){
            final Integer finalI = i;
            board[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(PieceSelected && !gameBoard.hasPiece(finalI)) {
                        ImageView temp = new ImageView(getApplicationContext());
                        temp.setImageDrawable(pieces[SelectedPiece]);
                        board[finalI].addView(temp);
                        switch (SelectedPiece){
                            case 0: PassedPiece = "Battleship"; break;
                            case 1: PassedPiece = "Car"; break;
                            case 2: PassedPiece = "Dog"; break;
                            case 3: PassedPiece = "Hat"; break;
                            case 4: PassedPiece = "Iron"; break;
                            case 5: PassedPiece = "Shoe"; break;
                            case 6: PassedPiece = "Thimble"; break;
                            case 7: PassedPiece = "Wheelbarrow"; break;
                        }
                        gameBoard.placePiece(finalI, PassedPiece);
                        SelectedPiece = -1;
                        PieceSelected = false;
                    }
                    return false;
                }
            });
        }
        for (int i = 0;i<8;i++){
            pieces[i] = selectablePieces[i].getDrawable();
            final int finalI = i;
            selectablePieces[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    PieceSelected = true;
                    SelectedPiece = finalI;
                    return false;
                }
            });
        }
        submit = findViewById(R.id.MonopolySubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnObjectHash();
            }
        });
        reset = findViewById(R.id.MonopolyReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        PieceSelected = false;
        SelectedPiece = -1;
    }

    private void buildBoard(){
        board = new FrameLayout[40];
        selectablePieces = new ImageView[8];
        pieces = new Drawable[8];
        board[0] = findViewById(R.id.Monopoly1);
        board[1] = findViewById(R.id.Monopoly2);
        board[2] = findViewById(R.id.Monopoly3);
        board[3] = findViewById(R.id.Monopoly4);
        board[4] = findViewById(R.id.Monopoly5);
        board[5] = findViewById(R.id.Monopoly6);
        board[6] = findViewById(R.id.Monopoly7);
        board[7] = findViewById(R.id.Monopoly8);
        board[8] = findViewById(R.id.Monopoly9);
        board[9] = findViewById(R.id.Monopoly10);
        board[10] = findViewById(R.id.Monopoly11);
        board[11] = findViewById(R.id.Monopoly12);
        board[12] = findViewById(R.id.Monopoly13);
        board[13] = findViewById(R.id.Monopoly14);
        board[14] = findViewById(R.id.Monopoly15);
        board[15] = findViewById(R.id.Monopoly16);
        board[16] = findViewById(R.id.Monopoly17);
        board[17] = findViewById(R.id.Monopoly18);
        board[18] = findViewById(R.id.Monopoly19);
        board[19] = findViewById(R.id.Monopoly20);
        board[20] = findViewById(R.id.Monopoly21);
        board[21] = findViewById(R.id.Monopoly22);
        board[22] = findViewById(R.id.Monopoly23);
        board[23] = findViewById(R.id.Monopoly24);
        board[24] = findViewById(R.id.Monopoly25);
        board[25] = findViewById(R.id.Monopoly26);
        board[26] = findViewById(R.id.Monopoly27);
        board[27] = findViewById(R.id.Monopoly28);
        board[28] = findViewById(R.id.Monopoly29);
        board[29] = findViewById(R.id.Monopoly30);
        board[30] = findViewById(R.id.Monopoly31);
        board[31] = findViewById(R.id.Monopoly32);
        board[32] = findViewById(R.id.Monopoly33);
        board[33] = findViewById(R.id.Monopoly34);
        board[34] = findViewById(R.id.Monopoly35);
        board[35] = findViewById(R.id.Monopoly36);
        board[36] = findViewById(R.id.Monopoly37);
        board[37] = findViewById(R.id.Monopoly38);
        board[38] = findViewById(R.id.Monopoly39);
        board[39] = findViewById(R.id.Monopoly40);

        selectablePieces[0] = findViewById(R.id.Battleship);
        selectablePieces[1] = findViewById(R.id.Car);
        selectablePieces[2] = findViewById(R.id.Dog);
        selectablePieces[3] = findViewById(R.id.Hat);
        selectablePieces[4] = findViewById(R.id.Iron);
        selectablePieces[5] = findViewById(R.id.Shoe);
        selectablePieces[6] = findViewById(R.id.Thimble);
        selectablePieces[7] = findViewById(R.id.Wheelbarrow);
        for (int i = 0;i<8;i++){
            pieces[i] = selectablePieces[i].getDrawable();
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
