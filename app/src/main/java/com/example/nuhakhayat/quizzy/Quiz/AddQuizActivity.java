package com.example.nuhakhayat.quizzy.Quiz;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.R;
import com.example.nuhakhayat.quizzy.StudyRoom.StudyRoomActivity;

public class AddQuizActivity extends AppCompatActivity implements Communicator{

	int qestionCount;
	Button next;
	AddQuestionFragment questionFragment;
	AddQuizFragment addQuizFragment;
	Database database;
	int clickNum;
	String qtitle;
	String TAG = "AddQuizActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_quiz);

		database = new Database(getApplicationContext());

		getFragmentManager().beginTransaction()
				.add(R.id.fragmentContainer, new AddQuizFragment())
				.commit();
		clickNum = 0;

		next = (Button)findViewById(R.id.addQuiz_next_button);
		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(clickNum == 0){
					Log.d(TAG,"clickNum == 0");
					addQuizFragment = (AddQuizFragment) getFragmentManager()
							.findFragmentById(R.id.fragmentContainer);

					if(addQuizFragment.getData()){
						getFragmentManager().beginTransaction()
								.setCustomAnimations(R.animator.slide_left_right,R.animator.slide_right_left)
								.show(addQuizFragment)
								.remove(addQuizFragment)
								.add(R.id.fragmentContainer, new AddQuestionFragment())
								.commit();
						clickNum++;
					}
				}else{
					if(clickNum <= qestionCount){
						Log.d(TAG,"clickNum < qestionCount");
						questionFragment = (AddQuestionFragment) getFragmentManager()
								.findFragmentById(R.id.fragmentContainer);

						if(questionFragment.getData()){
							getFragmentManager().beginTransaction()
									.setCustomAnimations(R.animator.slide_left_right,R.animator.slide_right_left)
									.show(questionFragment)
									.remove(questionFragment)
									.add(R.id.fragmentContainer, new AddQuestionFragment())
									.commit();
							clickNum++;
						}
						if (clickNum > qestionCount){
							showToast("Quiz and Question Added Successfully");
						}
					}
				}
			}
		});
	}

	@Override
	public void onQuestionAdd(String q, String a1, String a2, String a3, String a4, int pos) {
		//Add to database and check the the addition has been preformed
		long check = database.insertQuestion(q,a1,a2,a3,a4,pos,qtitle);
		if(check != -1){

		}else{
			Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG)
					.show();
		}
	}

	@Override
	public void onQuizAdd(String title, int numOfq) {

		//Add to database and check the the addition has been preformed
		long check = database.insertQuiz(title, numOfq, "1"); //add room id
		if(check != -1){
			qtitle = title;
		}else{
			Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG)
					.show();
		}
		qestionCount = numOfq;
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
		toastMsg.setGravity(Gravity.CENTER);

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
				startActivity(new Intent(AddQuizActivity.this,StudyRoomActivity.class));
				finish();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};


	@Override
	public void onQuizFinish(int numOfq, int score) {

	}
}

