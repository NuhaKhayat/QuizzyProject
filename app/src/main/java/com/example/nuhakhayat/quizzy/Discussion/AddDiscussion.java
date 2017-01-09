package com.example.nuhakhayat.quizzy.Discussion;

import android.content.Intent;
import android.database.Cursor;
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

/*
 * The class is used to add a new discussion to a study room
 */
public class AddDiscussion extends AppCompatActivity {

	//Initialize necessary variables
	String TAG = "AddDiscussionActivity";
	EditText discussionTitle, discussionDescription;
	Button addDiscussionbtn;
	Database database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_discussion);

		database = new Database(getApplicationContext());

		discussionTitle = (EditText)findViewById(R.id.disTitleEditText);
		discussionDescription = (EditText)findViewById(R.id.disDesEditText);

		addDiscussionbtn = (Button)findViewById(R.id.AddDisButton);
		addDiscussionbtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addDiscussion();
			}
		});

	}

	//This method is used to add new discussion to the database
	public void addDiscussion(){
		//Retrieve data entered by user
		String title = discussionTitle.getText().toString();
		String description = discussionDescription.getText().toString();
		//Retrieve study room id from intent
		String RoomID = getIntent().getStringExtra("RoomID");

		//Check that discussion title is filled
		if(title.isEmpty()){
			Toast.makeText(AddDiscussion.this,"Please Enter a Title",Toast.LENGTH_SHORT)
					.show();
			return;
		}
		//Check the discussion dose not already exist
		Cursor cursor = database.getDiscussion(title);
		if(cursor != null && cursor.moveToFirst()){
			Toast.makeText(getApplicationContext(),"Discussion Already Exist",Toast.LENGTH_LONG)
					.show();
		}else{
			//Add to database and check the the addition has been preformed
			long check = database.insertDisscussion(title,description,RoomID);
			if(check != -1){
				showToast("Discussion Added Successfully");
			}else{
				Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG)
						.show();
			}
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(AddDiscussion.this, StudyRoomActivity.class);
		intent.putExtra("RoomID",getIntent().getStringExtra("RoomID"));
		startActivity(intent);
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
				startActivity(new Intent(AddDiscussion.this,StudyRoomActivity.class));
				finish();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
}
