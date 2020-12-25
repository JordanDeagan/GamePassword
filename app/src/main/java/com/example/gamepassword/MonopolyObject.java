package com.example.gamepassword;

import java.util.HashMap;

public class MonopolyObject implements GameObject {
    HashMap<Integer, String> monopolyBoard;
    boolean empty;
    public MonopolyObject(){
        monopolyBoard = new HashMap<>();
        empty = true;
    }
    public void placePiece(Integer space, String piece){
        if(!monopolyBoard.containsKey(space)){
            monopolyBoard.put(space, piece);
            if(empty){
                empty = false;
            }
        }
    }

    public boolean hasPiece(Integer space){
        return monopolyBoard.containsKey(space);
    }

    @Override
    public String returnHash() {
        byte[] hashedBoard = MainActivity.db.hashed(monopolyBoard.toString(), "Monopoly".getBytes());
        return new String(hashedBoard);
    }

    @Override
    public boolean isEmpty(){
        return empty;
    }
}
