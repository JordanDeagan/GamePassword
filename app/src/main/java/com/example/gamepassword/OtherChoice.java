package com.example.gamepassword;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class OtherChoice extends AppCompatActivity {

    public static final int ClueBoardActivityRequestCode = 998;

    private String roomName, weaponName, suspectName;
    private int roomImage, weaponImage, suspectImage;
    Button back, submit;
    ImageView axe, candle, gun, knife, pipe, poison, rope, wrench,
            grey, green, mustard, plum, peacock, rose, scarlet, white, previousSus, previousWep;
    boolean suspectSelected, weaponSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_choice);
        back = findViewById(R.id.ChoiceBack);
        submit = findViewById(R.id.ChoiceSubmit);
        axe = findViewById(R.id.Axe);
        candle = findViewById(R.id.Candle);
        gun = findViewById(R.id.Gun);
        knife = findViewById(R.id.Knife);
        pipe = findViewById(R.id.Pipe);
        poison = findViewById(R.id.Poison);
        rope = findViewById(R.id.Rope);
        wrench = findViewById(R.id.Wrench);
        grey = findViewById(R.id.Grey);
        green = findViewById(R.id.Green);
        mustard = findViewById(R.id.Mustard);
        plum = findViewById(R.id.Plum);
        peacock = findViewById(R.id.Peacock);
        rose = findViewById(R.id.Rose);
        scarlet = findViewById(R.id.Scarlet);
        white = findViewById(R.id.White);
        axe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectWeapon(R.mipmap.axe_foreground, "Axe");
                previousWep = axe;
                previousWep.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        candle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectWeapon(R.mipmap.candle_foreground, "Candle");
                previousWep = candle;
                previousWep.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        gun.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectWeapon(R.mipmap.gun_foreground, "Gun");
                previousWep = gun;
                previousWep.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        knife.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectWeapon(R.mipmap.knife_foreground, "Knife");
                previousWep = knife;
                previousWep.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        pipe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectWeapon(R.mipmap.pipe_foreground, "Pipe");
                previousWep = pipe;
                previousWep.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        poison.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectWeapon(R.mipmap.poison_foreground, "Poison");
                previousWep = poison;
                previousWep.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        rope.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectWeapon(R.mipmap.rope_foreground, "Rope");
                previousWep = rope;
                previousWep.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        wrench.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectWeapon(R.mipmap.wrench_foreground, "Wrench");
                previousWep = wrench;
                previousWep.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        grey.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectSuspect(R.mipmap.gray_foreground, "Sergeant Gray");
                previousSus = grey;
                previousSus.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        green.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectSuspect(R.mipmap.green_foreground, "Mr Green");
                previousSus = green;
                previousSus.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        mustard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectSuspect(R.mipmap.mustard_foreground, "Colonel Mustard");
                previousSus = mustard;
                previousSus.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        plum.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectSuspect(R.mipmap.plum_foreground, "Professor Plum");
                previousSus = plum;
                previousSus.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        peacock.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectSuspect(R.mipmap.peacock_foreground, "Mrs Peacock");
                previousSus = peacock;
                previousSus.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        rose.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectSuspect(R.mipmap.rose_foreground, "Madame Rose");
                previousSus = rose;
                previousSus.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        scarlet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectSuspect(R.mipmap.scarlet_foreground, "Miss Scarlet");
                previousSus = scarlet;
                previousSus.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        white.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                selectSuspect(R.mipmap.white_foreground, "Mrs White");
                previousSus = white;
                previousSus.setForeground(getDrawable(R.drawable.image_border));
                return false;
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(weaponSelected && suspectSelected){
                    createRumor();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRoom();
            }
        });
        weaponSelected = false;
        suspectSelected = false;
        getRoom();
    }

    private void selectWeapon(int val, String name){
        weaponImage = val;
        weaponName = name;
        if(!weaponSelected){
            weaponSelected = true;
        } else {
            previousWep.setForeground(null);
        }
    }

    private void selectSuspect(int val, String name){
        suspectImage = val;
        suspectName = name;
        if(!suspectSelected){
            suspectSelected = true;
        } else {
            previousSus.setForeground(null);
        }
    }

    void createRumor(){
        Intent output = new Intent();
        output.putExtra(Clue.KEY_ROOM_I, roomImage);
        output.putExtra(Clue.KEY_ROOM_N, roomName);
        output.putExtra(Clue.KEY_SUSPECT_I, suspectImage);
        output.putExtra(Clue.KEY_SUSPECT_N, suspectName);
        output.putExtra(Clue.KEY_WEAPON_I, weaponImage);
        output.putExtra(Clue.KEY_WEAPON_N, weaponName);
        setResult(RESULT_OK, output);
        finish();
    }

    void getRoom(){
        startActivityForResult(new Intent(this, ClueBoard.class), ClueBoardActivityRequestCode);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ClueBoardActivityRequestCode && resultCode == RESULT_OK && data != null) {
            roomImage = data.getExtras().getInt(Clue.KEY_ROOM_I);
            roomName = data.getStringExtra(Clue.KEY_ROOM_N);
            weaponSelected = false;
            suspectSelected = false;
            previousWep = null;
            previousSus = null;
            axe.setForeground(null);
            candle.setForeground(null);
            gun.setForeground(null);
            knife.setForeground(null);
            pipe.setForeground(null);
            poison.setForeground(null);
            rope.setForeground(null);
            wrench.setForeground(null);
            grey.setForeground(null);
            green.setForeground(null);
            mustard.setForeground(null);
            plum.setForeground(null);
            peacock.setForeground(null);
            rose.setForeground(null);
            scarlet.setForeground(null);
            white.setForeground(null);
        } else if(requestCode == ClueBoardActivityRequestCode && resultCode == RESULT_CANCELED){
            setResult(RESULT_CANCELED, new Intent());

            finish();
        }
    }
}
