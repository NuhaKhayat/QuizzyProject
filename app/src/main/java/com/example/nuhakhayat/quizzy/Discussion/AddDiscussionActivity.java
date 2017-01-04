package com.example.nuhakhayat.quizzy.Discussion;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.R;
import com.example.nuhakhayat.quizzy.StudyRoomActivity;

public class AddDiscussionActivity extends AppCompatActivity {

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
		int RoomID = getIntent().getIntExtra("RoomID",0);

		if(title.isEmpty()){
			Toast.makeText(AddDiscussionActivity.this,"Please Enter a Title",Toast.LENGTH_SHORT).show();
			return;
		}
		if(description.isEmpty()){
			description = "None";
		}
		/*Cursor cursor = database.getDiscussion(title);
		if(cursor == null){*/
			long check = database.insertDisscussion(title,description,1); //change 1 with room id
			if(check != -1){
				Toast.makeText(getApplicationContext(),"Discussion Added Successfully",Toast.LENGTH_LONG).show();

			}else{
				Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
			}
		/*}else{
			Toast toast = Toast.makeText(getApplicationContext(),"Discussion Already Exist",Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			thread.start();
		}*/
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
}
