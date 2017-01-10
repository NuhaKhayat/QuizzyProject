package com.example.nuhakhayat.quizzy.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.R;
import com.example.nuhakhayat.quizzy.StudyRoom.StudyRoomActivity;

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
                                showToast("Account successfully created");
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

    //This method is used to show customized toast message
    public void showToast(String message){
        //Retrieve and inflate toast layout
        LayoutInflater inflater = getLayoutInflater();
        View toastView = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.toastLayout));

        //Lock screen when toast show, screen untouchable
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        //Set toast message in layout text view
        TextView toastMsg = (TextView)toastView.findViewById(R.id.textViewToast);
        toastMsg.setText(message);

        //Set toast place on screen, duration, view and show
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.FILL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastView);
        toast.show();
        thread.start();
    }

    //This thread is used to finish activity when toast disappear
    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                //Sleep thread for taost LENGHT_LONG
                Thread.sleep(3500);
                startActivity(new Intent(SignUp.this,Login.class));
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

}


