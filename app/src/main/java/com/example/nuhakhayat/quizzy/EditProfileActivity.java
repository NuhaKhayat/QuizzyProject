package com.example.nuhakhayat.quizzy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {
	int id;
	Button change;
	EditText edittxt1, edittxt2;
	Database db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		Intent intent = getIntent();
		id = intent.getIntExtra("id", 0);
		db = new Database(getApplicationContext());

		change = (Button) findViewById(R.id.changepass);
		edittxt1 = (EditText) findViewById(R.id.editPass);
		edittxt2 = (EditText) findViewById(R.id.conPass);
		change.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!edittxt1.getText().toString().isEmpty() && !edittxt2.getText().toString().isEmpty() &&
						(edittxt1.getText().toString()==edittxt1.getText().toString()))
				{
					//db.open;
					db.changePass(id, edittxt1.getText().toString());
					db.close();
					Toast.makeText(getApplicationContext(), "The password is changed successfully ", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getApplicationContext(), "Filed cannot be empty!", Toast.LENGTH_LONG).show();
				}

			}
		});
	}
}
