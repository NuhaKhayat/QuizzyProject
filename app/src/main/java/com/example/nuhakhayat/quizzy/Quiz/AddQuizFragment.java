package com.example.nuhakhayat.quizzy.Quiz;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nuhakhayat.quizzy.R;

/**
 * Created by NuhaKhayat on 1/8/17 AD.
 */
public class AddQuizFragment extends Fragment {

	int qestionCount = 2;
	RadioGroup qestionNumGroup;
	RadioButton qestionNumRB;
	EditText titleET;
	Communicator communicator;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_add_quiz, null, false);

		titleET = (EditText)view.findViewById(R.id.quiz_title_editText);

		qestionNumGroup = (RadioGroup)view.findViewById(R.id.qestion_num_radioGroup);
		qestionNumGroup.check(R.id.radioButton_2q);
		qestionNumGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int selectedRBid = qestionNumGroup.getCheckedRadioButtonId();
				qestionNumRB = (RadioButton)view.findViewById(selectedRBid);
				qestionCount = Integer.parseInt(qestionNumRB.getText().toString());
				Log.d("selectedRB", qestionNumRB.getText().toString());
			}
		});
		return view;
	}


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			communicator = (Communicator) activity;
		}catch (Exception e){
			Log.d("QuestionFragment",e.getMessage());

		}
	}

	public boolean getData(){
		String title = titleET.getText().toString();
		if(title.isEmpty()){
			Toast.makeText(getActivity().getApplicationContext(),"Enter quiz title",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		communicator.onQuizAdd(title,qestionCount);
		return true;
	}

}
