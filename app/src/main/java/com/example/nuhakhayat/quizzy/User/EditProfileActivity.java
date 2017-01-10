package com.example.nuhakhayat.quizzy.User;

import android.content.BroadcastReceiver;
import android.content.Intent;
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
import com.example.nuhakhayat.quizzy.broadcastResever;


public class EditProfileActivity extends AppCompatActivity {
	Button change;
	EditText edittxt1, edittxt2;
	Database db;
	broadcastResever brUsername;
	String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);

		db = new Database(getApplicationContext());
		change = (Button) findViewById(R.id.changepass);
		edittxt1 = (EditText) findViewById(R.id.editPass);
		edittxt2 = (EditText) findViewById(R.id.conPass);

		userId=brUsername.Username;





		change.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!edittxt1.getText().toString().isEmpty() && !edittxt2.getText().toString().isEmpty()&&
						(edittxt1.getText().toString().equals(edittxt2.getText().toString()) ))
				{
					db.changePass(userId,edittxt1.getText().toString());
					db.close();
					showToast("The password is changed successfully");
				} else if  (!edittxt1.getText().toString().equals(edittxt2.getText().toString() )) {
					Toast.makeText(getApplicationContext(), "Please make sure your password match !", Toast.LENGTH_LONG).show();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Fileds cannot be empty !", Toast.LENGTH_LONG).show();
				}

			}
		});
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
				finish();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
}
