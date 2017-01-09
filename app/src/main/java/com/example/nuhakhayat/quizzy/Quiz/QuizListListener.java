package com.example.nuhakhayat.quizzy.Quiz;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.nuhakhayat.quizzy.Quiz.QuizActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NuhaKhayat on 1/3/17 AD.
 */
public class QuizListListener implements AdapterView.OnItemClickListener{

	List<String> allItems;
	Activity activity;

	public QuizListListener(Activity activity, List<String> allItems) {
		this.activity = activity;
		this.allItems = allItems;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(activity.getApplicationContext(),QuizActivity.class);
		intent.putExtra("title",allItems.get(position));
		activity.startActivity(intent);
	}
}


