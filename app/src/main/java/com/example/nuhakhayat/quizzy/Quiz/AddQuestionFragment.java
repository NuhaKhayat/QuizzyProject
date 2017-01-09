package com.example.nuhakhayat.quizzy.Quiz;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nuhakhayat.quizzy.R;

/**
 * Created by NuhaKhayat on 1/8/17 AD.
 */
public class AddQuestionFragment extends Fragment{

	String TAG = "AddQuestionFragment";
	EditText question, answer1, answer2, answer3, answer4;
	RadioGroup correctAnswer;
	Button add;
	int correctAnsPos = 0;
	Communicator communicator;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.add_question_layout,null,false);
		Log.d(TAG,"onCreateView");
		question = (EditText)view.findViewById(R.id.qestion_editText);
		answer1 = (EditText)view.findViewById(R.id.answer_1_editText);
		answer2 = (EditText)view.findViewById(R.id.answer_2_editText);
		answer3 = (EditText)view.findViewById(R.id.answer_3_editText);
		answer4 = (EditText)view.findViewById(R.id.answer_4_editText);
		correctAnswer = (RadioGroup)view.findViewById(R.id.answer_RadioGroup);
		correctAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId){
					case R.id.answer_1_radioButton:
						correctAnsPos = 1;
						break;
					case R.id.answer_2_radioButton:
						correctAnsPos = 2;
						break;
					case R.id.answer_3_radioButton:
						correctAnsPos = 3;
						break;
					case R.id.answer_4_radioButton:
						correctAnsPos = 4;
						break;

				}
			}
		});
		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			communicator = (Communicator) activity;
			Log.d(TAG,"onAttach");
		}catch (Exception e){
			Log.d("QuestionFragment",e.getMessage());

		}
	}

	public boolean getData(){
		Log.d(TAG,"getData");
		String q = question.getText().toString();
		String a1 = answer1.getText().toString();
		String a2 = answer2.getText().toString();
		String a3 = answer3.getText().toString();
		String a4 = answer4.getText().toString();
		if(q.isEmpty()){
			Toast.makeText(getActivity().getApplicationContext(),"Enter question",
					Toast.LENGTH_SHORT).show();
			return false;
		}

		if (a1.isEmpty() || a2.isEmpty() || a3.isEmpty() || a4.isEmpty() ){
			Toast.makeText(getActivity().getApplicationContext(),"Enter all answers",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		if(correctAnsPos == 0){
			Toast.makeText(getActivity().getApplicationContext(),"Select correct answer",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		communicator.onQuestionAdd(q,a1,a2,a3,a4,correctAnsPos);
		return true;
	}
}
