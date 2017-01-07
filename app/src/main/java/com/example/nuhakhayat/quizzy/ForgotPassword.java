package com.example.nuhakhayat.quizzy;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    String username, email;
    EditText usernameET;
    Button resetBtn;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        db = new Database(getApplicationContext());

        usernameET = (EditText) findViewById(R.id.usernameET);

        resetBtn = (Button) findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameET.getText().toString();

                if(username.isEmpty()) {
                    Toast.makeText(ForgotPassword.this, "Please, enter a valid Username.", Toast.LENGTH_LONG).show();
                }else {
                    Cursor cursor = db.getEmail(username);

                    if(cursor.getCount()>0){
                        cursor.moveToFirst();
                        email = cursor.getString(0);
                        cursor.close();
                        Toast.makeText(ForgotPassword.this, email, Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(ForgotPassword.this, "Username doesn't exist.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void sendEmail(String email) {



    }
}


