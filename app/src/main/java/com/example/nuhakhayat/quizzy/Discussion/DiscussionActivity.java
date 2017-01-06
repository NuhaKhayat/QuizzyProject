package com.example.nuhakhayat.quizzy.Discussion;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.R;

/**
 * Created by NuhaKhayat on 1/4/17 AD.
 * The class is used to display a discussion with associated comments
 */
public class DiscussionActivity extends AppCompatActivity {

	//Initialize necessary variables
	static final String TAG = "DiscussionActivity";
	private static final int ADD_COMMENT_SUB_ACTIVITY = 1212;
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
		titleTV = (TextView)findViewById(R.id.textViewDisTitle);
		titleTV.setText(title);


		//Retrieve discussion data
		Cursor cursor = database.getDiscussion(title);
		if (cursor != null && cursor.moveToFirst()){
			description = cursor.getString(
					cursor.getColumnIndex(Database.COLUMN_DISCUSSION_DESCRIPTION));
		}

		//Set discussion description
		descriptionTV = (TextView)findViewById(R.id.disDescTextView);
		descriptionTV.append(description);

		//Display all comments in
		displayAllComments();

		//Set add comment button image
		imageView = (ImageView)findViewById(R.id.imageViewAddcom);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Start add comment popup activity
				Intent intent = new Intent(DiscussionActivity.this,AddCommentActivity.class);
				intent.putExtra("discssionTitle",title);
				startActivityForResult(intent,ADD_COMMENT_SUB_ACTIVITY);
			}
		});
	}


	//This method is used to refresh comments when new comment is entered in sub activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == ADD_COMMENT_SUB_ACTIVITY && resultCode == RESULT_OK){
			displayAllComments();
		}
	}

	//This method is used to retrieve all discussion comments from dataabse
	public void displayAllComments(){
		Cursor cursor = database.getComments(title);
		if(cursor != null && cursor.moveToFirst()){
			while(cursor.isAfterLast() == false){
				String username = cursor.getString(
						cursor.getColumnIndex(Database.COLUMN_FK_USERNAME_COMMENT));
				String comment = cursor.getString(cursor.getColumnIndex(Database.COLUMN_COMMENT));
				addCommentLayout(username,comment);
				cursor.moveToNext();
			}
		}
	}

	//This method is used to dynamically add comment layout
	public void addCommentLayout(String username, String comment){
		LayoutInflater inflater = (LayoutInflater)getApplicationContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View commetnView = inflater.inflate(R.layout.comment_layout,null);

		TextView usernameTV = (TextView)commetnView.findViewById(R.id.username_comment_textView);
		TextView commentTV = (TextView)commetnView.findViewById(R.id.comment_textView);
		usernameTV.setText(username);
		commentTV.setText(comment);

		ViewGroup rootLayout = (ViewGroup)findViewById(R.id.commentsLinearLayout);
		rootLayout.addView(commetnView,0, new ViewGroup.LayoutParams
				(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
	}
}
