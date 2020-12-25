package com.example.gamepassword;

import java.util.HashMap;

public class ChessObject implements GameObject {
    HashMap<Integer, String> chessBoard;
    boolean empty;
    public ChessObject(){
        chessBoard = new HashMap<>();
        empty = true;
    }
    public void placePiece(Integer space, String piece){
        if(!chessBoard.containsKey(space)){
            chessBoard.put(space, piece);
            if(empty){
                empty = false;
            }
        }
    }

    public boolean hasPiece(Integer space){
        return chessBoard.containsKey(space);
    }

    @Override
    public String returnHash() {
        byte[] hashedBoard = MainActivity.db.hashed(chessBoard.toString(), "Chess".getBytes());
        return new String(hashedBoard);
    }

    @Override
    public boolean isEmpty(){
        return empty;
    }
}
