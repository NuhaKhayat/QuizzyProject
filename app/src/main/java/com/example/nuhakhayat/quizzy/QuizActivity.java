package com.example.nuhakhayat.quizzy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		TextView titleTextView = (TextView)findViewById(R.id.titleTextView);
		titleTextView.setText(getIntent().getStringExtra("title"));
	}
}
