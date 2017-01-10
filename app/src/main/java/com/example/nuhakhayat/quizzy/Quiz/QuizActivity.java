package com.example.nuhakhayat.quizzy.Quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.nuhakhayat.quizzy.R;

public class QuizActivity extends AppCompatActivity implements Communicator{

	TextView titleTV, countDownTV;
	int QuizID, counter;
	Bundle bundle;
	DisplayQuestionFragment displayQuestionFragment;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);

		counter = 5;

		QuizID = getIntent().getIntExtra("id",0);
		titleTV = (TextView)findViewById(R.id.titleTextView);
		titleTV.setText(getIntent().getStringExtra("title"));

		bundle = new Bundle();
		bundle.putString("QuizName", getIntent().getStringExtra("title"));

		countDownTV = (TextView)findViewById(R.id.count_down_textView);
		thread.start();

	}


	Thread thread = new Thread(){
		@Override
		public void run() {
			try {
				while (counter > 0){
					Thread.sleep(1000);
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							counter--;
							Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
							countDownTV.setAnimation(alphaAnimation);
							countDownTV.setText(String.valueOf(counter));
							if (counter == 0){
								countDownTV.clearAnimation();
								countDownTV.setVisibility(View.GONE);
								displayQuestionFragment = new DisplayQuestionFragment();
								displayQuestionFragment.setArguments(bundle);
								getFragmentManager().beginTransaction()
										.replace(R.id.QuestionfragContainer, displayQuestionFragment)
										.commit();
							}
						}
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	Thread thread1 = new Thread(){
		@Override
		public void run() {
			try {
				Thread.sleep(3000);
				finish();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};

	@Override
	public void onQuizFinish(int numOfq, int score) {
		displayQuestionFragment = (DisplayQuestionFragment) getFragmentManager()
				.findFragmentById(R.id.QuestionfragContainer);
		getFragmentManager().beginTransaction()
				.remove(displayQuestionFragment)
				.commit();

		countDownTV.setVisibility(View.VISIBLE);
		countDownTV.setText("YOR SCORE "+score+"/"+numOfq);
		thread1.start();
	}

	@Override
	public void onQuestionAdd(String q, String a1, String a2, String a3, String a4, int pos) {

	}

	@Override
	public void onQuizAdd(String title, int numOfq) {

	}

	@Override
	public void onBackPressed() {
		finish();
	}
}
