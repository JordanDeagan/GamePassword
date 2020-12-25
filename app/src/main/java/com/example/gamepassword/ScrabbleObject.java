package com.example.gamepassword;

import android.widget.EditText;

import java.util.HashMap;

public class ScrabbleObject implements GameObject {

    HashMap<Integer, String> Scrabbleboard;
    EditText[] board;

    public ScrabbleObject(EditText[] given){
        Scrabbleboard = new HashMap<>();
        board = given;
    }

    @Override
    public String returnHash() {
        byte[] hashedBoard = MainActivity.db.hashed(Scrabbleboard.toString(), "Scrabble".getBytes());
        return new String(hashedBoard);
    }

    @Override
    public boolean isEmpty() {
        for (EditText square:board){
            if(square.getText().length()>0){
                return false;
            }
        }
        return true;
    }

    public void compileBoard(){
        for (int i=0;i<board.length;i++){
            if(board[i].getText().length()>0){
                Scrabbleboard.put(i,board[i].getText().toString());
            }
        }
    }
}
