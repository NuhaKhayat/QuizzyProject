package com.example.nuhakhayat.quizzy.Discussion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.R;

/**
 * Created by NuhaKhayat on 1/7/17 AD.
 * The class is used to add a new comment to a discussion
 * Popup activity
 */
public class AddCommentActivity extends AppCompatActivity {

	//Initialize necessary variables
	Button cancelBtn, addBtn;
	EditText commentET;
	Database database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_comment_layout);
		//Set the activity to be uncancelled
		this.setFinishOnTouchOutside(false);

		database = new Database(getApplicationContext());

		//Sets this activity to be a pop up
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		getWindow().setLayout((int) (dm.widthPixels*0.9) , (int) (dm.heightPixels* 0.3));

		commentET = (EditText)findViewById(R.id.commnet_editText);

		//Set listener for cancel button to finish the activity
		cancelBtn = (Button)findViewById(R.id.cancel_commet_button);
		cancelBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Return to parent activity with result canceled and finish
				Intent intent = new Intent();
				setResult(RESULT_CANCELED, intent);
				finish();
			}
		});

		//Set listener for add button to add the comment
		addBtn = (Button)findViewById(R.id.add_comment_button);
		addBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Retrieve data from edit text and intent
				String UserComment = commentET.getText().toString();
				String discussionTitle = getIntent().getStringExtra("discssionTitle");
				//get user name

				//Check that comment is filled
				if(UserComment.isEmpty()){
					Toast.makeText(getApplicationContext(),"Enter Comment",Toast.LENGTH_SHORT)
							.show();
					return;
				}

				//Add comment to database and check that comment is added
				Long check = database.insertCommetn(UserComment,discussionTitle,"UserTest"); //add user name
				if(check != -1){
					Toast.makeText(getApplicationContext(),"Comment Added Successfully",Toast.LENGTH_SHORT)
							.show();
					//Return to parent activity with result ok
					Intent intent = new Intent();
					setResult(RESULT_OK,intent);
					finish();
				}else{
					Toast.makeText(getApplicationContext(),"Something Went Wrong,Try Again",Toast.LENGTH_SHORT)
							.show();
					//Return to parent activity with result canceled
					Intent intent = new Intent();
					setResult(RESULT_CANCELED, intent);
					finish();
				}

			}
		});

	}
}
