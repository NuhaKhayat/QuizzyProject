package com.example.nuhakhayat.quizzy.Discussion;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.R;

public class DiscussionActivity extends AppCompatActivity {

	static final String TAG = "DiscussionActivity";
	Database database;
	TextView titleTV, descriptionTV;
	ImageView imageView;
	String title, description;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discussion);

		database = new Database(getApplicationContext());

		//Set discussion title
		title = getIntent().getStringExtra("title");
		titleTV = (TextView)findViewById(R.id.textView);
		titleTV.setText(title);


		//Retrieve discussion data
		Cursor cursor = database.getDiscussion(title);
		if (cursor != null && cursor.moveToFirst()){
			description = cursor.getString(cursor.getColumnIndex(Database.COLUMN_DISCUSSION_DESCRIPTION));
		}

		//Set discussion description
		descriptionTV = (TextView)findViewById(R.id.disDescTextView);
		descriptionTV.setText(description);

		imageView = (ImageView)findViewById(R.id.imageViewdis);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}
}
