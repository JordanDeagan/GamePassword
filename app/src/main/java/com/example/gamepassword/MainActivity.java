package com.example.gamepassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static DBHelper db;
    TextView User;
    Button signUp, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        User = findViewById(R.id.username);
        signUp = findViewById(R.id.sign_up);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = User.getText().toString();
                if (username.length()>0 && db.usernameExists(username)){
                    goToSelection(username, 0);
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = User.getText().toString();
                if (username.length()>0){
                    goToSelection(username, 1);
                }
            }
        });
    }

    private void goToSelection(String username, int state){
        Intent intent = new Intent(getBaseContext(),GameSelect.class);
        intent.putExtra("STATE", state);
        intent.putExtra("USERNAME", username);
        intent.putExtra("INITIAL",true);
        startActivity(intent);
    }
}
