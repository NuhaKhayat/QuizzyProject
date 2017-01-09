package com.example.nuhakhayat.quizzy.Quiz;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.nuhakhayat.quizzy.Database;
import com.example.nuhakhayat.quizzy.R;

/**
 * Created by NuhaKhayat on 1/8/17 AD.
 */
public class DisplayQuestionFragment extends Fragment {

	int  correctAnsPos, userPos, numOfq;
	int score = 0;
	Database database;
	Cursor cursor;
	RadioButton Ans1RB, Ans2RB, Ans3RB, Ans4RB;
	RadioGroup AnsRG;
	TextView qtv;
	String QuizName;
	Communicator communicator;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.display_question_layout,null,false);
		database = new Database(getActivity().getApplicationContext());
		QuizName = getArguments().getString("QuizName");

		qtv = (TextView) view.findViewById(R.id.display_question_textView);

		Ans1RB = (RadioButton)view.findViewById(R.id.display_answer_1_radioButton);
		Ans2RB = (RadioButton)view.findViewById(R.id.display_answer_2_radioButton);
		Ans3RB = (RadioButton)view.findViewById(R.id.display_answer_3_radioButton);
		Ans4RB = (RadioButton)view.findViewById(R.id.display_answer_4_radioButton);

		AnsRG = (RadioGroup)view.findViewById(R.id.display_answer_RadioGroup);

		cursor = database.getQuestions(QuizName);
		numOfq = cursor.getCount();
		cursor.moveToFirst();
		loadQuestion();

		AnsRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId){
					case R.id.display_answer_1_radioButton:
						userPos = 1;
						break;
					case R.id.display_answer_2_radioButton:
						userPos = 2;
						break;
					case R.id.display_answer_3_radioButton:
						userPos = 3;
						break;
					case R.id.display_answer_4_radioButton:
						userPos = 4;
						break;
				}
				if(correctAnsPos == userPos){
					score++;
				}
				if(cursor.moveToNext()) {
					loadQuestion();
				}else{
					communicator.onQuizFinish(numOfq,score);
				}
			}
		});
		return view;
	}

	public void loadQuestion(){
		qtv.setText(cursor.getString(cursor.getColumnIndex(Database.COLUMN_QUESTION)));
		Ans1RB.setText(cursor.getString(cursor.getColumnIndex(Database.COLUMN_ANSWER_1)));
		Ans2RB.setText(cursor.getString(cursor.getColumnIndex(Database.COLUMN_ANSWER_2)));
		Ans3RB.setText(cursor.getString(cursor.getColumnIndex(Database.COLUMN_ANSWER_3)));
		Ans4RB.setText(cursor.getString(cursor.getColumnIndex(Database.COLUMN_ANSWER_4)));
		correctAnsPos = cursor.getInt(cursor.getColumnIndex(Database.COLUMN_CORRECT_ANSWER_POSITION));
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			communicator = (Communicator) activity;
		}catch (Exception e){
		}
	}
}
