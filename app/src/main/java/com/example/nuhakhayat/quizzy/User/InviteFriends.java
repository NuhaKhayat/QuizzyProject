package com.example.nuhakhayat.quizzy.User;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nuhakhayat.quizzy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static com.example.nuhakhayat.quizzy.R.string.username;
import static com.example.nuhakhayat.quizzy.User.ForgotPassword.correctCode;

public class InviteFriends extends AppCompatActivity {
    // The ListView
    private ListView lstNames;
    public static int selectedItems[];
    public int SelectedItem;
    public static int test, index = 0;
    public static List<String> contacts;
    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    public String sendto, textMessage, subject;
    // Request code for READ_CONTACTS. It can be any number > 0.
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    Button inviteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);

        context = this;
        inviteBtn = (Button) findViewById(R.id.inviteBtn);
        inviteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(test >= 1) {
                    String validEmails[] = new String [100];
                    for (int i=0; i<test; i++) {

                        if (selectedItems[i] == 1) {
                            validEmails [i] = contacts.get(i);
                            index++;
                        }

                    }
                    inviteFriends(validEmails, index);
                }
            }
        });

        // Find the list view
        this.lstNames = (ListView) findViewById(R.id.myList);
        lstNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                for (int i = 0; i < lstNames.getChildCount(); i++) {
                    if(position == i ){
                        //check if the item already selected
                        if (selectedItems[i] == 0) {
                            selectedItems[i] = 1;
                            Log.d("i: ", i+"");
                            lstNames.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.LightGreen));
                        }else {
                            selectedItems[i] = 0;
                            lstNames.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        }
                    }
                }
            }
        });

        // Read and show the contacts\
        showContacts();
        selectedItems = new int[test];
        Log.d("test: ", test+"");
        //fill the whole array with zeros
        for (int i=0; i<test; i++) {
            selectedItems[i] = 0;
        }
    }

    /**
     * Show the contacts in the ListView.
     */
    public void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            contacts = getContactNames();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts);

            lstNames.setAdapter(adapter);

            test = contacts.size();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Read the name of all the contacts.
     *
     * @return a list of names.
     */
    private List<String> getContactNames() {
        ArrayList<String> names = new ArrayList<String>();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor cur1 = cr.query(
                        ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                        new String[]{id}, null);
                while (cur1.moveToNext()) {
                    //to get the contact names
                    //String name=cur1.getString(cur1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String email = cur1.getString(cur1.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    if(email!=null){
                        names.add(email);
                    }
                }
                cur1.close();
            }
        }
        return names;
    }

    public void inviteFriends(String EmailsList[], int index) {
        String current;
        //sendEmail("dalal.ghomlas@gmail.com");
        for (int i=0; i<index; i++) {
            current = EmailsList[i];

            sendEmail(current);
        }
    }

    public void sendEmail (String email) {
        //send emails to the whole list
        //send email
        sendto = email;
        subject = "Join Quizzy!";
        textMessage = "Hello friend!, \n This email was sent to you based on a request by one of your good friends. \n" +
                "we would love you to join us in Quizzy family by downloading Quizzy app!";

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

    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("QuizzyUOD@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendto));
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
}
