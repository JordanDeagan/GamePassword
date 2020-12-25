package com.example.gamepassword;

import android.graphics.drawable.Drawable;
import android.media.Image;

import androidx.annotation.NonNull;

import java.util.WeakHashMap;

public class Rumor {
    String Room, Sus, Weap;
    int RoomImage, Suspect, Weapon;
    public Rumor(String R, String S, String W, int r, int s, int w){
        Room = R;
        Sus = S;
        Weap = R;
        RoomImage = r;
        Suspect = s;
        Weapon = w;
    }

    @NonNull
    @Override
    public String toString() {
        String r = Room + Sus + Weap;
        return r;
    }

    public int getRoom(){
        return RoomImage;
    }

    public int getSuspect(){
        return Suspect;
    }

    public int getWeapon(){
        return Weapon;
    }
}
