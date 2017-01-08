package com.example.nuhakhayat.quizzy.Quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nuhakhayat.quizzy.R;

public class QuizActivity extends AppCompatActivity {

	ImageView closeIV;
	TextView titleTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);

		titleTV = (TextView)findViewById(R.id.titleTextView);
		titleTV.setText(getIntent().getStringExtra("title"));

		closeIV = (ImageView)findViewById(R.id.imageViewClose);
		closeIV.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				closeActivity();
			}
		});
	}

	public void closeActivity(){

	}
}
