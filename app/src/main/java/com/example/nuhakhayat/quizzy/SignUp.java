package com.example.nuhakhayat.quizzy;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private EditText usernameET, emailET, passwordET, confPasswordET;
    Button signupBtn;
    Database database;
    String username, email, password, cPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        database = new Database(getApplicationContext());

        usernameET = (EditText) findViewById(R.id.usernameTxt);
        emailET = (EditText) findViewById(R.id.emailTxt);
        passwordET = (EditText) findViewById(R.id.passTxt);
        confPasswordET = (EditText) findViewById(R.id.confirmpassTxt);

        signupBtn = (Button) findViewById(R.id.signupBtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = usernameET.getText().toString();
                email = emailET.getText().toString();
                password = passwordET.getText().toString();
                cPassword = confPasswordET.getText().toString();

                if(username.isEmpty() || email.isEmpty()  || password.isEmpty() ) {
                    Toast.makeText(SignUp.this, "Fileds shouldn't be empty", Toast.LENGTH_LONG).show();
                }else{
                    if(checkPassword(password,cPassword)) {
                        if(password.length() >= 3) {
                            //Toast.makeText(SignUp.this, "match!", Toast.LENGTH_LONG).show();
                            if(database.checkUsername(username)) {
                                database.signup(username, email,password);
                                Toast.makeText(SignUp.this, "Account successfully created", Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(SignUp.this, "Username already exists!", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(SignUp.this, "Password length must be at least 3. ", Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(SignUp.this, "Passwords doesn't match!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    //check if the two passwords match
    private boolean checkPassword(String pass1, String pass2) {

        if(pass1.equals(pass2)){
            return true;
        }
        return false;
    }

}


