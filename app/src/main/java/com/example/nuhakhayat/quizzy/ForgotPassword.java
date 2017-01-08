package com.example.nuhakhayat.quizzy;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgotPassword extends AppCompatActivity {

    String username, email, RandomCode;
    EditText usernameET;
    Button resetBtn;
    Database db;

    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    String rec, subject, textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        db = new Database(getApplicationContext());
        context = this;

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

                        RandomCode = generateRandomCode();

                        //send email
                        rec = email;
                        subject = "Quizzy Reset Password";
                        textMessage = "Hi "+username+"!, \n This email was sent to you based on your request. \n" +
                                "Password reset code is: "+RandomCode+", please enter this code in the dialog provided in the Quizzy app.\n" +
                                "Thnaks!\nQuizzy Team \n\n IF YOU DID NOT REQUEST THIS TYPE OF EMAIL PLEASE IGNORE THIS EMAIL!!";

                        Properties props = new Properties();
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.smtp.socketFactory.port", "465");
                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.port", "465");

                        session = Session.getDefaultInstance(props, new Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication("QuizzyUOD@gmail.com", "Quizzy123");
                            }
                        });

                        pdialog = ProgressDialog.show(context, "", "Sending Mail...", true);

                        RetreiveFeedTask task = new RetreiveFeedTask();
                        task.execute();
                    }else {
                        Toast.makeText(ForgotPassword.this, "Username doesn't exist.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("QuizzyUOD@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject(subject);
                message.setContent(textMessage, "text/html; charset=utf-8");
                Transport.send(message);
            } catch(MessagingException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
        }
    }

    private String generateRandomCode() {
        Random rand = new Random();
        Character letters[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String Code;
        int rand1 = rand.nextInt(9);
        int rand2 = rand.nextInt(9);
        int rand3 = rand.nextInt(9);

        int randLetter1 = rand.nextInt(25);
        int randLetter2 = rand.nextInt(25);

        Character L1 = letters[randLetter1];
        Character L2 = letters[randLetter2];

        Code = "QZ"+rand1+L1+rand2+L2+rand3;

        return Code;
    }
}


