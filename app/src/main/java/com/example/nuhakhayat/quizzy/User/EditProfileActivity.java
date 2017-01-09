package com.example.nuhakhayat.quizzy.User;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.R;
import com.example.nuhakhayat.quizzy.broadcastResever;


public class EditProfileActivity extends AppCompatActivity {
	Button change;
	EditText edittxt1, edittxt2;
	Database db;
	broadcastResever brUsername;
	String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);

		db = new Database(getApplicationContext());
		change = (Button) findViewById(R.id.changePass);
		edittxt1 = (EditText) findViewById(R.id.editPass);
		edittxt2 = (EditText) findViewById(R.id.conPass);

		userId=brUsername.Username;




		change.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!edittxt1.getText().toString().isEmpty() && !edittxt2.getText().toString().isEmpty()&&
						(edittxt1.getText().toString().equals(edittxt2.getText().toString()) ))
				{
					db.changePass(userId,edittxt1.getText().toString());
					db.close();
					Toast.makeText(getApplicationContext(), "The password is changed successfully ", Toast.LENGTH_LONG).show();
				} else if  (!edittxt1.getText().toString().equals(edittxt2.getText().toString() )) {
					Toast.makeText(getApplicationContext(), "Please make sure your password match !", Toast.LENGTH_LONG).show();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Filed cannot be empty !", Toast.LENGTH_LONG).show();
				}

			}
		});
	}
}
