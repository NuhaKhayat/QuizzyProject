package com.example.nuhakhayat.quizzy.StudyRoom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.R;
import com.example.nuhakhayat.quizzy.broadcastResever;

import java.util.ArrayList;

public class AddStudyRoomActivity extends AppCompatActivity {
	// Define the layout elements
	public static EditText courseName;
	public static CheckBox major1;
	public static CheckBox major2;
	public static CheckBox major3;
	public static CheckBox major4;
	public static CheckBox major5;
	public static Button addRoombtn;
	Database database;
	broadcastResever UserName;
	public static String UserId;
	public static String roomName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_study_room);

		// Initialize the layout elements
		courseName = (EditText)findViewById(R.id.RoomName);
		addRoombtn = (Button)findViewById(R.id.button7);
		major1 = (CheckBox)findViewById(R.id.Ch1);
		major2 = (CheckBox)findViewById(R.id.Ch2);
		major3 = (CheckBox)findViewById(R.id.Ch3);
		major4 = (CheckBox)findViewById(R.id.Ch4);
		major5 = (CheckBox)findViewById(R.id.Ch5);
		database = new Database(getApplicationContext());

		UserId = UserName.Username;
		roomName = courseName.getText().toString();

		addRoombtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				roomName = courseName.getText().toString();
				//Log.d("Edit Text", roomName);

				if (roomName.equals(null) || roomName.isEmpty()){
					Toast.makeText(AddStudyRoomActivity.this, "Please enter the Course Name", Toast.LENGTH_LONG).show();
					return;
				}else {
					addRoom(roomName);
				}
			}
		});

	}

	public void addRoom(String roomName){
		//roomName = courseName.getText().toString();
		ArrayList<String> majors = new ArrayList<String>();

		// check the checked CheckBoxes
		if (major1.isChecked()){
			String Maj1 = major1.getText().toString();
			majors.add(Maj1);
		}
		if (major2.isChecked()){
			String Maj2 = major2.getText().toString();
			majors.add(Maj2);
		}
		if (major3.isChecked()){
			String Maj3 = major3.getText().toString();
			majors.add(Maj3);
		}
		if (major4.isChecked()){
			String Maj4 = major4.getText().toString();
			majors.add(Maj4);
		}
		if (major5.isChecked()){
			String Maj5 = major5.getText().toString();
			majors.add(Maj5);
		}

		// Check if the Room already exist
		boolean check = database.checkRoom(roomName);
		//Log.d("Check Value:", "check = :"+ check);
		if ( check == true){
			// Insert the room in Room Table
			long sucAdd = database.insertRoom(roomName);
			//String RoomId = database.getLastAddedRoom();
			//Log.d("majors size: ",majors.size()+"");
			database.insertMajors(roomName, majors);
			database.insertSubRoom(UserId, roomName);
			if ( sucAdd != -1){
				showToast("Room Added Successfully");
			}else {
				Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
			}
		}else {
			Toast.makeText(getApplicationContext(), "Course Room Already Exist", Toast.LENGTH_LONG).show();
		}



	}

	Thread thread = new Thread(){
		@Override
		public void run() {
			try {
				Thread.sleep(3500); // As I am using LENGTH_LONG in Toast
				Intent intent = new Intent(AddStudyRoomActivity.this,StudyRoomActivity.class);
				intent.putExtra("RoomID", roomName);
				Log.d("the Intent:", roomName);
				startActivity(intent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	public void showToast(String message){
		LayoutInflater inflater = getLayoutInflater();
		View toastView = inflater.inflate(R.layout.toast_layout,
				(ViewGroup) findViewById(R.id.toastLayout));

		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
		//    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

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
