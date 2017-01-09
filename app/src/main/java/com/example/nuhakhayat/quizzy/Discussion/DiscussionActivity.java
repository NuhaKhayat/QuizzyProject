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
import com.like.LikeButton;
import com.like.OnLikeListener;

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
				int id = cursor.getInt(cursor.getColumnIndex(Database.COLUMN_PK_COMMENT_ID));
				String username = cursor.getString(
						cursor.getColumnIndex(Database.COLUMN_FK_USERNAME_COMMENT));
				String comment = cursor.getString(cursor.getColumnIndex(Database.COLUMN_COMMENT));
				int liked = cursor.getInt(cursor.getColumnIndex(Database.COLUMN_COMMENT_LIKED));
				addCommentLayout(id,username,comment,liked);
				cursor.moveToNext();
			}
		}
	}

	//This method is used to dynamically add comment layout
	public void addCommentLayout(int commentID , String username, String comment, final int liked){

		//Retrieve and inflate the layout
		LayoutInflater inflater = (LayoutInflater)getApplicationContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View commetnView = inflater.inflate(R.layout.comment_layout,null);

		//Set the layout text view, username, comment, id
		final TextView commentIdTV = (TextView)commetnView.findViewById(R.id.comment_id_textView);
		TextView usernameTV = (TextView)commetnView.findViewById(R.id.username_comment_textView);
		TextView commentTV = (TextView)commetnView.findViewById(R.id.comment_textView);
		commentIdTV.setText(String.valueOf(commentID));
		usernameTV.setText(username);
		commentTV.setText(comment);

		//Set the layout like button value and attributes
		LikeButton likeCommentBtn = (LikeButton)commetnView.findViewById(R.id.like_commet_button);
		likeCommentBtn.setCircleEndColorRes(R.color.Yellow);
		likeCommentBtn.setCircleStartColorRes(R.color.MediumGreen);
		likeCommentBtn.setExplodingDotColorsRes(R.color.DarkGreen,R.color.Yellow);
		if(liked == 1){
			likeCommentBtn.setLiked(true);
		}else{
			likeCommentBtn.setLiked(false);
		}
		//Set like button click listener to update the liked value is database
		likeCommentBtn.setOnLikeListener(new OnLikeListener() {
			@Override
			public void liked(LikeButton likeButton) {
				likeButton.setLiked(true);
				int id = Integer.parseInt(commentIdTV.getText().toString());
				database.updateCommentLiked(id,1);

			}
			@Override
			public void unLiked(LikeButton likeButton) {
				likeButton.setLiked(false);
				int id = Integer.parseInt(commentIdTV.getText().toString());
				database.updateCommentLiked(id,0);
			}
		});
		ViewGroup rootLayout = (ViewGroup)findViewById(R.id.commentsLinearLayout);
		rootLayout.addView(commetnView,0, new ViewGroup.LayoutParams
				(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
	}
}
