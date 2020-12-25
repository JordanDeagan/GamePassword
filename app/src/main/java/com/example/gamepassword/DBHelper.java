package com.example.gamepassword;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "Logins.db";
    private static final String USER_TABLE = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_SALT = "salt";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + USER_TABLE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT," +
                KEY_PASS + " BLOB," +
                KEY_SALT + " BLOB)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    //Adding a new User
    void addUser(String username, ArrayList<String> passGame) {
        SQLiteDatabase db = this.getWritableDatabase(); //get the database
        String password = convertGame(passGame);        //convert the passed game states to a String
        ContentValues values = new ContentValues();     //Content values to store
        SecureRandom random = new SecureRandom();       //random to use in creating a salt
        byte[] salt = new byte[16];                     //get the salt
        random.nextBytes(salt);                         //random it for the new user
        values.put(KEY_NAME, username);                 //store username
        values.put(KEY_PASS, hashed(password, salt));   //create and store the hashed password
        values.put(KEY_SALT, salt);                     //add the salt for checking password attepts
        db.insert(USER_TABLE,null, values);             //insert into the db
        db.close();
    }

    private String convertGame(ArrayList<String> GameList){
        StringBuilder password = new StringBuilder();
        for (String game: GameList){   //run through every game state stored in the list, min 1
            password.append(game);
        }
        return password.toString();
    }

    boolean usernameExists(String username) {
        //make query that returns all instances of a username, should only return 1 or 0 results
        String selectQuery = "SELECT * FROM " + USER_TABLE +
                " WHERE " + KEY_NAME + " = " + '"' + username + '"';

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {         //If there is a result, the user exists
            cursor.close();
            return true;
        } else {                            //If there are no results, username hasn't been used
            cursor.close();
            return false;
        }
    }

    boolean logsIn(String username, ArrayList<String> passGame) {
        SQLiteDatabase db = this.getReadableDatabase();
        String password = convertGame(passGame);                //convert entered games to string
        String selectQuery = "SELECT * FROM " + USER_TABLE +
                " WHERE " + KEY_NAME + " = " + '"' + username + '"';

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {                             //grab the user if they exist
            byte[] test = hashed(password, cursor.getBlob(3));  //create hash using stored salt
            if (Arrays.equals(test, cursor.getBlob(2))) {       //compare to stored hash
                cursor.close();
                return true;
            }
            else{
                Log.d("db","password failed");
                cursor.close();
                return false;
            }
        }
        Log.d("db","username failed");
        cursor.close();
        return false;                                           //if no user, don't log in
    }

    byte[] hashed(String password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");   //get the key factory
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = new byte[0];  //start hash
        try {
            hash = factory.generateSecret(spec).getEncoded();   //use factory generated key to create hash
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
