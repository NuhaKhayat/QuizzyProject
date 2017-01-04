package com.example.nuhakhayat.quizzy.Discussion;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.nuhakhayat.quizzy.StudyRoomActivity;

public class AddDiscussionActivity extends AppCompatActivity {

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

	public void addDiscussion(){
		String title = discussionTitle.getText().toString();
		String description = discussionDescription.getText().toString();
		String RoomID = getIntent().getStringExtra("RoomID");
		Log.d(TAG, title.toString());
		Log.d(TAG, description.toString());
		Log.d(TAG, RoomID.toString());

		if(title.isEmpty()){
			Toast.makeText(AddDiscussionActivity.this,"Please Enter a Title",Toast.LENGTH_SHORT)
					.show();
			return;
		}

		Cursor cursor = database.getDiscussion(title);
		if(cursor != null && cursor.moveToFirst()){
			Toast.makeText(getApplicationContext(),"Discussion Already Exist",Toast.LENGTH_LONG)
					.show();
		}else{
			long check = database.insertDisscussion(title,description,RoomID); //change 1 with room id
			if(check != -1){
				showToast("Discussion Added Successfully");
			}else{
				Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG)
						.show();
			}
		}
	}


	Thread thread = new Thread(){
		@Override
		public void run() {
			try {
				Thread.sleep(3500); // As I am using LENGTH_LONG in Toast
				startActivity(new Intent(AddDiscussionActivity.this,StudyRoomActivity.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(AddDiscussionActivity.this, StudyRoomActivity.class);
		intent.putExtra("RoomID",getIntent().getStringExtra("RoomID"));
		startActivity(intent);
	}

	public void showToast(String message){
		LayoutInflater inflater = getLayoutInflater();
		View toastView = inflater.inflate(R.layout.toast_layout,
				(ViewGroup) findViewById(R.id.toastLayout));

		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
		//		WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

		TextView toastMsg = (TextView)toastView.findViewById(R.id.textViewToast);
		toastMsg.setText(message);

		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.FILL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(toastView);
		toast.show();
		thread.start();
	}
}

