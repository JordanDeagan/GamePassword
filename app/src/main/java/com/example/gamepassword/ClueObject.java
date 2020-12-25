package com.example.gamepassword;

import java.util.ArrayList;
import java.util.List;

public class ClueObject implements GameObject {

    List<Rumor> rumors;
    boolean empty;

    ClueObject(){
        rumors = new ArrayList<>();
        empty = true;
    }
    public void makeRumor(String R, String S, String W, int r, int s, int w){
        rumors.add(new Rumor(R,S,W,r,s,w));
        if(empty){
            empty = false;
        }
    }

    @Override
    public String returnHash() {
        byte[] hashedBoard = MainActivity.db.hashed(rumors.toString(), "Clue".getBytes());
        return new String(hashedBoard);
    }

    @Override
    public boolean isEmpty(){
        return empty;
    }

    public void undo(){
        if(rumors.size()>0) {
            rumors.remove(rumors.size() - 1);
            if(rumors.size()==0){
                empty = true;
            }
        }
    }
}
