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
	String title,description;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discussion);
		Log.d(TAG,"onCreate");

		descriptionTV = (TextView)findViewById(R.id.disDescTextView);
		titleTV = (TextView)findViewById(R.id.textView);

		database = new Database(getApplicationContext());
		Cursor cursor = database.getDiscussion(title);

		if(cursor == null){
			Log.d(TAG,"Cursor null");
		}
		if (cursor != null){
			Log.d(TAG,"Cursor NOT null");
			Log.d(TAG,cursor.toString());
			description = cursor.getString(cursor.getColumnIndex(Database.COLUMN_DISCUSSION_DESCRIPTION));
		}
		if(cursor != null){
			Log.d(TAG,"Cursor NOT null");
		}

		if(cursor.moveToFirst()){
			Log.d(TAG,"moveToFirst true");
		}else{
			Log.d(TAG,"moveToFirst false");
		}

		title = getIntent().getStringExtra("title");
		titleTV.setText(title);
		descriptionTV.setText(description);

		imageView = (ImageView)findViewById(R.id.imageViewdis);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}
}
