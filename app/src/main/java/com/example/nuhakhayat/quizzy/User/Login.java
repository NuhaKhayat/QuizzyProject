package com.example.nuhakhayat.quizzy.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.MainActivity;
import com.example.nuhakhayat.quizzy.R;
import com.example.nuhakhayat.quizzy.broadcastResever;

public class Login extends AppCompatActivity {

    EditText usernameET, passwordET;
    TextView forgotPass;
    Button signinBtn, signupBtn;
    String username, password;
    Database db;

    broadcastResever br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
		SharedPreferences sharedPreferences =
				getSharedPreferences("LoginPreferences",MODE_PRIVATE);

		String test = sharedPreferences.getString("userName",null);

		if (test != null){
			username = test;
			Intent intent = new Intent ();
			intent.setAction("com.example.nuhakhayat.quizzy");
			intent.putExtra("username", username);
			sendBroadcast(intent);

			Intent myintent = new Intent(Login.this,  MainActivity.class);
			startActivity(myintent);
			finish();
		}

        db = new Database(getApplicationContext());

        usernameET = (EditText) findViewById(R.id.usernET);
        passwordET = (EditText) findViewById(R.id.passET);


        forgotPass = (TextView) findViewById(R.id.forgotPassTV);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        signinBtn = (Button) findViewById(R.id.button);
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = usernameET.getText().toString();
                password = passwordET.getText().toString();

                if(username.isEmpty()|| password.isEmpty()) {
                    Toast.makeText(Login.this, "Fields shouldn't be empty.", Toast.LENGTH_LONG).show();
                }else {
                    if(db.login(username, password)) {
                        //Toast.makeText(Login.this, "logged in.", Toast.LENGTH_LONG).show();
                        //send username
						SharedPreferences sharedPreferences =
								getSharedPreferences("LoginPreferences",MODE_PRIVATE);
						SharedPreferences.Editor editor = sharedPreferences.edit();
						editor.putString("userName",username).commit();

                        Intent intent = new Intent ();
                        intent.setAction("com.example.nuhakhayat.quizzy");
                        intent.putExtra("username", username);
                        sendBroadcast(intent);

                        Intent myintent = new Intent(Login.this,  MainActivity.class);
                        startActivity(myintent);
                        finish();
                    }else {
                        Toast.makeText(Login.this, "Username & Password doesn't match or exists .", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        signupBtn = (Button) findViewById(R.id.signupBtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

    }
}


